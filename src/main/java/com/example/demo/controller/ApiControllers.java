package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.models.User;
import com.example.demo.repo.UserRepo;


@RestController 
@CrossOrigin(origins = "http://localhost:4200")


public class ApiControllers {
		
	@Autowired
	private UserRepo userRepo;
	
	
	@GetMapping("/employeeList")
    public List<User> getUsers(){
        return userRepo.findAll();
    }	

	
	@PostMapping("employeeList/create")	
    public User saveUsers(@RequestBody User user) {
        return userRepo.save(user);
    }
	
	
	@PutMapping("/employeeList/{id}/edit")
	public ResponseEntity<User> updateEmployee(@PathVariable Long id, @RequestBody User user){
		User updatedUser = userRepo.findById(id).get();		
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPhone(user.getPhone());
        updatedUser.setSalary(user.getSalary());
        updatedUser.setAddress(user.getAddress());
		
        User updatedEmployee = userRepo.save(updatedUser);
		return ResponseEntity.ok(updatedEmployee);
	}
	

	
	@DeleteMapping("/employeeList/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id) {
		User employee = userRepo.findById(id).orElseThrow();
		userRepo.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}	

	
	@GetMapping("/employeeList/{id}/view")
	public ResponseEntity<User> getEmployeeById(@PathVariable("id") long id) {
		Optional<User> tutorialData = userRepo.findById(id);
		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

//	@GetMapping("/employeeList/salary")
//	@GetMapping("/employeeList/salary")
//	public ResponseEntity<List<User>> getEmployeesWithHighSalary() {
//		List<User> list=new ArrayList<User>();
////		List<User> list;
//	    List<User> highSalaryEmployees = list.stream()
//	                                          .filter(emp -> emp.getSalary() > 5000)
//	                                          .collect(Collectors.toList());
//	    System.out.println("highSalaryEmployees =>"+highSalaryEmployees);
//	    return new ResponseEntity<>(highSalaryEmployees, HttpStatus.OK);
//	}
	

	
//	@GetMapping("/employeeList/highestSalary")
//    public ResponseEntity<User> getUserWithHighestSalary() {
//        User user = userRepo.findUserWithHighestSalary();
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

//	@GetMapping("/employeeList/highestSalary")
//    public ResponseEntity<User> getUserWithHighestSalary() {
//        User user = userRepo.findUserWithHighestSalary();
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
	
//	@GetMapping("/employeeList/salary")
//    public ResponseEntity<List<User>> getUsersWithHighSalary(@RequestParam double salary) {
//        List<User> highSalaryUsers = userRepo.findUsersWithSalaryGreaterThan(salary);
//        return new ResponseEntity<>(highSalaryUsers, HttpStatus.OK);
//    }
	
	//===== Working Fine First Step
	//	@GetMapping("/employeeList/salaryGreaterThan")
	//	public List<User> getUsersWithSalaryGreaterThan() {
	//		double salaryThreshold = 5000;			
	//		return userRepo.findUsersWithSalaryGreaterThan(salaryThreshold);
	//	}
	
	
	@GetMapping("/employeeList/salaryGreaterThan")
	public List<User> getUsersWithSalaryGreaterThan() {
		double salaryamount = 5000;			
		
		List<User> users = userRepo.findAll();
		
		return users.stream()
                .filter(user -> user.getSalary() > salaryamount)
                .collect(Collectors.toList());
		
//		return userRepo.findUsersWithSalaryGreaterThan(salaryThreshold);
	}
	
}
