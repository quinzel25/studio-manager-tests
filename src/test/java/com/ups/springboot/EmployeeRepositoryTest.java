package com.ups.springboot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import com.ups.springboot.dao.EmployeeRepository;
import com.ups.springboot.entity.Employee;

@Transactional
@Rollback
@SpringBootTest(classes = UndergroundPineEmployeeTestApplication.class)
public class EmployeeRepositoryTest {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	void testUserRepositoryIsNotNull() {
		Assertions.assertNotNull(employeeRepository);
	}
	
	// find user by their name
	@Test
	void findEmployeeById() {
		Employee employee = new Employee();
		employee.setFirstName("test");
		employeeRepository.save(employee);
		Employee employee2 = employeeRepository.findById(employee.getId()).get();
		Assertions.assertNotNull(employee2);
		Assertions.assertEquals("test", employee2.getFirstName());
	} 
	
	@Test
	void testDeleteEmployee() {
		Employee employee = new Employee();
		employeeRepository.save(employee);
		int theId = employee.getId();
		employeeRepository.deleteById(theId);
		Assertions.assertFalse(employeeRepository.findById(theId).isPresent());
	}
	
}
