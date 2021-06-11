package com.liepu.lib_plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class LTracePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.extensions.create("methodConfig", ConfigInfo.class)
        def exten = project.extensions.findByType(AppExtension.class)
        exten.registerTransform(new TraceTransformer(project))
    }
}