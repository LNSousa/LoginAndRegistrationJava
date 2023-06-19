package com.lsousa.logreg.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.lsousa.logreg.models.LoginUser;
import com.lsousa.logreg.models.User;
import com.lsousa.logreg.repositories.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo repo;
	
    // This method will be called from the controller
    // whenever a user submits a login form.
    public User register(User newUser, BindingResult result) {
        // TO-DO - Reject values: 
        // Reject if email is taken (present in database)
        // 1. Find user in the DB by email    
    	Optional<User> optionalUser = repo.findByEmail(newUser.getEmail());
        // 2. if the email is present , reject
    	if(optionalUser.isPresent()) {
    		result.rejectValue("email", "duplicate", "Email address is already registered");
    	}
        // Reject if password doesn't match confirmation
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    		result.rejectValue("confirm", "match", "Confirm password does not match");
    	}
        // if result has errors, return 
    	if(result.hasErrors()) {
    		return null;
    	}
        // Hash and set password, save user to database  
    	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hashed);
    	
		return repo.save(newUser);
    }

    // This method will be called from the controller
    // whenever a user submits a login form.
    public User login(LoginUser newLogin, BindingResult result) {
    // TO-DO - Reject values: 
        // 1. Find user in the DB by email  
    	Optional<User> optionalUser = repo.findByEmail(newLogin.getEmail());  
        // 2. if the email is not present , reject      
    	if(!optionalUser.isPresent()) {
    		result.rejectValue("email", "not registered", "Email address is incorrect");
    	}
    	
    	if(result.hasErrors()) {
    		return null;
    	}
        // 3.1 grab the user from potential user    
    	User user = optionalUser.get();
        // 3.2 if BCrypt password match fails
    	if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
        }
        // 4 if result has errors,return
    	if(result.hasErrors()) {
    		return null;
    	}
        // Otherwise, return the user object  
    	return user;
    }

}