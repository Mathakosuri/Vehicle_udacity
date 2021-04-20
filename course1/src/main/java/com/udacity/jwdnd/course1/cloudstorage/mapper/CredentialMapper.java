package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@Mapper
public interface CredentialMapper {

	  @Select("SELECT * FROM CREDENTIALS WHERE USERID = #{userid}")
	    List<Credential> getUserCredentials(int userid);

	  @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
		 Credential findCredentialById(Integer credentialId);
	  
	    @Insert("INSERT INTO CREDENTIALS (CREDENTIALID, URL, USERNAME, KEY, PASSWORD, USERID) VALUES(#{credentialid}, #{url}, #{username}, #{key},#{password},#{userid})")
	    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
	    int insertCredential(Credential credentail);
	    
	    
	    @Update("UPDATE CREDENTIALS SET url=#{url}, userName=#{username},key=#{key}, password=#{password},userid=#{userid} WHERE credentialid =#{credentialid}")
	    void updateCredential(Credential credential);
	    
	    @Delete("DELETE FROM CREDENTIALS WHERE credentialid =#{credentialid}")
	    void deleteCredential(int credentialid);
}
