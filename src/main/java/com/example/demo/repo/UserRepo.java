package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	//	List<User> findBySalaryGreaterThan(double salaryThreshold);

	@Query("SELECT u FROM User u WHERE u.salary > :salary")
	
    List<User> findUsersWithSalaryGreaterThan(@Param("salary") double salary);
	
	//	List<User> findBySalaryGreaterThan(double salaryThreshold);

}
