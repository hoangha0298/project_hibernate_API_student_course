package com.example.demo.Controller;

import com.example.demo.Model.ResponseDelete;
import com.example.demo.Model.Student;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;     // singleton service sinh viên

    // thêm
    @PostMapping("student/create")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudents(student);
    }

    // update
    @PostMapping("student/update_courses")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateCourses(student);
    }

    // lấy về tất cả sinh viên
    @GetMapping("students/list")
    public ArrayList<Student> getAllStudent() {
        return studentService.getAllStudents();
    }

    // lấy sinh viên theo id
    @GetMapping("student/{stdId}")
    public Student getStudentById(@PathVariable int stdId) {
        return studentService.getStudentById(stdId);
    }

    // xóa sinh viên theo id
    @GetMapping("student/delete/{stdId}")
    public ResponseDelete deleteStudentById(@PathVariable int stdId) {
        ResponseDelete responseDelete = new ResponseDelete();
        responseDelete.setSucess(studentService.deleteStudentById(stdId));
        return responseDelete;
    }

    // Đăng ký môn học cho sinh viên theo id sinh viên , id môn học
    @PostMapping("student/registration")
    public Student addCourseForStudentById(@RequestParam("student_id") int stdId, @RequestParam("course_id") int couId) {
        return studentService.addCourseForStudentById(stdId,couId);
    }

}
