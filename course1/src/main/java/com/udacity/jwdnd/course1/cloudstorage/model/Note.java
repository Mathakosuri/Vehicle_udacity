package com.udacity.jwdnd.course1.cloudstorage.model;

import org.springframework.beans.factory.annotation.Autowired;


public class Note {
  




private Integer noteId;
  private String  noteTitle;
  private String noteDescription;
  @Autowired
  private Integer userId;
  
  public Note() {
		super();
	}
  
public Note(Integer noteId, String noteTitle, String noteDescription, Integer userId) {
	super();
	this.noteId = noteId;
	this.noteTitle = noteTitle;
	this.noteDescription = noteDescription;
	this.userId = userId;
}





public Note(String noteTitle, String noteDescription, Integer userId) {
	super();
	this.noteTitle = noteTitle;
	this.noteDescription = noteDescription;
	this.userId = userId;
}

public Integer getNoteId() {
	return noteId;
}




public void setNoteId(Integer noteId) {
	this.noteId = noteId;
}




public String getNoteTitle() {
	return noteTitle;
}




public void setNoteTitle(String noteTitle) {
	this.noteTitle = noteTitle;
}




public String getNoteDescription() {
	return noteDescription;
}




public void setNoteDescription(String noteDescription) {
	this.noteDescription = noteDescription;
}




public Integer getUserId() {
	return userId;
}




public void setUserId(Integer userId) {
	this.userId = userId;
}



  
  
  

  
  
  
  
}
