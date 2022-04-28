package com.gracecenote.centralreporting.service.interfaces;

import java.util.List;

import com.gracecenote.centralreporting.entity.Student;

public interface StudentService {

	public boolean saveStudent(Student student);

	public List<Student> getStudents();

	public boolean deleteStudent(Student student);

	public Student getStudentByID(Student student);

	public boolean updateStudent(Student student);
}
