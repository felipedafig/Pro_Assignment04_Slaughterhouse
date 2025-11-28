# Pro Assignment 4 - Slaughterhouse Microservices

This project is a multi-module Maven project refactored into a microservices architecture. It uses **Docker** to provide the necessary database (PostgreSQL) and message broker (RabbitMQ).

## ⚡ Quick Start (Fix for Errors)

If you are seeing errors like `via:common:jar:0.0.1-SNAPSHOT is missing` or `Connection refused` (RabbitMQ), follow these steps strictly.

### 1. Install Dependencies
Run the install script to build the shared library (`common`) so other stations can find it.

*   **Windows**: Double-click `install_dependencies.bat`
*   **Mac/Linux**: Run `./install_dependencies.sh`

*(Or manually run `mvn clean install -DskipTests` in the root folder)*

### 2. Start Infrastructure (RabbitMQ & Database)
You **must** have Docker Desktop installed and running.

*   **Windows**: Double-click `run_infrastructure.bat`
*   **Mac/Linux**: Run `./run_infrastructure.sh`

*(Or manually run `docker-compose up -d` in the root folder)*

This will start:
*   **PostgreSQL** on port `5432` (Databases: st1, st2, st3, trace)
*   **RabbitMQ** on port `5672` (Management UI: http://localhost:15672)

### 3. Run the Stations
Once the above steps are done, open separate terminals for each station and run them:

**Station 1 (Animal Registration):**
```bash
cd station1
mvn spring-boot:run
```

**Station 2 (Cutting):**
```bash
cd station2
mvn spring-boot:run
```

**Station 3 (Packing):**
```bash
cd station3
mvn spring-boot:run
```

**Traceability Service:**
```bash
cd traceability
mvn spring-boot:run
```

## 🧪 Testing with Postman
Import `postman_collection.json` into Postman to test the APIs.

## 🛠 Troubleshooting

*   **"Could not find artifact via:common..."**: Run Step 1 again.
*   **"Connection refused" / RabbitMQ error**: Run Step 2 again. Check if Docker is running (`docker ps`).
*   **Database Schema Errors**: If you changed the DB schema, you might need to delete the old Docker volume:
    ```bash
    docker-compose down -v
    docker-compose up -d
    ```
