package com.gracecenote.centralreporting.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gracecenote.centralreporting.entity.Student;
import com.gracecenote.centralreporting.service.interfaces.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping(value = "/api")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("save-student")
	public boolean saveStudent(@RequestBody Student student) {
		return studentService.saveStudent(student);

	}

	@GetMapping("students-list")
	public List<Student> allstudents() {
		return studentService.getStudents();

	}

	@DeleteMapping("delete-student/{student_id}")
	public boolean deleteStudent(@PathVariable("student_id") int student_id, Student student) {
		student.setStudent_id(student_id);
		return studentService.deleteStudent(student);
	}

	@GetMapping("student/{student_id}")
	public Student allstudentByID(@PathVariable("student_id") int student_id, Student student) {
		student.setStudent_id(student_id);
		return studentService.getStudentByID(student);

	}

	@PostMapping("update-student/{student_id}")
	public boolean updateStudent(@RequestBody Student student, @PathVariable("student_id") int student_id) {
		student.setStudent_id(student_id);
		return studentService.updateStudent(student);
	}
	
	@PostMapping("/upload2")
	@ResponseBody
	public List<Map<String, String>> upload2(@RequestParam("file") MultipartFile[] files) throws IOException {
	    if (files == null || files.length == 0) {
	        return null;
	    }
	    List<Map<String, String>> results = new ArrayList<>();
	    for (MultipartFile file : files) {
	        file.transferTo(new File("/home/diptidhumal/relay-git/docs/aetv" + file.getOriginalFilename()));
	                Map<String, String> map = new HashMap<>(16);
	        map.put("contentType", file.getContentType());
	        map.put("fileName", file.getOriginalFilename());
	        map.put("fileSize", file.getSize() + "");
	        results.add(map);
	    }
	    return results;
	}


}
