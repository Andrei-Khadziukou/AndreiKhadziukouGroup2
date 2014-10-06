package training.loader;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import training.runner.Runner;

/**
 * Created by Andrei_Pimenau on 30.9.14.
 */
public class CustomClassLoader extends ClassLoader {

    private Logger logger = Logger.getRootLogger();

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        logger.debug("method 'findClass' by name [" + name + "]");
        byte[] classFile = loadFile(Runner.getFileName(name));
        return defineClass(name, classFile, 0, classFile.length);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        logger.debug("method 'loadClass' by name [" + name + "]");
        return super.loadClass(name);
    }

    private byte[] loadFile(String fileName) {
        File file = new File(fileName);
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new ClassCastException(e.getMessage());
        }
    }

}
