plugins {
    id("com.gradle.plugin-publish") version "1.2.1"
    alias(libs.plugins.kotlin.jvm)
}

repositories {
    mavenCentral()
}

group = "io.github.urlshortenerasacxyz"
version = "1.0"

gradlePlugin {
    val buildlambdadeploymentzip by plugins.creating {
        id = "io.github.urlshortenerasacxyz.buildlambdadeploymentzip"
        displayName = "Build Lambda Deployment ZIP"
        description = "Generates a ZIP file structured for AWS Lambda requirements"
        website = "https://github.com/urlshortenerasacxyz/buildlambdadeploymentzip"
        vcsUrl = "https://github.com/urlshortenerasacxyz/buildlambdadeploymentzip"
        tags = listOf("aws", "lambda", "aws-lambda", "serverless", "aws-s3", "s3", "amazon-web-services", "buildlambdadeploymentzip", "lambdadeployment", "lambdazip")
        implementationClass = "io.github.urlshortenerasacxyz.BuildlambdadeploymentzipPlugin"
    }
}

