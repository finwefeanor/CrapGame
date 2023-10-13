# Crap Game

Crap Game is a popular dice game where players make wagers on the outcome of the roll, or a series of rolls, of a pair of dice.


## Running the Project

### Maven Wrapper:
Navigate to the root directory of the project and run:

```bash
./mvnw spring-boot:run
```

### Sending HTTP Requests:
After Starting the project, you can send HTTP requests

### Available Endpoints:
- `POST /play/multi`: Play multiple rounds of the Crap Game. The number of rounds is specified in the request body.


### Using PowerShell on Windows:
```
$response = Invoke-WebRequest -Method POST -Uri http://localhost:8080/play/multi -BODY '{"games": 5}' -ContentType "application/json"
$response.Content | Format-List
```
### Using curl:
If you have curl installed, you can use the following command:

```
curl -X POST -H "Content-Type: application/json" -d '{"games": 5}' http://localhost:8080/play/multi
```

### Sample Responses

```
{
            "gameId": 3,
            "dice1": 6,
            "dice2": 2,
            "stake": 1,
            "winning": 0,
            "rolls": [
                {
                    "dice1": 3,
                    "dice2": 1,
                    "sum": 4,
                    "message": "Continue to roll",
                    "rollNumber": 1,
                    "playerPointNumber": 8
                },
                {
                    "dice1": 4,
                    "dice2": 2,
                    "sum": 6,
                    "message": "Continue to roll",
                    "rollNumber": 2,
                    "playerPointNumber": 8
                },
                {
                    "dice1": 2,
                    "dice2": 5,
                    "sum": 7,
                    "message": "Oh no! You rolled a 7 on this roll, and your Player Point was 8, You Lost!",
                    "rollNumber": 3,
                    "playerPointNumber": 8
                }
            ],
            "message": "You lost this round by rolling a 7, you need to match the Player Point"
```

```
{
            "gameId": 4,
            "dice1": 1,
            "dice2": 6,
            "stake": 1,
            "winning": 2,
            "rolls": [],
            "message": "You won on the first throw! Wonderful"
        },
```

```
{
            "gameId": 5,
            "dice1": 2,
            "dice2": 1,
            "stake": 1,
            "winning": 0,
            "rolls": [],
            "message": "You lost on the first throw! Unlucky"
        }
```
