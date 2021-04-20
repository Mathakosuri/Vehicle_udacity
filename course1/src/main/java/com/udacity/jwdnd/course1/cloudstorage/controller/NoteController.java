package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
//@RequestMapping("/note")
public class NoteController {
	
	private final UserService userService;
	private final NoteService noteService;
	
	public NoteController(UserService userService, NoteService noteService) {
		super();
		this.userService = userService;
		this.noteService = noteService;
	}
	
	
/*	@PostMapping("/note")
	public String saveNote(@ModelAttribute Note note,Model model) {
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String  pcl= String.valueOf(auth.getPrincipal());
	System.out.println("the string name "+pcl);
	User user = userService.getUser(pcl);
	System.out.println("inside savenote");
	
	if(note != null) {
		System.out.println("the note userid"+note.getUserId());
		this.noteService.createNote(note);
        model.addAttribute("notes", noteService.getNotes(note.getUserId()));
        
		
	}
	
	
	 return "redirect:/home";
	 
}*/
	
	
	
	
	@PostMapping("/note")
	 public String createOrUpdateNote(Note note, Authentication authentication, RedirectAttributes redirectAttributes) {
	     String username = authentication.getName();
	     boolean isSuccess=true;
	     int userId = userService.getUser(username).getUserId();
	     note.setUserId(userId);
	     if (note.getNoteId().intValue() > 0) {
	         try {
	             noteService.addNote(note);
	             redirectAttributes.addFlashAttribute("successMessage", "Your note was updated successful.");
	            
	            return "redirect:/result";
	         } catch (Exception e) {
	             isSuccess=false;
	             redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong with the note update. Please try again!");
	             return "redirect:/result";
	         }
	     } else {
	         try {
	             noteService.createNote(note);
	             redirectAttributes.addFlashAttribute("successMessage", "Your note was created successful.");
	             return "redirect:/result";
	         } catch (Exception e) {
	        	 isSuccess=false;
	             redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong with the note update. Please try again!");
	             return "redirect:/result";
	         }
	     }
	 }
	
	
	@GetMapping(value = "/getNote/{noteId}")
	public String getNote(@PathVariable(name = "noteId") String noteID) {
		Integer noteId = Integer.parseInt(noteID);
		Note note= noteService.getNote(noteId);
		if(note!=null) {
			noteService.addNote(note);
		}
		return "redirect:/home";
	}
	
	
@RequestMapping("/deletenote/{id}")
 public String deleteNote(@PathVariable("id") Integer noteID,@ModelAttribute Note note,Model model) {
	System.out.println("deleting note ");
	
	System.out.println("the note id"+noteID);
	 if(noteID!=null) {
		noteService.deleteNote(noteID); 
		
	  }
	
	 return "redirect:/home";
	 }

	

}
