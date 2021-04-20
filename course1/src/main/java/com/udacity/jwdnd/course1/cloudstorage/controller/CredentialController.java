 package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;


@Controller
public class CredentialController {
	
	private final UserService userService;
	private final CredentialService credentialService;
	private final EncryptionService encryptionService;
	

 


	public CredentialController(UserService userService, CredentialService credentialService,
			EncryptionService encryptionService) {
		super();
		this.userService = userService;
		this.credentialService = credentialService;
		this.encryptionService = encryptionService;
	}


	@PostMapping("/credential")
	 public String createOrUpdateNote(Credential credential, Authentication authentication, RedirectAttributes redirectAttributes) {
	     String username = authentication.getName();
	      int userId = userService.getUser(username).getUserId();
	     credential.setUserid(userId);
	     String password = credential.getPassword(); //get password from credential form
	        // <start> encrypt the password
	        SecureRandom random = new SecureRandom();
	        byte[] key = new byte[16];
	        random.nextBytes(key);
	        String encodedKey = Base64.getEncoder().encodeToString(key);
	        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
	        // <end> encrypt the password
	        credential.setPassword(encryptedPassword);
	        credential.setKey(encodedKey);
	     if (credential.getCredentialid().intValue() > 0) {
	         try {
	        	 credentialService.addCredential(credential);
	             redirectAttributes.addFlashAttribute("successMessage", "Your credentials was updated successful.");
	            
	            return "redirect:/result";
	         } catch (Exception e) {
	            
	             redirectAttributes.addFlashAttribute("errorMessage", e.getMessage()+"Something went wrong with the credentials update. Please try again!");
	             return "redirect:/result";
	         }
	     } else {
	         try {
	        	 credentialService.createCredential(credential);
	             redirectAttributes.addFlashAttribute("successMessage", "Your note was created successful.");
	             return "redirect:/result";
	         } catch (Exception e) {
	        	 redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
	             return "redirect:/result";
	         }
	     }
	 }
	
	
/*	@GetMapping(value = "/getNote/{noteId}")
	public String getNote(@PathVariable(name = "noteId") String noteID) {
		Integer noteId = Integer.parseInt(noteID);
		Note note= noteService.getNote(noteId);
		if(note!=null) {
			noteService.addNote(note);
		}
		return "redirect:/home";
	}*/
	
	
@RequestMapping("/deletecredential/{id}")
 public String deleteNote(@PathVariable("id") Integer credentialId,@ModelAttribute Credential credential,Model model) {
	System.out.println("deleting credential ");
	
	System.out.println("the credential id"+credentialId);
	 if(credentialId!=null) {
		credentialService.deleteCredential(credentialId);
		
	  }
	
	 return "redirect:/home";
	 }

	

}
