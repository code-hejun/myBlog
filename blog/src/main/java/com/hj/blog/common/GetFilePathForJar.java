package com.hj.blog.common;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class GetFilePathForJar {

    public static File getPath() throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) {
            path = new File("");
        }

        File upload = new File(path.getAbsolutePath(),"static/upload/");
        if(!upload.exists()) {
            upload.mkdirs();
        }

        return upload;
    }

}
