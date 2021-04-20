package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


import com.udacity.jwdnd.course1.cloudstorage.model.File;



@Mapper
public interface FileMapper {
	
	
	
	@Select("SELECT * FROM FILES WHERE USERID = #{userId}")
    List<File> getFiles(int userId);

   @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
	 File findFileById(Integer fileId);
  
    @Insert("INSERT INTO FILES (FILEID,FILENAME,CONTENTTYPE,FILESIZE,USERID,FILEDATA) VALUES(#{fileId}, #{fileName}, #{contenttype}, #{fileSize},#{userId},#{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int saveFile(File file);
    
    @Delete("DELETE  FROM FILES WHERE fileid =#{fileId}")
    void deleteFile(int fileId);



}
