#!/bin/bash

project_path=${0%/*}
javac -d $project_path/out/production $(find $project_path/src -name "*.java")
