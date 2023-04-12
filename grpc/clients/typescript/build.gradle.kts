tasks.register<Exec>("install") {
    commandLine("yarn")
}

tasks.register<Exec>("generateProto") {
    commandLine("sh", "generateProto.sh")
}

tasks.register<Exec>("buildWeb") {
    group = "build"
    commandLine("yarn", "compile:web")
}

tasks.register<Exec>("buildNode") {
    group = "build"
    commandLine("yarn", "compile:node")
}

tasks.register("build") {
    group = "build"
    dependsOn( "install", "generateProto", "buildWeb", "buildNode")
}

tasks.register<Exec>("EnvoyProxy") {
    group = "run"
    commandLine("sh", "runEnvoy.sh")
}

tasks.register<Exec>("NodeClient") {
    group = "run"
    commandLine("node", ".")
}

tasks.register<Exec>("WebClient") {
    group = "run"
    commandLine("open", "./index.html")
}