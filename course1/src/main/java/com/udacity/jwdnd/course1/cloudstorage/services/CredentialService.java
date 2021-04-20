 package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@Service
public class CredentialService {
	private final CredentialMapper credentialmapper;
	
	 public CredentialService(CredentialMapper credentialmapper) {
		super();
		this.credentialmapper = credentialmapper;
	}

	public int createCredential(Credential credential) {
		  return credentialmapper.insertCredential(new Credential(credential.getUrl(),credential.getUsername(),credential.getKey(),credential.getPassword(),credential.getUserid()));
	      }

	    public List<Credential> getUserCredentials(int userId) {
	        return credentialmapper.getUserCredentials(userId);
	    }
	    
	    
	    public void deleteCredential(int credentialId) {
	    	credentialmapper.deleteCredential(credentialId);
	    }

	    
	    public void addCredential(Credential credential) {
	    	 Credential credentials = credentialmapper.findCredentialById(credential.getCredentialid());
		if(credentials != null) {
			credentials.setUrl(credential.getUrl());
			credentials.setUsername(credential.getUsername());
			credentials.setKey(credential.getKey());
			credentials.setPassword(credential.getPassword());
			credentials.setUserid(credential.getUserid());
			credentialmapper.updateCredential(credentials);
		} else {
			 credentialmapper.insertCredential(new Credential(credential.getUrl(),credential.getUsername(),credential.getKey(),credential.getPassword(),credential.getUserid()));

		}
	    }
	    
	    public Credential getCredentialById(Integer credentialId) {
		return credentialmapper.findCredentialById(credentialId);
		
	}
		

}
