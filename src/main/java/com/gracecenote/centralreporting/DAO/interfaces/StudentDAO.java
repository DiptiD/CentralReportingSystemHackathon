package com.gracecenote.centralreporting.DAO.interfaces;

import java.util.List;

import com.gracecenote.centralreporting.entity.Student;

public interface StudentDAO {

	public boolean saveStudent(Student student);

	public List<Student> getStudents();

	public boolean deleteStudent(Student student);

	public Student getStudentByID(Student student);

	public boolean updateStudent(Student student);

}
