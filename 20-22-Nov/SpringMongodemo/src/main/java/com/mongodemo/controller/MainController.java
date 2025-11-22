package com.mongodemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodemo.model.Student;
import com.mongodemo.repository.StudentRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

	
@RestController
public class MainController {
	
	@Autowired
	StudentRepository studentRepo; 

	@PostMapping("/addStudent")
	public void addStudent(@RequestBody Student student) {
		studentRepo.save(student);
	}
	
	@GetMapping("/getStudent/{id}")
	public Student getStudent(@PathVariable Integer id) {
		return studentRepo.findById(id).orElse(null);
	}
	
	@GetMapping("/fetchStudents")
	public List<Student> fetchStudent() {
		return studentRepo.findAll();
	}
	
	@PutMapping("/updateStudent")
	public void updateStudent(@RequestBody Student student) {
		Student data = studentRepo.findById(student.getRno()).orElse(null);
		if(data!=null) {
			data.setName(student.getName());
			data.setAddress(student.getAddress());
			studentRepo.save(data);
		}
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	public void deleteStudent(@PathVariable Integer id) {
		studentRepo.deleteById(id);
	}
}
