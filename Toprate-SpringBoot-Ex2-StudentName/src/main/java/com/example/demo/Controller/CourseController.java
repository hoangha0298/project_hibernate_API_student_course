package com.example.demo.Controller;

import com.example.demo.Model.Course;
import com.example.demo.Model.ResponseDelete;
import com.example.demo.Model.Course;
import com.example.demo.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    // thêm
    @PostMapping("course/create")
    public Course addCourse(@RequestBody Course Course) {
        return courseService.addCourses(Course);
    }

    // lấy tất cả môn học
    @GetMapping("courses/list")
    public ArrayList<Course> getAllCourse() {
        return courseService.getAllCourses();
    }

    // lấy môn học theo id
    @GetMapping("course/{couId}")
    public Course getCourseById(@PathVariable int couId) {
        return courseService.getCourseById(couId);
    }

    // xóa môn học theo id
    @GetMapping("course/delete/{couId}")
    public ResponseDelete deleteCourseById(@PathVariable int couId) {
        ResponseDelete responseDelete = new ResponseDelete();
        responseDelete.setSucess(courseService.deleteCourseById(couId));
        return responseDelete;
    }

}
