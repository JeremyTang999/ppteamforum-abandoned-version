package com.ppteam.controller;



import java.io.*;
import java.util.Calendar;
import java.util.Collection;

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
import com.ppteam.entity.User;
import com.ppteam.json.*;
import com.ppteam.service.*;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;

	@RequestMapping(value="/registerinfo",method=RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody RegisterInfo info){
		userService.register(info);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/username",method=RequestMethod.POST)
	public String username(@RequestBody Username username){
		if(userService.usernameAvailable(username.getUsername()))
			return "available";
		else
			return "unavailable";
	}
	
	@RequestMapping(value="/username",method=RequestMethod.GET)
	public ResponseEntity<?> username(){
		//return new ResponseEntity<String>("username", HttpStatus.OK);
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		else{
			Collection<?> c=auth.getAuthorities();
			if(c.size()==1){
				String role=((GrantedAuthority)(c.iterator().next())).getAuthority();
				return new ResponseEntity<>(new UsernameAndRole(auth.getName(),role),HttpStatus.OK);
			}
			else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		}
		
	}
	
	@RequestMapping(value="/userinfo",method=RequestMethod.GET)
	public UserInfo userinfo(){
		return new UserInfo("male","abc");
	}
	
	@RequestMapping(value="/userinfo",method=RequestMethod.POST)
	public ResponseEntity<?> userinfo(UserInfo info){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
