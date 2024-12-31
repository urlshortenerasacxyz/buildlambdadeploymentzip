package io.github.urlshortenerasacxyz

import BuildLambdaDeploymentZIPTask
import org.gradle.api.Project
import org.gradle.api.Plugin

class BuildlambdadeploymentzipPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        val buildLambdaDeploymentZIPTask = project.tasks.register("buildlambdadeploymentzip", BuildLambdaDeploymentZIPTask::class.java) {}
        buildLambdaDeploymentZIPTask.configure {
            it.dependsOn("build")
        }
    }
}
