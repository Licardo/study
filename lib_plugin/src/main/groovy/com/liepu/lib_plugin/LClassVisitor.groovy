package com.liepu.lib_plugin

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class LClassVisitor extends ClassVisitor implements Opcodes{

    private ClassVisitor classVisitor
    private String methodName;

    LClassVisitor(String methodName, ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor)
        this.classVisitor = classVisitor
        this.methodName = methodName
    }

    @Override
    void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        classVisitor.visit(version, access, name, signature, superName, interfaces)
//        super.visit(version, access, name, signature, superName, interfaces)
    }


    @Override
    MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(access, name, descriptor, signature, exceptions)
        println(">>> $name")
        if ("<init>" == name || methodName != name || methodVisitor == null) {
            return methodVisitor
        }
        methodVisitor = new LMethodVisitor(methodVisitor)
        return methodVisitor
    }

    @Override
    void visitEnd() {
        super.visitEnd()
    }

    static class LMethodVisitor extends MethodVisitor implements Opcodes{

        private MethodVisitor methodVisitor;

        LMethodVisitor(MethodVisitor methodVisitor) {
            super(Opcodes.ASM5, methodVisitor)
            this.methodVisitor = methodVisitor;
        }

        @Override
        void visitCode() {
            super.visitCode()
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(20, label0);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;", false);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitInsn(Opcodes.AALOAD);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StackTraceElement", "getMethodName", "()Ljava/lang/String;", false);
            methodVisitor.visitVarInsn(Opcodes.ASTORE, 3);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(21, label1);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 3);
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "android/os/Trace", "beginSection", "(Ljava/lang/String;)V", false);
        }

        @Override
        void visitInsn(int opcode) {
            if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW) {
                Label label3 = new Label();
                methodVisitor.visitLabel(label3);
                methodVisitor.visitLineNumber(23, label3);
                methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "android/os/Trace", "endSection", "()V", false);
                Label label4 = new Label();
                methodVisitor.visitLabel(label4);
                methodVisitor.visitLineNumber(24, label4);
                methodVisitor.visitInsn(Opcodes.RETURN);
            }
            methodVisitor.visitInsn(opcode)
        }
    }
}