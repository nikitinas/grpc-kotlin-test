#!/usr/bin/env bash
cd "$(dirname "$0")"
mkdir -p src/generated
export PATH="node_modules/protoc-gen-grpc-web/bin:node_modules/protoc/protoc/bin:$PATH"
protoc -I=../.. protocol/src/main/proto/service.proto --js_out=import_style=commonjs:src/generated --grpc-web_out=import_style=typescript,mode=grpcwebtext:src/generated