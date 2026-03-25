import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {
    public static final String FILE_NAME = "quantity_measurement_repo.ser";

    private static QuantityMeasurementCacheRepository instance;

    private final List<QuantityMeasurementEntity> cache;

    private QuantityMeasurementCacheRepository() {
        cache = new ArrayList<>();
        loadFromDisk();
    }

    public static synchronized QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }
        return instance;
    }

    @Override
    public synchronized void save(QuantityMeasurementEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        cache.add(entity);
        saveToDisk(entity);
    }

    @Override
    public synchronized List<QuantityMeasurementEntity> getAllMeasurements() {
        return Collections.unmodifiableList(new ArrayList<>(cache));
    }

    private void saveToDisk(QuantityMeasurementEntity entity) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME, true);
             AppendableObjectOutputStream oos = new AppendableObjectOutputStream(fos)) {
            oos.writeObject(entity);
            oos.flush();
        } catch (IOException ex) {
            throw new QuantityMeasurementException("Error saving quantity measurement entity", ex);
        }
    }

    private void loadFromDisk() {
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0L) {
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                Object item = ois.readObject();
                if (item instanceof QuantityMeasurementEntity) {
                    cache.add((QuantityMeasurementEntity) item);
                }
            }
        } catch (EOFException ignored) {
            // End of file is expected when reading serialized stream.
        } catch (IOException | ClassNotFoundException ex) {
            cache.clear();
            System.err.println("Warning: unable to load existing measurement history, starting with empty cache");
        }
    }

    private static final class AppendableObjectOutputStream extends ObjectOutputStream {
        private AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            File file = new File(FILE_NAME);
            if (!file.exists() || file.length() == 0L) {
                super.writeStreamHeader();
            } else {
                reset();
            }
        }
    }
}
