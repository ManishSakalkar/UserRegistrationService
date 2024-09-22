package com.userregistrationservice.Service;

import java.util.Optional;

import com.userregistrationservice.Entity.User;

public interface UserService {
	
	public Object createUser(User user);
	
	Optional<User> getUserById(Integer userid);
	
	User findByUsername(String username);
	
	public Iterable<User> getAllUser();
	
	public String updateUser ( User user);
	
	public String deleteUserById(Integer userid);
}
