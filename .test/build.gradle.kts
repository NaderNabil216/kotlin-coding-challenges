import com.igorwojda.challenge.utils.TestUtils
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.22"
    id("com.adarshr.test-logger") version "3.2.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("org.amshove.kluent:kluent:1.72")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.9.22"))
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("failed")

        // log full stacktrace of failed test (assertion library descriptive error)
        exceptionFormat = TestExceptionFormat.FULL
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "21"
}

task("generateTests") {
    group = "verification"

    TestUtils.generateTestFiles(project)
}
