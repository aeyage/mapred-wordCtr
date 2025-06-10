#!/bin/bash

# WordCount MapReduce Runner Script

if [ $# -ne 2 ]; then
    echo "Usage: $0 <input_directory> <output_directory>"
    echo "Example: $0 input output"
    exit 1
fi

INPUT_DIR=$1
OUTPUT_DIR=$2

# Remove output directory if it exists
if [ -d "$OUTPUT_DIR" ]; then
    echo "Removing existing output directory: $OUTPUT_DIR"
    rm -rf "$OUTPUT_DIR"
fi

echo "Running WordCount MapReduce job..."
echo "Input: $INPUT_DIR"
echo "Output: $OUTPUT_DIR"

# Run the MapReduce job
java -cp target/mapred-wordctr-1.0-SNAPSHOT.jar WordCountDriver "$INPUT_DIR" "$OUTPUT_DIR"

if [ $? -eq 0 ]; then
    echo ""
    echo "Job completed successfully!"
    echo "Results:"
    echo "========"
    cat "$OUTPUT_DIR/part-r-00000"
else
    echo "Job failed!"
    exit 1
fi
