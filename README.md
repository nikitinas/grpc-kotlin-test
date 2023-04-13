# Kotlin/Typescript gRPC example

This sample project demonstrates gRPC communication between Kotlin JVM server and the following clients:
- Kotlin JVM
- Typescript Node.js
- Typescript Web

## Prerequisites

* Docker
* Node.js

### For Windows

* Git Bash

## Building

Run `gradle build` task

## Running

1. Start server: `gradle KotlinServer`
2. Start Envoy proxy in Docker container: `gradle EnvoyProxy`
3. Run clients:
   * Kotlin: `gradle KotlinClient`
   * Node.js: `gradle NodeClient`
   * Web: `gradle WebClient`