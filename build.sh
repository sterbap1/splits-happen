#!/bin/bash

mvn clean compile assembly:single

if [ ! -f ./target/splitshappen-jar-with-dependencies.jar ]; then
  echo "Build failed"
  exit -1
fi

mv ./target/splitshappen-jar-with-dependencies.jar ./target/splitshappen.jar

echo "to run, use 'java -jar ./target/splitshappen.jar <input>'"
