package com.udacity.jwdnd.course1.cloudstorage.controller;

//import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.model.File;

@Controller
public class FileController {

	private final FileService fileService;
	//private final UserService userService;
	
   public FileController(FileService fileService ) {
		super();
		this.fileService = fileService;
		
	}

	@RequestMapping(value="/uploadFile/{id}",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String handleFileUpload(@RequestParam("fileUpload") MultipartFile file,@PathVariable("id") Integer userId,
			RedirectAttributes redirectAttributes) {
		
	    System.out.println("upload file userID"+userId);
		fileService.uploadFile(file,userId);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		
		return "redirect:/home";

		}
	
	
	@RequestMapping("/downloadFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		File doc = fileService.getFileByID(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(doc.getContenttype()))
				.header(HttpHeaders.CONTENT_DISPOSITION," attachment:filename=\""+doc.getFileName()+"\"")
		        .body(new ByteArrayResource(doc.getFileData()));
		
	}
	 
	@RequestMapping("/deleteFile/{fileId}")
	public String deleteFile(@PathVariable Integer fileId,Model model){
		  try {
			  fileService.deleteFile(fileId);
		  
		  }catch (Exception e) {
			
		}
	    return "redirect:/home";
	}
	

}
	
	
	


