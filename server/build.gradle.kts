import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
}

val protobufVersion by properties
val grpcVersion by properties
val grpcKotlinVersion by properties
val coroutinesVersion by properties

dependencies {
    implementation(project(":kotlin-stub"))
    api(kotlin("stdlib-jdk8"))
    runtimeOnly("io.grpc:grpc-netty:$grpcVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$coroutinesVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

tasks.register<JavaExec>("KotlinServer") {
    dependsOn("classes")
    group = "run"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("nikitinas.grpc.hello.HelloServerKt")
}

val helloStartScripts = tasks.register<CreateStartScripts>("helloStartScripts") {
    mainClass.set("nikitinas.grpc.hello.HelloServerKt")
    applicationName = "hello-server"
    outputDir = tasks.named<CreateStartScripts>("startScripts").get().outputDir
    classpath = tasks.named<CreateStartScripts>("startScripts").get().classpath
}

tasks.named("startScripts") {
    dependsOn(helloStartScripts)
}