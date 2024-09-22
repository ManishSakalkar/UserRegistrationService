package com.userregistrationservice.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userregistrationservice.Entity.User;
import com.userregistrationservice.Service.UserService;
import com.userregistrationservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Object createUser(User user) {
		
		userRepository.save(user);
		return "User Create Successfully";
	}

	@Override
	public Optional<User> getUserById(Integer userid) {
		return userRepository.findById(userid);
	}
	
	@Override
	public User findByUsername(String username){
		return userRepository.findByUsername(username);
		
	}

	@Override
	public Iterable<User> getAllUser() {
		Iterable<User> alluser = userRepository.findAll();
		return alluser;
	}

	@Override
	public String updateUser(User user) {
		Optional<User> opt = userRepository.findById(user.getUserid());
		if(opt.isPresent()) {
			
			User temp = opt.get();
			
			temp.setUsername(user.getUsername());
			temp.setEmail(user.getEmail());
			temp.setPassword(user.getPassword());
			temp.setConfirmPassword(user.getConfirmPassword());
			
			userRepository.save(temp);
			
			return "User Updated Successfully";
		}
		return "User Not Found";
		
	}

	@Override
	public String deleteUserById(Integer userid) {
		Optional<User> opt = userRepository.findById(userid);
		if(opt.isPresent()) {
			User user = opt.get();
			userRepository.delete(user);
			return "User is Deleted";
		}
		else {
			return "User Not Found";
		}
		
	}

}
