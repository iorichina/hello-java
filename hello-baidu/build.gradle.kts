plugins {
    id("java")
}

group = "iorichina.hellojava"
version = "0.0.1-beta"

repositories {
    maven {
        url = uri("http://maven.aliyun.com/nexus/content/groups/public/")
    }
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.json/json
    implementation("org.json:json:20230618")
    // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    // https://mvnrepository.com/artifact/org.apache.poi/poi
    implementation("org.apache.poi:poi:5.2.3")
    // https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
    implementation("org.apache.poi:poi-ooxml:5.2.3")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}