# QuantityMeasurementApp UC16

This project is configured for UC16 with layered packages and JDBC persistence.

## Key points

- Package base: `com.app.quantitymeasurement`
- Repository strategy: cache or database (`app.repository.type`)
- Default database: H2
- Schema file: `src/main/resources/db/schema.sql`
- Tests include repository, service, controller, and integration coverage

## Run

```powershell
mvn clean test
mvn clean package
mvn exec:java -Dexec.mainClass=com.app.quantitymeasurement.QuantityMeasurementApp
```

