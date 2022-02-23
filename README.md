# Programming-Lab5-2Semester
## Interactive Collection Worker
### setup
```
# create xml file and set it as environment variable with name "COLLECTION_FILE"
# win
setx COLLECTION_FILE full/path/to/the/file
# linux
export COLLECTION_FILE=full/path/to/the/file
```

### build
```
mvn install
```

### run
```
java -jar lab-client/target/lab-client-1.0-SNAPSHOT-jar-with-dependencies.jar
```
