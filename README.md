# MapReduce WordCount Application

## Overview
This is a classic Hadoop MapReduce application that counts the frequency of words in text files.

## Problem Fixed
The original code had **missing Hadoop dependencies** causing compilation errors. All `org.apache.hadoop.*` imports could not be resolved because the required JAR files were not in the classpath.

## Solution Applied

### 1. **Added Maven Build Configuration**
- Created `pom.xml` with all necessary Hadoop dependencies
- Configured Maven Shade Plugin to create a fat JAR with all dependencies
- Set up proper Java compilation settings

### 2. **Fixed Hadoop Configuration**
- Added local file system configuration to run without a full Hadoop cluster
- Configured the job to run in local mode
- Added proper path handling for local file system

### 3. **Added Logging Configuration**
- Created `log4j.properties` to reduce unnecessary warning messages

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

## Sample Output
Given the input file with university names:
```
USM UTP UITM
UNIMAP UIA UNITAR
AIMST USM UIA USM
UNITAR USM UTAR AIMST
UTM USM UITM
```

The output will be:
```
AIMST   2
UIA     2
UITM    2
UNIMAP  1
UNITAR  2
USM     5
UTAR    1
UTM     1
UTP     1
```

## Error Resolution Summary

### Original Errors:
1. **Import Resolution Errors**: `The import org.apache cannot be resolved`
2. **Type Resolution Errors**: `Configuration cannot be resolved to a type`
3. **Method Resolution Errors**: `Job.getInstance() cannot be resolved`

### Root Cause:
- Missing Hadoop libraries in classpath
- No build system to manage dependencies

### Fix Applied:
- Added Maven configuration with all required Hadoop dependencies
- Configured application for local execution mode
- Created fat JAR with all dependencies included

## Running in Different Modes

### Local Mode (Current Setup)
- Runs entirely on local machine
- No Hadoop cluster required
- Uses local file system

### Cluster Mode (For Production)
To run on a real Hadoop cluster, modify the configuration in `WordCountDriver.java`:
```java
// Remove these lines for cluster mode:
conf.set("fs.defaultFS", "file:///");
conf.set("mapreduce.framework.name", "local");
```

## Requirements
- Java 8 or higher
- Maven 3.6 or higher
- No Hadoop installation required (dependencies included in JAR)
