# Dimension Data Server Console App

Console application for managing servers

## Running application 
1. Clone project.
2. You can either configure the config in application.properties to run against your own H2 db. Or you can leave it 
commented out which will run the app against an in memory db.
```
spring.datasource.url={url}
spring.datasource.username={username}
spring.datasource.password={password}
```
3. Cd to root directory of project and run:
```
mvn clean install
```
or this to skip running tests
```
mvn clean install -DskipTests
```
* This will run the integration tests and output a jar to the target directory.
5. Run the following from project root:
```
java -jar  target/dimension-data-app-0.0.1-SNAPSHOT.jar
```
## Supported commands
### Built-In Commands
* clear: Clear the shell screen.
* exit, quit: Exit the shell.
* help: Display help about available commands.
* script: Read and execute commands from a file.
* stacktrace: Display the full stacktrace of the last error.

### Server Commands
* count-servers: Count servers
    * example: count-servers
* create-server: Creates a server
    * example: create-server --name serverName --description "Info about the server"
* delete-server: Deletes a server
    * example: delete-server --id 1
* import-server-from-xml-file: Import server from xml file
    * example: import-server-from-xml-file --file-name serverFromFile.xml
* list-servers: List servers (all args are optional)
    * example: list-servers --name-containing this --description-containing this --page 0 --size 2
* update-server: Updates a server

Also see integration tests for examples