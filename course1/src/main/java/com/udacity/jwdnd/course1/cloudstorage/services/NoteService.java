package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;

import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Service
public class NoteService {
	
	private final NoteMapper noteMapper;
	
	
	public NoteService(NoteMapper noteMapper) {
		super();
		this.noteMapper = noteMapper;
	}
	
	
	 public int createNote(Note note) {
		  return noteMapper.insert(new Note(note.getNoteTitle(), note.getNoteDescription(), note.getUserId()));
	      }

	    public List<Note> getNotes(int userId) {
	        return noteMapper.getNotes(userId);
	    }
	    
	    
	    public void deleteNote(int noteId) {
	         noteMapper.deleteNote(noteId);
	    }

	    
	    public void addNote(Note note) {
	    	 Note notes = noteMapper.findNoteById(note.getNoteId());
		if(notes != null) {
			notes.setNoteTitle(note.getNoteTitle());
			notes.setNoteDescription(note.getNoteDescription());
			noteMapper.updateNote(notes);
		} else {
		    notes = new Note();
		    notes.setNoteTitle(note.getNoteTitle());
		    notes.setNoteDescription(note.getNoteDescription());
			noteMapper.insert(notes);
		}
	    }
	    
	    public Note getNote(Integer noteId) {
		return noteMapper.findNoteById(noteId);
		
	}
		

}
