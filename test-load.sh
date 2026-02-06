$url = "http://localhost:8080/api/v1/users/1"
Write-Host "Starting stress test: 50 requests..."
for ($i=1; $i -le 50; $i++) {
    Invoke-WebRequest -Uri $url -Headers @{"X-Tenant-ID"="India"} -UseBasicParsing | Out-Null
    Write-Host -NoNewline "."
}
Write-Host "`nTest Complete."

#!/bin/bash
# URL of your API Gateway or Identity Service
URL="http://localhost:8080/api/v1/users/1"

echo "Starting stress test: 50 requests..."

for i in {1..50}
do
   # Send request and print a small marker
   curl -s -H "X-Tenant-ID: India" $URL > /dev/null
   echo -n "."
done

echo -e "\nTest Complete. Check your IntelliJ logs to see requests hitting different ports!"