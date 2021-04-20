package com.udacity.jwdnd.course1.cloudstorage.services;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;


@Service
public class FileService {
	
	private final FileMapper fileMapper;
	
	public FileService(FileMapper fileMapper) {
		super();
		this.fileMapper = fileMapper;
	}

	public File uploadFile( MultipartFile files,Integer userID) {
		String docName = files.getOriginalFilename();
		String docLength=String.valueOf((files.getSize()>0)?files.getSize():0);
		   
			try {
				Blob blob = new javax.sql.rowset.serial.SerialBlob(files.getBytes());
				System.out.println("the blob file value "+blob);
				File file  = new File(docName,files.getContentType(),docLength,userID,files.getBytes());
				 fileMapper.saveFile(file);
					
			} catch (Exception e) {
				e.printStackTrace();
			} 
		      return null;
	      }

	    public List<File> getFiles(int userId) {
	        return fileMapper.getFiles(userId);
	    }
	    
	    
	    public  File getFileByID(int fileID) {
	    	return fileMapper.findFileById(fileID);
	    }
	    
	    public void deleteFile(int fileID) {
	    	fileMapper.deleteFile(fileID);
	    	
	    }

}
