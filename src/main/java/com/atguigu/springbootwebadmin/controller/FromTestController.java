package com.atguigu.springbootwebadmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
public class FromTestController {
    @GetMapping("/form_layouts")
    public String form_layouts(){
        return "form/form_layouts";
    }

    /**
     * MultipartFile 自动封装上传过来的文件
     * @param email
     * @param username
     * @param headerImg
     * @param photos
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImg") MultipartFile headerImg,//@RequestPart适用于表单项的文件
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {//多文件使用MultipartFile[]
        log.info("上传的信息：email={},username={},headerImg={},photos={}",
                email,username,headerImg.getSize(),photos.length);

        //上传的文件再保存起来
        if(!headerImg.isEmpty()){
            String originalFilename = headerImg.getOriginalFilename();//拿到文件名
            headerImg.transferTo(new File("D:\\hhhhhh\\"+originalFilename));
        }
        if(photos.length>0){
            for (MultipartFile photo : photos) {
                if(!photo.isEmpty()){
                    String originalFilename = photo.getOriginalFilename();
                    photo.transferTo(new File("D:\\hhhhhh\\"+originalFilename));
                }
            }
        }
        return "main";//随便返回一个页面
    }
}
