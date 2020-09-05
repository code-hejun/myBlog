package com.hj.blog.common;

import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GetFilePath {

    public static List<String> getFileName() throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        //String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/blogImg";
        //File f = new File(path);

        File f = GetFilePathForJar.getPath();

        if (!f.exists()) {
            //System.out.println(path + " not exists");
            System.out.println(" not exists");
            return list;
        }

        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
                list.add(fs.getName() + " [目录]");
                System.out.println(fs.getName() + " [目录]");
            } else {
                list.add(fs.getName());
                System.out.println(fs.getName());
            }
        }
        return list;
    }


}
