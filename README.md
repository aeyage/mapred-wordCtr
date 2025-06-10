# MapReduce Word Counter

A Hadoop MapReduce implementation that counts the frequency of words in text files.

## Project Structure
```
mapred-wordCtr/
├── pom.xml                 # Maven build configuration
├── run-wordcount.sh        # Convenience script to run the application
├── src/
│   ├── WordCountDriver.java    # Main driver class
│   ├── WordCountMapper.java    # Mapper implementation
│   ├── WordCountReducer.java   # Reducer implementation
│   └── main/resources/
│       └── log4j.properties   # Logging configuration
├── input/
│   └── input.txt           # Sample input file
└── target/
    └── mapred-wordctr-1.0-SNAPSHOT.jar  # Built application JAR
```

## Dependencies Used
- **Hadoop Client**: Core Hadoop functionality
- **Hadoop Common**: Common utilities and file system support
- **Hadoop HDFS**: Hadoop Distributed File System support
- **Hadoop MapReduce**: MapReduce framework components

## How to Build
```bash
mvn clean compile package
```

## How to Run

### Option 1: Using the convenience script
```bash
./run-wordcount.sh input output
```

### Option 2: Using Java directly
```bash
java -cp target/mapred-wordctr-1.0-SNAPSHOT.jar WordCountDriver input output
```

## Running in Different Modes

### Local Mode
- Runs entirely on local machine
- No Hadoop cluster required
- Uses local file system

### Cluster Mode (For Prods)
To run on a real Hadoop cluster, modify the configuration in `WordCountDriver.java`:
```java
// Remove these lines for cluster mode:
conf.set("fs.defaultFS", "file:///");
conf.set("mapreduce.framework.name", "local");
```