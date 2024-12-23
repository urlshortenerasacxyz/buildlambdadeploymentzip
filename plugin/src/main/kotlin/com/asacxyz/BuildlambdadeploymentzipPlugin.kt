package com.asacxyz

import BuildLambdaDeploymentZIPTask
import org.gradle.api.Project
import org.gradle.api.Plugin

class BuildlambdadeploymentzipPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("buildlambdadeploymentzip", BuildLambdaDeploymentZIPTask::class.java) {
        }
    }
}
