package com.rgg.plugin

import com.android.builder.model.SigningConfig
import org.gradle.api.DefaultTask
import org.gradle.api.file.FileTree
import org.gradle.api.file.FileTreeElement
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction

/**
 * 因为360加固需要取当前文件java/bin相关的工具签名，所以若需要自动加固，则需要把java文件夹放到app目录下
 */
class JiaGuTask extends DefaultTask {

    //gradle 7.0需要的注解
    @Input
    JiaGuEntity entity

    @Input
    SigningConfig signingConfig

    @InputFile
    File apk

    JiaGuTask() {
        //task视图中自定义分组
        group = "jiagu"
    }

    //在AndroidStudio 右侧栏上Gradle 点击对应的task，则执行该方法
    @TaskAction
    def run() {
        println("JiaGuTask, TaskAction")
        //签名为空，则不加固
        if (!signingConfig) {
            return
        }
        println("apk地址：${apk.absolutePath}")
        println("输出地址：${apk.parent}")
        println("entity.getToolPath()：${entity.getToolPath()}")
        println("project.getRootDir().getAbsolutePath():${project.getRootDir().getAbsolutePath()}")
//        def originPath = "${entity.getToolPath()}\\origin.apk"
//        //复制input的apk到指定有tools工具下的文件夹里，
//        assert copy(apk.absolutePath, originPath)
        //复制jiagu/java文件夹到app中
//        assert copy("${entity.getToolPath()}\\java", "${project.getRootDir().getAbsolutePath()}\\app\\java\\")
//            "cmd copy ${entity.getToolPath()}\\java ${project.getRootDir().getAbsolutePath()}\\app".execute()
//        project.exec {
        //xcopy D:\RggStudy\technoogy_code\source_code\live_project\jiagu_plugin\tools\jiagu\java /s D:\RggStudy\technoogy_code\source_code\live_project\app\java\
//            def process = "cmd xcopy D:\\RggStudy\\technoogy_code\\source_code\\live_project\\jiagu_plugin\\tools\\jiagu\\java\\ /s D:\\RggStudy\\technoogy_code\\source_code\\live_project\\app\\java\\".execute()
//            println(process.text)
//            it.commandLine('cmd', 'xcopy',
//                    "${entity.getToolPath()}\\java",
//                    "${project.getRootDir().getAbsolutePath()}\\app\\java\\",
//                    '/s')
//            it.commandLine 'cmd', 'xcopy', "${entity.getToolPath()}/java", "${project.getRootDir().getAbsolutePath()}/app/java/", '/s'
//            it.commandLine 'cmd', 'xcopy', "D:\\RggStudy\\technoogy_code\\source_code\\live_project\\jiagu_plugin\\tools\\jiagu\\java", '/s', "D:\\RggStudy\\technoogy_code\\source_code\\live_project\\app\\java\\"

//            project.fileTree('D:\\RggStudy\\笔记\\插件加固\\360\\360jiagubao_windows_64\\jiagu\\java') {
//                FileTree fileTree ->
//                    fileTree.visit {
//                        FileTreeElement element ->
//                            copy {
//                                from element.file
//                                into 'D:\\RggStudy\\technoogy_code\\source_code\\live_project\\app\\java\\'
//                            }
//                    }
//            }

//        }
        //调用加固的 命令行工具
        //首次使用必须先登录, username为您的360用户名，pasword为您的用户登录密码
        //执行 java -jar jiagu.jar -login <username> <password>
        project.exec {
            it.commandLine("${entity.getToolPath()}\\java\\bin\\java", '-jar',
                    entity.fileJarPath,
                    '-login',
                    entity.name,
                    entity.password)
        }

        //导入签名信息 keystore_path为密钥文件路径，keystore_password为密钥密码，alias为别名，alias_password为别名密码
        //java -jar jiagu.jar -importsign <keystore_path> <keystore_password> <alias> <alias_password>
//        println("signingConfig.storeFile.absolutePath:${signingConfig.storeFile.absolutePath}")
//        println("signingConfig.storePassword:${signingConfig.storePassword}")
//        println("signingConfig.keyAlias:${signingConfig.keyAlias}")
//        println("signingConfig.keyPassword:${signingConfig.keyPassword}")
        project.exec {
//            it.commandLine('cmd', 'cd', entity.fileJarPath)
            it.commandLine("${entity.getToolPath()}\\java\\bin\\java",
                    '-jar',
                    entity.fileJarPath,
                    '-importsign',
                    signingConfig.storeFile.absolutePath,
                    signingConfig.storePassword,
                    signingConfig.keyAlias,
                    signingConfig.keyPassword)
        }

        //加固命令。inputAPKpath为待加固APK的路径，outputPath为保存加固后APK的路径
        //java -jar jiagu.jar -jiagu <inputAPKpath> <outputPath>
        //[-autosign]【可选】启用自动签名，需要先保存签名配置信息
        //[-automulpkg]【可选】启用自动生成多渠道包，需要先保存多渠道配置信息
        //[-pkgparam mulpkg_filepath]【可选】启用自定义文件生成多渠道包，需要传入多渠道包配置文件
        project.exec {
//            it.commandLine('cmd', 'cd', entity.fileJarPath)
            it.commandLine("${entity.getToolPath()}\\java\\bin\\java",
                    '-jar',
                    entity.fileJarPath,
                    '-jiagu',
                    apk.absolutePath,
//                    originPath,
                    apk.parent,
                    '-autosign'
            )
        }

    }

    JiaGuEntity getEntity() {
        return entity
    }

    void setEntity(JiaGuEntity entity) {
        this.entity = entity
    }

    SigningConfig getSigningConfig() {
        return signingConfig
    }

    void setSigningConfig(SigningConfig signingConfig) {
        this.signingConfig = signingConfig
    }

    File getApk() {
        return apk
    }

    void setApk(File apk) {
        this.apk = apk
    }

    //复制
    def copy(String src, String dest) {
        def f = new File(src)
        def list = f.list(new FilenameFilter() {
            @Override
            boolean accept(File dir, String name) {
                return true
            }
        })

        def desk = new File(dest)
        if (!desk.exists()) {
            desk.mkdirs()
        }

        list.each {
            File f1 = new File(src + "/" + it)
            File f2 = new File(dest + "/" + it)

            if (!f2.exists()) {
                f2.createNewFile()
            }
            f1.withDataInputStream {
                input ->
                    f2.withDataOutputStream {
                        output -> output << input
                    }
            }
        }
        return true
    }

}