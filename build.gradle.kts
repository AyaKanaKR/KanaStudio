plugins {
    kotlin("jvm") version "2.0.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "kr.awr.kdv.KanaStudio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.build {
    dependsOn("shadowJar")
}

// Git 커밋 및 푸시 작업 정의
val GitBuild by tasks.creating {
    group = "versioning"
    description = "Commits and pushes changes to the GitHub repository"

    doLast {
        // Git 명령어 목록
        val commands = listOf(
            "git add ."
        )

        commands.forEach { command ->
            println("Executing: $command")
            val process = Runtime.getRuntime().exec(command)
            process.waitFor()
            val exitCode = process.exitValue()
            println("Command finished with exit code: $exitCode")

            if (exitCode != 0) {
                throw GradleException("Command failed: $command")
            }
        }

        // 변경 사항이 있을 때만 커밋 및 푸시
        val commitProcess = Runtime.getRuntime().exec("git commit -m 'Automated commit after build'")
        commitProcess.waitFor()
        val commitExitCode = commitProcess.exitValue()
        if (commitExitCode == 0) {
            val pushProcess = Runtime.getRuntime().exec("git push origin master") // 적절한 브랜치 이름 사용
            pushProcess.waitFor()
            println("Push finished with exit code: ${pushProcess.exitValue()}")
        } else {
            println("No changes to commit.")
        }
    }
}


// Git pull 명령어 추가
val pullProcess = Runtime.getRuntime().exec("git pull origin master") // 적절한 브랜치 이름 사용
pullProcess.waitFor()
println("Pull finished with exit code: ${pullProcess.exitValue()}")


// 빌드 완료 후 자동 실행
tasks.build {
    finalizedBy(GitBuild)
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}
