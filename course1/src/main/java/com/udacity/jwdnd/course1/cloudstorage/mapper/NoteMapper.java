  package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
@Mapper
public interface NoteMapper {
	
	  @Select("SELECT * FROM NOTES WHERE USERID = #{noteid}")
	    List<Note> getNotes(int noteid);

	  @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
		public Note findNoteById(Integer noteId);
	  
	    @Insert("INSERT INTO NOTES (noteid,notetitle,notedescription, userid) VALUES(#{noteId}, #{noteTitle}, #{noteDescription}, #{userId})")
	    @Options(useGeneratedKeys = true, keyProperty = "noteId")
	    int insert(Note note);
	    
	    
	    @Update("UPDATE NOTES SET notetitle=#{noteTitle}, noteDescription =#{noteDescription} WHERE noteId =#{noteId}")
	    void updateNote(Note note);
	    
	    @Delete("DELETE FROM NOTES WHERE noteId =#{noteId}")
	    void deleteNote(int noteId);
	}
	


