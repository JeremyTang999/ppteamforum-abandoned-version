package com.ppteam.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {
	
	/*@RequestMapping("/test")
	public String test(){
		return "test";
	}
	
	@RequestMapping("/register")
	public String testReg(){
		return "register";
	}
	
	@RequestMapping("/")
	public String testIndex(){
		return "index";
	}*/

	@RequestMapping(value="/uploadtest",method=RequestMethod.POST)
	public String test(@RequestParam("imagefile") MultipartFile file){
		if(!file.isEmpty()){
			try {
				String dirname="target/classes/static/img/";
				String time=Calendar.getInstance().getTimeInMillis()+"";
				String imgname=dirname+time;
				BufferedOutputStream out=
						new  BufferedOutputStream(
								new FileOutputStream(
										new File(imgname)));
				File dir=new File(dirname);
				if(!dir.exists()){
					dir.mkdir();
				}
				out.write(file.getBytes());
				out.flush();
				out.close();
				return "img\\"+time;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "fail";
			}			
		}
		else{
			return "empty";
		}
	}
}
