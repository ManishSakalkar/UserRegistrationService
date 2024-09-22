package com.userregistrationservice.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userregistrationservice.Entity.User;
import com.userregistrationservice.ServiceImpl.UserServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping("/createuser")
	public ResponseEntity<Object> createUser(@RequestBody User user, BindingResult result){
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(FieldError error : result.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(errors);
			
		}
		Object saveUser = userServiceImpl.createUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
		
	}
	
	@GetMapping("/getuser/{userid}")
	public ResponseEntity<Optional<User>> findUserById(@PathVariable Integer userid)
	{
		Optional<User> user = userServiceImpl.getUserById(userid);
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
		
	}
	
	 @GetMapping("/getuserbyusername/{username}")
	 public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
		    
		    User user = userServiceImpl.findByUsername(username);

		    
		    if (user != null) {
		       
		        return ResponseEntity.ok(user);
		    } else {
		      
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with username '" + username + "' not found");
		    }
		}
	
	@GetMapping("/getalluser")
	public ResponseEntity<Iterable<User>> getAllUser(){
		Iterable<User> findAllUser = userServiceImpl.getAllUser();
		
		return ResponseEntity.status(HttpStatus.OK).body(findAllUser);
	}
	
	
	@PutMapping("/updateuser/{userid}")
	public ResponseEntity<String> updateUser(@RequestBody User user){
		
		
		String updateResult = userServiceImpl.updateUser(user);
		
		return ResponseEntity.status(HttpStatus.OK).body(updateResult);
	
	}
	
	
	@DeleteMapping("/deleteuser/{userid}")
	public ResponseEntity<Object> deleteById(@PathVariable Integer userid){
		String result =  userServiceImpl.deleteUserById(userid);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@GetMapping("/forgotpassword/{username}")
    public ResponseEntity<Object> forgotPassword(@PathVariable String username) {
       
		User user = userServiceImpl.findByUsername(username);
        
		if (user != null) {
           
			String password = user.getPassword();
            
			return ResponseEntity.ok().body("Your password is: " + password);
        } else {
        	
            return ResponseEntity.notFound().build();
        }
    }
		

}
