import nikitinas.grpc.hello.gradle.tasks.*
import com.github.gradle.node.yarn.task.*

plugins {
    id("com.github.node-gradle.node") version "3.5.1"
}

tasks.register<BashExec>("generateProto") {
    commandLine("generateProto.sh")
    dependsOn("yarn")
}

tasks.register<YarnTask>("buildWeb") {
    group = "build"
    args.add("compile:web")
    dependsOn("generateProto")
}

tasks.register<YarnTask>("buildNode") {
    group = "build"
    args.add("compile:node")
    dependsOn("generateProto")
}

tasks.register<YarnTask>("watchWeb") {
    group = "build"
    args.add("watch:web")
}

tasks.register<YarnTask>("watchNode") {
    group = "build"
    args.add("watch:node")
}

tasks.register("build") {
    group = "build"
    dependsOn("buildWeb", "buildNode")
}

tasks.register<BashExec>("EnvoyProxy") {
    group = "run"
    commandLine("runEnvoy.sh")
}

tasks.register<Exec>("NodeClient") {
    group = "run"
    commandLine("node", ".")
}

tasks.register<OpenBrowser>("WebClient") {
    group = "run"
    commandLine("./index.html")
}