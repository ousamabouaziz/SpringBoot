package com.example.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.User;
import com.example.demo.repository.UserRepository;



@CrossOrigin(origins = "http://localhost:4200") 
@RestController
@RequestMapping("/api/v1/")
public class UserController {
	
	
	
	

	@Autowired
	private UserRepository userRepository;
	
	// get all users
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}		
	
	// create user rest api
		@PostMapping("/users")
		public User createUser(@RequestBody User user) {
			return userRepository.save(user);
		}
		
		// get user by id rest api
		@GetMapping("/users/{id}")
		public ResponseEntity<User> getEmployeeById(@PathVariable Long id) {
			User user = userRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
			return ResponseEntity.ok(user);
		}
		
		// update user rest api
		
		@PutMapping("/users/{id}")
		public ResponseEntity<User> updateEmployee(@PathVariable Long id, @RequestBody User userDetails){
			User user = userRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
			
			user.setName(userDetails.getName());
			user.setLastname(userDetails.getLastname());
			user.setEmail(userDetails.getEmail());
			
			User updatedUser = userRepository.save(user);
			return ResponseEntity.ok(updatedUser);
		}
		
		// delete employee rest api
		@DeleteMapping("/users/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
			User user = userRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
			
			userRepository.delete(user);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}

}
