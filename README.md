# Crap Game

Roll Dice Game in Java

## Running the Project

### Maven Wrapper:
Navigate to the root directory of the project and run:

```bash
./mvnw spring-boot:run
```

### Sending HTTP Requests:
After Starting the project, you can send HTTP requests

### Using PowerShell on Windows:
```
$response = Invoke-WebRequest -Method POST -Uri http://localhost8080/play/multi -BODY '{"rounds": 5}' -ContentType "application/json"
$response.Content | Format-List
```
### Using curl:
If you have curl installed, you can use the following command:

```
curl -X POST -H "Content-Type: application/json" -d '{"rounds": 5}' http://localhost:8080/play/multi
```


