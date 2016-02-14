package com.example

import io.spring.gradle.dependencymanagement.DependencyManagementExtension
import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.ClosureBackedAction

class MyPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        if(project.name.matches("[a-z]*-service")){
            println("Skipping dependency managament plugin for ${project.name}")
            return
        }else {
            println("Added dependency managament plugin for ${project.name}")
            DependencyManagementPlugin dependencyManagementPlugin = new DependencyManagementPlugin()
            dependencyManagementPlugin.apply(project)
        }
        project.extensions.configure(DependencyManagementExtension, new ClosureBackedAction<DependencyManagementExtension>({
            imports {
                mavenBom("org.springframework.cloud:spring-cloud-dependencies:Brixton.BUILD-SNAPSHOT")
            }
        }))
    }
}
