#!/bin/bash

javac -cp .:jl1.0.1.jar:mysql-connector-java-5.1.46-bin.jar $(find src -name "*.java")
java -cp .:src:jl1.0.1.jar:mysql-connector-java-5.1.46-bin.jar Frame.LoadPage