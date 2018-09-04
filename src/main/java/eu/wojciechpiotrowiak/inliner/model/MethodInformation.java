package eu.wojciechpiotrowiak.inliner.model;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MethodInformation {
    private final String classPath;
    private final String methodName;
    private final String methodSignature;
    private final int bytecodeLength;

    public MethodInformation(String classPath, String methodName, String methodSignature, int bytecodeLength) {
        this.classPath = classPath;
        this.methodName = methodName;
        this.methodSignature = methodSignature;
        this.bytecodeLength = bytecodeLength;
    }

    public String getClassPath() {
        return classPath;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getMethodSignature() {
        return methodSignature;
    }

    public int getBytecodeLength() {
        return bytecodeLength;
    }

    @Override
    public String toString() {
        return "MethodInformation{" +
                "classPath='" + classPath + '\'' +
                ", methodName='" + methodName + '\'' +
                ", methodSignature='" + methodSignature + '\'' +
                ", bytecodeLength=" + bytecodeLength +
                '}';
    }
}
