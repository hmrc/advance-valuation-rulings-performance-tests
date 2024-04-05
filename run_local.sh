#!/bin/bash

./run_format_and_deps.sh

sbt -DrunLocal=true Gatling/test
