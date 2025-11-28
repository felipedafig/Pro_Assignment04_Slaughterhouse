@echo off
echo Starting Infrastructure (PostgreSQL + RabbitMQ)...
docker-compose up -d
if %errorlevel% neq 0 (
    echo Error: Failed to start Docker containers. Make sure Docker Desktop is running.
    pause
    exit /b %errorlevel%
)
echo Infrastructure started successfully!
echo You can now run the stations.
pause
