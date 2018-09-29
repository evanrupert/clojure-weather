# Clojure CLI weather

Command interface to display the current weather given a location identifier

## Usage

```bash
weather <command> [Choice of location option]
```
#### Commands
| Command     | Description                    |
|-------------|--------------------------------|
| description | Get current weather description|
| temperature | Get current temperature        |
| wind-speed  | Get current wind speed         |
| humidity    | Get current humidity           |

#### Location options:
| Short Command | Long Command | Description                                    |
|---------------|--------------|------------------------------------------------| 
| -l            | --location   |  Get weather at given latitude and longitude\n |
| -c            | --city       |  Get weather at given city name\n              |
| -i            | --id         |  Get weather at given city id\n                |
| -z            | --zip-code   |  Get weather at given zip code\n               |
| -h            | --help       |  Display this help message\n                   |
