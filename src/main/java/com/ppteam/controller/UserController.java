package com.ppteam.controller;



import java.io.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ppteam.dao.UserDao;
import com.ppteam.entity.Role;
import com.ppteam.entity.User;
import com.ppteam.json.*;
import com.ppteam.service.*;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserDao userDao;
	
	/*@Autowired
	private UserDao userDao;*/

	@RequestMapping(value="/registerinfo",method=RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody RegisterInfo info){
		System.out.println(info.getUsername());
		userService.register(info);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/username_available",method=RequestMethod.POST)
	public String checkUsername(@RequestParam("username") String username){
		boolean t=userService.usernameAvailable(username);
		if(t){
			return "available";
		}
		else{
			return "unavailable";
		}
	}
	
	@RequestMapping(value="/basicinfo",method=RequestMethod.GET)
	public ResponseEntity<BasicInfo> getBasicInfo(){
		
		String username=getUsername();
		
		if(username==null)
			return new ResponseEntity<BasicInfo>(HttpStatus.FORBIDDEN);
		else{
			BasicInfo bi=userService.getBasicInfo(username);
			return new ResponseEntity<BasicInfo>(bi,HttpStatus.OK);
		}
			
		
	}
	
	@RequestMapping(value="/userinfo",method=RequestMethod.GET)
	public UserInfo getUserinfo(@RequestParam("id")int id){
		return userService.getUserInfo(id);
	}
	
	@RequestMapping(value="/userinfo",method=RequestMethod.POST)
	public ResponseEntity<?> setUserinfo(@RequestBody UserInfo info){
		boolean b=userService.setUserInfo(info);
		return new ResponseEntity<>(
				b ? HttpStatus.OK :HttpStatus.FORBIDDEN);
		/*if(b){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}*/
		
	}
	
	@RequestMapping(value="/securityinfo",method=RequestMethod.POST)
	public ResponseEntity<?> securityInfo(SecurityInfo info){
		/*boolean t=userService.setSecurityInfo(info);
		return new ResponseEntity<>(
				t ? HttpStatus.OK : HttpStatus.FORBIDDEN);
		*//*if(t){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}*/
		return null;
	}
	
	@RequestMapping(value="/updatetest",method=RequestMethod.GET)
	public void testUpdateUser(@RequestParam int id){
		User u=new User();
		u.setId(id);
		u.setRole(Role.ADMIN);
		u.setUsername("a");
		userDao.update(u);
	}
	
	private String getUsername(){
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
		
			return null;
		}
		else{
			return auth.getName();
		}
	}
	
	private Collection<?> getRole(){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Collection<?> c=auth.getAuthorities();
		return c;
	}
}
