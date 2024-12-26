# buildlambdadeploymentzip

## Default Gradle Application Setup

When creating a Gradle application via CLI, the `application` plugin is included by default. Running a build generates a ZIP file: `./app/build/distributions/app.zip`

Extracting it gives: `/app/lib/{JARS}`

This structure works locally but is incompatible with AWS Lambda.

## Build Lambda Deployment ZIP

### Purpose
This plugin adjusts the ZIP structure for AWS Lambda deployment.

### Usage
Run: `./gradlew buildLambdaDeploymentZIP`

The ZIP file will be generated in the project root: `./app.zip`

Extracting it gives: `/lib/{JARS}`

## AWS Lambda Upload

1. Add the **Build Lambda Deployment ZIP** plugin to your project.
2. Run: `./gradlew buildLambdaDeploymentZIP`
3. Upload `app.zip` to AWS Lambda.
