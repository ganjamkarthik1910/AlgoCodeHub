plugins {
    java
}

group = "com.algocodehub"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

sourceSets {
    main {
        java {
            srcDirs("../../src")
        }
    }
}

tasks.jar {
    archiveBaseName.set("algocodehub")
    archiveClassifier.set("lib")
    manifest {
        attributes(
            "Implementation-Title" to "AlgoCodeHub",
            "Implementation-Version" to project.version,
            "Automatic-Module-Name" to "com.algocodehub.dsa"
        )
    }
}

tasks.register<Jar>("fatJar") {
    group = "build"
    description = "Runnable JAR — AlgoPlayground (pass -Pmain=program.DSPlayground for DS demos)"
    archiveClassifier.set("demo")
    archiveBaseName.set("algocodehub")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    dependsOn(tasks.classes)

    val mainClass = project.findProperty("main")?.toString() ?: "program.AlgoPlayground"

    manifest {
        attributes(
            "Main-Class" to mainClass,
            "Implementation-Title" to "AlgoCodeHub",
            "Implementation-Version" to project.version
        )
    }

    from(sourceSets.main.get().output)
}

tasks.register<JavaExec>("runDS") {
    group = "application"
    description = "Run DSPlayground"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("program.DSPlayground")
}

tasks.register<JavaExec>("runAlgo") {
    group = "application"
    description = "Run AlgoPlayground (gradle runAlgo -Pargs='search')"
    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("program.AlgoPlayground")
    if (project.hasProperty("args")) {
        args = (project.property("args") as String).split(" ").filter { it.isNotEmpty() }
    }
}
