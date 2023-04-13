#!/usr/bin/env bash
docker build -t envoy:v1 .
docker stop envoy
docker rm envoy
docker run -d --name envoy -p 9902:9902 -p 8088:8088 envoy:v1