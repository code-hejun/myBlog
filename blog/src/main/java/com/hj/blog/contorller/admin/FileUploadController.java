package com.hj.blog.contorller.admin;

import com.hj.blog.common.GetFilePath;

import com.hj.blog.common.GetFilePathForJar;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Controller()
@RequestMapping("/admin")
public class FileUploadController {

    @RequestMapping("/up")
    public  String page(Model model) throws FileNotFoundException {
        List<String> list = GetFilePath.getFileName();
        model.addAttribute("list",list);
        return "admin/imgUpload";
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam("img") MultipartFile file, Model model) throws FileNotFoundException {

        if(file.isEmpty()){
            model.addAttribute("msg","文件为空");
            return "admin/imgUpload";
        }

        //String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/blogImg/";
        //System.out.println(path);

        //创建输入输出流
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            //获取文件的输入流
            inputStream = file.getInputStream();
            //获取上传时的文件名
            String fileName = file.getOriginalFilename();
            //注意是路径+文件名
            //File targetFile = new File(path + fileName);
            File targetFile = new File(GetFilePathForJar.getPath(),fileName);
            //如果之前的 String path = "d:/upload/" 没有在最后加 / ，那就要在 path 后面 + "/"

            //判断文件父目录是否存在
            if(!targetFile.getParentFile().exists()){
                //不存在就创建一个
                targetFile.getParentFile().mkdir();
            }
            //获取文件的输出流
            outputStream = new FileOutputStream(targetFile);
            //最后使用资源访问器FileCopyUtils的copy方法拷贝文件
            FileCopyUtils.copy(inputStream, outputStream);
            /*参数是通过源码
                public static int copy(InputStream in, OutputStream out) throws IOException {
                    ......
                }
           而得知的*/

            //告诉页面上传成功了
            model.addAttribute("msg", "上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            //出现异常，则告诉页面失败
            model.addAttribute("msg", "上传失败");
        } finally {
            //无论成功与否，都有关闭输入输出流
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        List<String> list = GetFilePath.getFileName();
        model.addAttribute("list",list);
        return "admin/imgUpload";
    }


    public static void main(String[] args) throws FileNotFoundException {
       /* File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) {
            path = new File("");
        }


        File upload = new File(path.getAbsolutePath(),"static/upload/");
        if(!upload.exists()) {
            upload.mkdirs();
        }*/
        //File upload = new File(path.getAbsolutePath(),"static/upload/test.jpg");
    }

}
