package com.urise.webapp;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainFile {

    public static void main(String[] args) {
        File file = new File(".ignore");
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("./src/com/urise/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

//        FileInputStream fis = null;
//        try {
//             fis = new FileInputStream(file);
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (fis != null){
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        try (FileInputStream fis = new FileInputStream(file)){
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        File rootDir = new File("./src");
        getFileRecursive(rootDir);


    }

    private static void getFileRecursive(File file) {
        for (File f : Objects.requireNonNull(file.listFiles())) {
            if (f.isDirectory()) {
                getFileRecursive(f.getAbsoluteFile());
            } else System.out.println(f.getAbsolutePath());
        }
    }
}
