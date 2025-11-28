# Pro Assignment 4 - Slaughterhouse Microservices

This project is a multi-module Maven project refactored into a microservices architecture. It uses **Docker** to provide the necessary database (PostgreSQL) and message broker (RabbitMQ).

## 🏛️ Architecture
The system follows an **Event-Driven Architecture** using **RabbitMQ** (Fanout Exchanges) to propagate events to multiple listeners dynamically.

*   **Station 1 (Animal Registration)**:
    *   **Publisher**: Publishes `AnimalRegisteredEvent` to `animal-events` exchange.
    *   **Database**: `st1` schema.
*   **Station 2 (Cutting)**:
    *   **Subscriber**: Listens to `animal-events`.
    *   **Publisher**: Publishes `PartAddedToTrayEvent` to `part-events` exchange.
    *   **Database**: `st2` schema.
*   **Station 3 (Packing)**:
    *   **Subscriber**: Listens to `part-events`.
    *   **Publisher**: Publishes `ProductCreatedEvent` to `product-events` exchange.
    *   **Database**: `st3` schema.
*   **Traceability Service**:
    *   **Subscriber**: Listens to `animal-events`, `part-events`, and `product-events`.
    *   **Database**: `trace` schema.
    *   **Purpose**: Logs the entire lifecycle of an animal -> part -> product.

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
*   **"Queue not found"**: Ensure you have pulled the latest code where `RabbitMQConfig` declares the queues automatically.
*   **Database Schema Errors**: If you changed the DB schema, you might need to delete the old Docker volume:
    ```bash
    docker-compose down -v
    docker-compose up -d
    ```
