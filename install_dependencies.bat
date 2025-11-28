@echo off
echo Installing common dependencies...
call mvn clean install -DskipTests
if %errorlevel% neq 0 (
    echo Error: Failed to install common dependencies.
    pause
    exit /b %errorlevel%
)
echo Dependencies installed successfully.
pause
