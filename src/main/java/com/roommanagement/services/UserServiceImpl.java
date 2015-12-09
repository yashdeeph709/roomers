package com.roommanagement.services;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;
import com.roommanagement.collections.UserCollection;
import com.roommanagement.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UsersRepository repository;
	@Autowired
	private MongoOperations mongoOperations;

	public void delete(String id) {
		repository.delete(id);
	}


	public List<UserCollection> getUsers() {
		return repository.findAll();
	}


	public UserCollection insert(UserCollection user) {
	BasicQuery basicQuery= new BasicQuery("{ name : \""+user.getEmail()+"\" }");
		UserCollection userTest=mongoOperations.findOne(basicQuery,UserCollection.class);
		if(userTest!=null)
		{
			System.out.println("User already exists "+userTest.getName());
		}
		else
		{
			return repository.insert(user);
		}
		return user;
	}


	public boolean checkUser(String id) {	
		KeyGenerator kg;
		try {
			kg = KeyGenerator.getInstance("DES");
			 Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding"); 
				Key key = kg.generateKey();
				c.init(Cipher.ENCRYPT_MODE, key);
				byte input[] = "Stand and unfold yourself".getBytes();
				byte encrypted[] = c.doFinal(input);
				byte iv[] = c.getIV();
				IvParameterSpec dps = new IvParameterSpec(iv);
				c.init(Cipher.DECRYPT_MODE, key, dps);
				byte output[] = c.doFinal(encrypted);
				System.out.println(new String(output));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return repository.findOne(id)==null?true:true;
	}

}
