package training.runner;

import org.apache.log4j.Logger;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import training.api.IPlugin;
import training.loader.CustomClassLoader;
import training.stub.StubPlugin;

/**
 * Created by Andrei_Pimenau on 29.9.14.
 */
public class Runner {

    /**
     * Files to read extension.
     */
    private static String EXT = ".class";
    /**
     * Directory to scan.
     */
    private static String DIR = "D:/aps";

    private static ClassLoader cl = new CustomClassLoader();
    private static Logger logger = Logger.getRootLogger();

    /**
     * Runner method.
     *
     * @param args args.
     * @throws ReflectiveOperationException exception.
     */
    public static void main(String[] args) throws ReflectiveOperationException {
        logger.info("Program started");
        boolean loop = true;
        while (loop) {
            List<IPlugin> plugins = loadAll();
            printMenu(plugins);
            int chosen = getMenuPoint();
            if (chosen < plugins.size()) {
                plugins.get(chosen).exec();
            } else {
                loop = false;
            }
        }
        logger.info("Program finished");
    }

    /**
     * Makes file name by class name.
     *
     * @param name class name.
     * @return file name.
     */
    public static String getFileName(String name) {
        return Runner.DIR + FileSystems.getDefault().getSeparator() + name + Runner.EXT;
    }

    /**
     * Wait for user choose and return number of chosen menu point.
     *
     * @return menu point.
     */
    private static int getMenuPoint() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextInt();
    }

    /**
     * Print menu.
     *
     * @param plugins list of plugins.
     */
    private static void printMenu(List<IPlugin> plugins) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--------------------------\n");
        int i = 0;
        for (IPlugin plugin : plugins) {
            sb.append(i++).append(".").append(plugin.getName()).append("\n");
        }
        sb.append(i).append(".exit\n--------------------------\nPlease, select from menu(enter a number):");
        logger.info(sb.toString());
    }

    /**
     * Load all plugins.
     *
     * @return list of loaded plugins.
     * @throws ReflectiveOperationException exception.
     */
    private static List<IPlugin> loadAll() throws ReflectiveOperationException {
        List<IPlugin> plugins = new LinkedList<IPlugin>();
        plugins.add(new StubPlugin());
        List<File> files = getFiles();
        for (File file : files) {
            String name = trimExt(file.getName());
            Class c = Class.forName(name, true, cl);
            @SuppressWarnings("unchecked")
            IPlugin plugin = (IPlugin) c.newInstance();
            plugins.add(plugin);
        }
        return plugins;
    }

    /**
     * Get class name by file name.
     *
     * @param name file name.
     * @return class name.
     */
    private static String trimExt(String name) {
        return name.substring(0, name.length() - EXT.length());
    }

    /**
     * Scan dir and find all plugins.
     *
     * @return file list.
     */
    private static List<File> getFiles() {
        List<File> files = Collections.emptyList();
        File dir = new File(DIR);
        if (dir.isDirectory()) {
            File[] dirFiles = dir.listFiles();
            if (dirFiles != null) {
                files = new ArrayList<File>(Arrays.asList(dirFiles));
                Iterator<File> iterator = files.iterator();
                while (iterator.hasNext()) {
                    File next = iterator.next();
                    if (!next.getPath().endsWith(EXT)) {
                        iterator.remove();
                    }
                }
            }
        }
        return files;
    }

}
