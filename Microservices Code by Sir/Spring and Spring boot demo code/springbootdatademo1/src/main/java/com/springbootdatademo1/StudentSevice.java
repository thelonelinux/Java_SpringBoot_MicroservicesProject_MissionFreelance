package com.springbootdatademo1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentSevice {
	
	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> getAllStudents() {
		//this will get all the records from the student table and create a list of
		//student objects
		return studentRepository.findAll();
	}
	
	public Student getStudentById(int id) {
		return studentRepository.findById(id).get();
	}
	
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public List<Student> getStudentsByFirstNameAndLastName(String firstName, String lastName) {
		return studentRepository.findByFirstNameAndLastName(firstName, lastName);
	}
	
	public List<Student> getStudentsByFirstNameOrLastName(String firstName, String lastName) {
		return studentRepository.findByFirstNameOrLastName(firstName, lastName);
	}
	
	public List<Student> getStudentsByFirstName(String firstName) {
		return studentRepository.findByFirstName(firstName);
	}
	
	public List<StudentDTO> getIdAndEmailByFirstAndLastName(String firstName, String lastName) {
		List<Object[]> studentData = studentRepository.getIdAndEmailByFirstAndLastName(firstName, lastName);
		List<StudentDTO> students = new ArrayList<>();
		for(int i=0;i<studentData.size();i++) {
			Object[] studentAttributesData = studentData.get(i);
			int id = (Integer)studentAttributesData[0];
			String email = (String)studentAttributesData[1];
			StudentDTO studentDTO = new StudentDTO();
			studentDTO.setId(id);
			studentDTO.setEmail(email);
			students.add(studentDTO);
		}
		return students;
	}
	
	public List<Student> getAllStudents(int offset, int limit) {
		Pageable pageable = PageRequest.of(offset, limit);
		return studentRepository.findAll(pageable).getContent();
	}

}
