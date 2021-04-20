package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller

//@RequestMapping(value={"/home"})
@RequestMapping("/home")
public class HomeController {
	
	private final UserService userService;
	private final NoteService noteService;
	private final FileService fileService;
	private final CredentialService credentialService;
	private final EncryptionService encryptionService;
	
 public HomeController(UserService userService, NoteService noteService, FileService fileService,CredentialService credentialService,EncryptionService encryptionService) {
		super();
		this.userService = userService;
		this.noteService = noteService;
		this.fileService = fileService;
		this.credentialService=credentialService;
		this.encryptionService= encryptionService;
	}





//@RequestMapping(value ="/home",  method = RequestMethod.GET)
	
@GetMapping()	
public String homeView(Authentication auth,Model model,@ModelAttribute Note note) {
	if(auth!=null) {
		String userName = auth.getName();
		User user = userService.getUser(userName);
		if(user!=null && user.getUserId()!=null) {
			try {
			
		List<File> files = fileService.getFiles(user.getUserId());
		 model.addAttribute("files", files);
		 model.addAttribute("notes", noteService.getNotes(user.getUserId()));
		 List<Credential> credentials= credentialService.getUserCredentials(user.getUserId());
	     model.addAttribute("credentials",credentials);
	     model.addAttribute("encryptionService",encryptionService);
			    
			    
		// model.addAttribute("credentials",credentialService.getUserCredentials(user.getUserId()));
		 model.addAttribute("userId", user.getUserId());
		 if(user.getUserId()!=null) {
			 note.setUserId(user.getUserId());
		 }
		 System.out.println("the  logged in userId"+user.getUserId());
		 
		}catch(Exception e) {
			System.out.println("Error retrieving user details"+e.getMessage());
			return "/result";
		}
		}
		
	}
	
	return "/home";
}



// @RequestMapping(value = {"/note"},  method = RequestMethod.POST)

//	public String saveNote(@ModelAttribute Note note,Model model) {
	
//	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	String  pcl= String.valueOf(auth.getPrincipal());
//	System.out.println("the string name "+pcl);
//	User user = userService.getUser(pcl);
//	System.out.println("inside savenote");
	
//	if(note != null) {
//		System.out.println("the note userid"+note.getUserId());
//		this.noteService.createNote(note);
 //       model.addAttribute("notes", noteService.getNotes(note.getUserId()));
        
		
//	}
	
	
//	 return "redirect:/home";
	 
//} 


}
