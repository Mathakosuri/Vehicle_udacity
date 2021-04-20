package com.udacity.jwdnd.course1.cloudstorage.model;


import java.sql.Blob;

import org.apache.ibatis.type.BlobInputStreamTypeHandler;

public class File {
	
	private Integer fileId;
	private String fileName;
	private String contenttype;
	private String fileSize;
	
	
	private byte[] fileData;
	
	private Integer userId;
	
	
	public File(Integer fileId, String fileName, String contenttype, String fileSize, Integer userId,byte[] fileData) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.contenttype = contenttype;
		this.fileSize = fileSize;
		this.userId = userId;
		this.fileData = fileData;
	}
	
	
	
	public File(String fileName, String contenttype, String fileSize, Integer userId,byte[] fileData) {
		super();
		this.fileName = fileName;
		this.contenttype = contenttype;
		this.fileSize = fileSize;
		this.userId = userId;
		this.fileData = fileData;
	}



	
	public Integer getFileId() {
		return fileId;
	}

    public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}



	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContenttype() {
		return contenttype;
	}
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	

}
