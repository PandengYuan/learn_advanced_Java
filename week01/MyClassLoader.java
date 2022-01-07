package week01;


import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;


public class MyClassLoader extends ClassLoader{

    public static byte[] loadFile(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return buffer;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] bytes = new byte[0];
        try {
            bytes = loadFile("c:\\Hello.xlass");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i=0; i<bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }

        return defineClass(name, bytes,0,bytes.length);

    }


    public static void main(String[] args) throws Exception {

        Class clazz = new MyClassLoader().findClass("Hello");
        Object instance = clazz.newInstance();
        Method method = clazz.getMethod("hello");
        method.invoke(instance);



    }
}
