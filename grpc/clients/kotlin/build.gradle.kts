plugins {
    application
    kotlin("jvm")
}

val grpcVersion by properties
val coroutinesVersion by properties

dependencies {
    implementation(project(":grpc:kotlin-stub"))
    runtimeOnly("io.grpc:grpc-netty:$grpcVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$coroutinesVersion")
}

tasks.register<JavaExec>("KotlinClient") {
    dependsOn("classes")
    group = "run"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("nikitinas.grpc.hello.HelloClientKt")
}

val helloClientStartScripts = tasks.register<CreateStartScripts>("helloClientStartScripts") {
    mainClass.set("nikitinas.grpc.hello.HelloClientKt")
    applicationName = "hello-client"
    outputDir = tasks.named<CreateStartScripts>("startScripts").get().outputDir
    classpath = tasks.named<CreateStartScripts>("startScripts").get().classpath
}

tasks.named("startScripts") {
    dependsOn(helloClientStartScripts)
}
