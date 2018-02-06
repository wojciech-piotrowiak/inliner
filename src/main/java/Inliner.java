
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.MethodInfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Inliner {
    public static void main(String[] args) throws IOException {
        try (InputStream is = javax.swing.JComponent.class.getResourceAsStream("JComponent.class")) {
            ClassFile classFile = new ClassFile(new DataInputStream(is));
            for (Object mi : classFile.getMethods()) {
                if (mi instanceof MethodInfo) {
                    MethodInfo methodInfo = (MethodInfo) mi;
                    CodeAttribute ca = methodInfo.getCodeAttribute();
                    if(ca == null) continue; // abstract or native
                    int bLen = ca.getCode().length;
                    if (bLen > 300)
                        System.out.println(methodInfo.getName() + " " + methodInfo.getDescriptor() + ", " + bLen + " bytes");
                }

            }
        }
    }
}
