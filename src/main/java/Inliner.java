
import eu.wojciechpiotrowiak.inliner.model.MethodInformation;
import eu.wojciechpiotrowiak.inliner.model.ProjectFactory;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.MethodInfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Inliner {
    public static void main(String[] args) throws IOException {

        String path = Paths.get("..")+"/inliner/target/classes/";
        Files.walk(Paths.get(path), FileVisitOption.FOLLOW_LINKS)
                .filter(file->file.toFile().isFile())
                .filter(file->file.toFile().getName().endsWith(".class")).forEach(file->{
        try (InputStream inputStream = Files.newInputStream(file)) {
            parseFile(inputStream, file.toAbsolutePath().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        });
    }

    private static void parseFile(InputStream inputStream, String path) throws IOException {
        ClassFile classFile = new ClassFile(new DataInputStream(inputStream));
        List<MethodInformation> information = new ArrayList<>();
        for (Object mi : classFile.getMethods()) {
            if (mi instanceof MethodInfo) {
                MethodInfo methodInfo = (MethodInfo) mi;
                CodeAttribute ca = methodInfo.getCodeAttribute();
                if (ca == null) continue; // abstract or native
                int bLen = ca.getCode().length;
                MethodInformation methodInformation = new MethodInformation(path, methodInfo.getName(), methodInfo.getDescriptor(), bLen);
                // System.out.println(methodInfo.getName() + " " + methodInfo.getDescriptor() + ", " + bLen + " bytes");
                information.add(methodInformation);
            }

        }

        ProjectFactory.buildFromMethodInfo(information);
    }
}
