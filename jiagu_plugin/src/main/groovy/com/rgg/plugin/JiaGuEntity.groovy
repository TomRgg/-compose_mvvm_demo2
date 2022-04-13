package com.rgg.plugin

import com.android.builder.model.SigningConfig
import org.gradle.api.tasks.Input


class JiaGuEntity {
    @Input
    String name

    @Input
    String password

    @Input
    String fileJarPath

    @Input
    SigningConfig signingConfig

    @Input
    String toolPath

    String getToolPath() {
        return toolPath
    }

    void setToolPath(String toolPath) {
        this.toolPath = toolPath
    }

    SigningConfig getSigningConfig() {
        return signingConfig
    }

    void setSigningConfig(SigningConfig signingConfig) {
        this.signingConfig = signingConfig
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    String getFileJarPath() {
        return fileJarPath
    }

    void setFileJarPath(String fileJarPath) {
        this.fileJarPath = fileJarPath
    }
}