package com.gracecenote.centralreporting.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gracecenote.centralreporting.DAO.interfaces.StudentDAO;
import com.gracecenote.centralreporting.entity.Student;
import com.gracecenote.centralreporting.service.interfaces.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentdao;

	public boolean saveStudent(Student student) {
		return studentdao.saveStudent(student);
	}

	public List<Student> getStudents() {
		return studentdao.getStudents();
	}

	public boolean deleteStudent(Student student) {
		return studentdao.deleteStudent(student);
	}

	public Student getStudentByID(Student student) {
		return studentdao.getStudentByID(student);
	}

	public boolean updateStudent(Student student) {
		return studentdao.updateStudent(student);
	}
}
