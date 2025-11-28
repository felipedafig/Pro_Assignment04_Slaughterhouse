param(
    [string]$PostmanPath = "$env:LOCALAPPDATA\Postman\Postman.exe",
    [switch]$SkipPostman
)

$ErrorActionPreference = "Stop"

function Test-PortReady {
    param(
        [string]$Host,
        [int]$Port,
        [string]$ServiceName
    )

    try {
        $connection = Test-NetConnection -ComputerName $Host -Port $Port -WarningAction SilentlyContinue
        if (-not $connection.TcpTestSucceeded) {
            Write-Warning "$ServiceName not reachable on $Host:$Port. Start it manually before running the stations."
        }
    } catch {
        Write-Warning "Unable to verify $ServiceName on $Host:$Port ($_)."
    }
}

function Start-Station {
    param(
        [string]$StationName,
        [string]$RelativePath
    )

    $modulePath = Join-Path -Path $PSScriptRoot -ChildPath $RelativePath
    if (-not (Test-Path $modulePath)) {
        Write-Warning "Skipping $StationName: path $modulePath not found."
        return
    }

    $command = @"
Set-Location -LiteralPath `"$modulePath`"
..\mvnw.cmd spring-boot:run
"@

    Start-Process -FilePath "powershell.exe" -ArgumentList "-NoExit","-Command",$command | Out-Null
    Write-Host "Launched $StationName in a new PowerShell window."
}

Write-Host "Checking core services..."
Test-PortReady -Host "localhost" -Port 5432 -ServiceName "PostgreSQL"
Test-PortReady -Host "localhost" -Port 5672 -ServiceName "RabbitMQ"

Write-Host "Starting stations..."
Start-Station -StationName "Station 1" -RelativePath "station1"
Start-Station -StationName "Station 2" -RelativePath "station2"
Start-Station -StationName "Station 3" -RelativePath "station3"
Start-Station -StationName "Traceability" -RelativePath "traceability"

if (-not $SkipPostman) {
    if (Test-Path $PostmanPath) {
        Start-Process -FilePath $PostmanPath | Out-Null
        Write-Host "Postman launched from $PostmanPath."
    } else {
        Write-Warning "Postman executable not found at $PostmanPath. Use -PostmanPath to point to the correct location or pass -SkipPostman."
    }
} else {
    Write-Host "Skipping Postman launch as requested."
}

Write-Host "All launch commands issued. Watch each station window for 'Started ... in X seconds'."

