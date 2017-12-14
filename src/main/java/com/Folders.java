package com;

import org.apache.commons.io.FileUtils;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by Elad Salti on 03-Dec-17.
 */
public class Folders {
    public static void main(String[] args) throws IOException {

        File source = new File(args[0]);
        File[] files = new File(args[1]).listFiles();
        int count = 0;

        deleteFiles(files, count);
        addJar(files, count,source);
        getJarName(source);
        changeName(files, count, source);
    }

    public static String getJarName(File pathOfJarFile){
        File[] files = pathOfJarFile.listFiles();
        return files[0].getName();
    }

    public static void deleteFiles(File[] files, int count) throws IOException {
        for (File file : files) {
            if (file.isDirectory() && count != 1) {
                System.out.println("Directory: " + file.getName());
                deleteFiles(file.listFiles(), 1); // Calls same method again.
            } else {
                if (file.getName().endsWith(".jar"))
                    file.delete();
                System.out.println("File: " + file.getName());
            }
        }
    }

    public static void addJar(File[] files, int count, File source) throws IOException {
        for (File file : files) {
            if (file.isDirectory() && count != 1) {
                System.out.println("Directory: " + file.getName());
                FileUtils.copyDirectory(source, file);
                addJar(file.listFiles(), 1, source); // Calls same method again.
            } else {
                System.out.println("File: " + file.getName());
            }

        }
    }

    public static void changeName(File[] files, int count, File source) throws IOException {
        for (File file : files) {
            if (file.isDirectory() && count != 1) {
                System.out.println("Directory: " + file.getName());
                changeName(file.listFiles(), 1, source); // Calls same method again.
            } else {
                if (file.getName().endsWith("Command.txt")) {
                    List<String> lines = Files.readAllLines(file.toPath());
                    String[] commandArguments = lines.get(0).split(" ");
                    commandArguments[2] = getJarName(source);
                    String str = String.join(" ", commandArguments);
                    lines.set(0, str);
                    Files.write(file.toPath(), lines);
                }
            }
        }
    }
}
