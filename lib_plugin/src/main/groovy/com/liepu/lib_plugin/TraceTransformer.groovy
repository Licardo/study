package com.liepu.lib_plugin

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import java.io.FileOutputStream;
import com.android.utils.FileUtils
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter


class TraceTransformer extends Transform {

    @Override
    String getName() {
        return "paofanTest"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        println('-------------------------start-----------------------------')

        transformInvocation.inputs.each {
//            println(">>>$it")
            it.directoryInputs.each { input ->
//                println(">>> $input")
                List<File> fileList = getAllFiles(input.file)
//                println(">>> ${fileList}")
                fileList.each {file ->
                    println(">>>${file.name}")
                    if (file.name.endsWith('.class') && !file.name.contains('Service') && !file.name.contains('FlutterWrapperActivity')) {
                        ClassReader classReader = new ClassReader(file.readBytes())
                        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                        ClassVisitor visitor = new LClassVisitor(classWriter)
                        classReader.accept(visitor, ClassReader.SKIP_DEBUG)
                        byte[] data = classWriter.toByteArray()

                        FileOutputStream fout = new FileOutputStream(file.absolutePath)
                        fout.write(data)
                        fout.close()
                    }
                }

                def file = transformInvocation.outputProvider.getContentLocation(input.name, input.contentTypes,
                        input.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(input.file, file)

            }
            //  !!!!!!!!!! !!!!!!!!!! !!!!!!!!!! !!!!!!!!!! !!!!!!!!!!
            //使用androidx的项目一定也注意jar也需要处理，否则所有的jar都不会最终编译到apk中，千万注意
            //导致出现ClassNotFoundException的崩溃信息，当然主要是因为找不到父类，因为父类AppCompatActivity在jar中
            it.jarInputs.each {
                jarInput ->
                    def dest = transformInvocation.outputProvider.getContentLocation(
                            jarInput.name,
                            jarInput.contentTypes,
                            jarInput.scopes,
                            Format.JAR
                    )
                    FileUtils.copyFile(jarInput.file, dest)
            }
        }

        println('---------------------------end-------------------------------')
    }

    private List<File> getAllFiles(File input) {
        List<File> files = input.listFiles()
        List<File> fs = new ArrayList<>()
        files.each {file ->
            if (file.isDirectory()) {
                // 添加
                fs.addAll(getAllFiles(file))
            } else {
                fs.add(file)
            }
        }
        return fs
    }
}