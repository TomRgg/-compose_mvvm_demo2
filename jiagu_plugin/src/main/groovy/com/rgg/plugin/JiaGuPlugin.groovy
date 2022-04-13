package com.rgg.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import com.android.builder.model.SigningConfig
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 自定义加固插件
 */
class JiaGuPlugin implements Plugin<Project> {

    /**
     * 该方法在别的gradle文件中 导入如 plugins{id: xxxxxx} 后，在该gradle文件运行时，即使触发以下方法
     * @param project
     */
    @Override
    void apply(Project project) {
        //1.从目标的gradle文件获取加固{}参数
        JiaGuEntity jiaGuEntity = project.getExtensions().create("jiagu", JiaGuEntity.class)
        //在gradle配置完成后回调获取参数，不然获取参数为null
        project.afterEvaluate {
            //2.从目标的gradle文件获取签名{}参数
            AppExtension android = project.getExtensions().android
            android.applicationVariants.all { ApplicationVariant variant ->
                //拿到对应变体的（debug，release）签名信息
                SigningConfig signingConfig = variant.signingConfig
                //拿到apk
                variant.outputs.all {
                    BaseVariantOutput output ->
                        File apk = output.outputFile
                        //创建加固任务 根据Build Variant 类型数，创建对应的 例如：jiagu Debug Task
                        ////在AndroidStudio 右侧栏上Gradle 生成对应的task
                        JiaGuTask task = project.getTasks().create("jiagu${variant.baseName.capitalize()}", JiaGuTask.class)
//                        task.entity = jiaGuEntity
//                        task.signingConfig = signingConfig
                        task.setEntity(jiaGuEntity)
                        task.setSigningConfig(signingConfig)
                        task.setApk(apk)
                }

            }
        }
    }
}