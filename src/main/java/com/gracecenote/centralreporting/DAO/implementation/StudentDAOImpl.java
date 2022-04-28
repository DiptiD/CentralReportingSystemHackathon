package com.gracecenote.centralreporting.DAO.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gracecenote.centralreporting.DAO.interfaces.StudentDAO;
import com.gracecenote.centralreporting.entity.Student;
import com.gracecenote.centralreporting.entity.User;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	final String SELECT_QUERY = "select * from student";
	final String SELECT_BY_ID_QUERY = "select * from student where student_id = ?";
	final String INSERT_QUERY = "insert into student (student_id, student_name, student_email, student_branch) values (?, ?, ?, ?)";
	final String UPDATE_QUERY = "update student set student_name = ?, student_email = ?, student_branch =? where student_id = ?";
	final String DELETE_QUERY = "delete from student where student_id = ?";
	

	public boolean saveStudent(Student student) {
		boolean status = false;
		try {
			jdbcTemplate.update(INSERT_QUERY, student.getStudent_id(), student.getStudent_name(),
					student.getStudent_email(), student.getStudent_branch());
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public List<Student> getStudents() {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_QUERY);
		List<Student> students = new ArrayList<Student>();

		for (Map<String, Object> row : rows) {
			Student student = new Student();
			student.setStudent_id((int) row.get("student_id"));
			student.setStudent_name((String) row.get("student_name"));
			student.setStudent_email((String) row.get("student_email"));
			student.setStudent_branch((String) row.get("student_branch"));
			students.add(student);
		}
		return students;
	}

	public boolean deleteStudent(Student student) {
		boolean status = false;
		try {
			String deleteQuery = "delete from Student where id = ?";
			jdbcTemplate.update(deleteQuery, student.getStudent_id());
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public List<User> getUSers() {
		String SQL = "select * from User";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);
		List<User> users = new ArrayList<User>();

		for (Map<String, Object> row : rows) {
			User user = new User();
			user.setUser_id((int) row.get("user_id"));
			user.setUsername((String) row.get("username"));
			users.add(user);
		}
		return users;
	}

	public Student getStudentByID(Student student) {
		String sql = "select from student where student_id = ?";
		return jdbcTemplate.queryForObject(sql, new StudentMapper(), student.getStudent_id());
	}

	public boolean updateStudent(Student student) {
		boolean status = false;
		try {
			int queryStatus = jdbcTemplate.update(UPDATE_QUERY, student.getStudent_name(), student.getStudent_email(),
					student.getStudent_branch(), student.getStudent_id());
			if (queryStatus != 0) {
				System.out.println("Student data updated for ID " + student.getStudent_id());
			} else {
				System.out.println("No Student found with ID " + student.getStudent_id());
			}
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public List<User> getUsers() {
		return null;
	}

	private static final class StudentMapper implements RowMapper<Student> {
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

			Student student = new Student();
			student.setStudent_id(rs.getInt("student_id"));
			student.setStudent_name(rs.getString("student_name"));
			student.setStudent_email(rs.getString("student_email"));
			student.setStudent_branch(rs.getString("student_branch"));
			return student;
		}
	}
}
