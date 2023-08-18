import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("fabric-loom") version "1.3.9"
	kotlin("jvm") version "1.9.0"
	id("maven-publish")
}

base.archivesName.set(properties["archives_base_name"] as String)
version = properties["mod_version"]!!
group = properties["maven_group"]!!

repositories {
	maven("https://maven.terraformersmc.com/releases")
	maven("https://maven.brokenfuse.me/releases")
}

dependencies {
	minecraft("com.mojang:minecraft:${properties["minecraft_version"]}")
	mappings("net.fabricmc:yarn:${properties["yarn_mappings"]}:v2")
	modImplementation("net.fabricmc:fabric-loader:${properties["loader_version"]}")

	modImplementation("net.fabricmc.fabric-api:fabric-api:${properties["fabric_version"]}")
	modImplementation("net.fabricmc:fabric-language-kotlin:${properties["fabric_kotlin_version"]}")
//	modImplementation("org.teamvoided:voidlib:${properties["voidlib_version"]}")

	modImplementation("com.terraformersmc:modmenu:${properties["modmenu_version"]}")

	// modImplementation("net.fabricmc.fabric-api:fabric-api-deprecated:${project.properties["fabric_version"]}")
}

tasks {
	processResources {
		inputs.property("version", project.version)
		filteringCharset = "UTF-8"

		filesMatching("fabric.mod.json") {
			expand(mapOf("version" to project.version))
		}
	}

	val targetJavaVersion = 17
	withType<JavaCompile> {
		options.encoding = "UTF-8"
		options.release.set(targetJavaVersion)
	}

	withType<KotlinCompile> {
		kotlinOptions.jvmTarget = targetJavaVersion.toString()
	}

	java {
		toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.toVersion(targetJavaVersion).toString()))
		withSourcesJar()
	}

	jar {
		from("LICENSE") {
			rename { "${it}_${base.archivesName}" }
		}
	}
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			from(components["java"])
		}
	}

	repositories {}
}
