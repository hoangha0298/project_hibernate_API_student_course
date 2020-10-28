package com.example.demo.Service;

import com.example.demo.Model.Course;
import com.example.demo.Repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;

@Service
public class CourseService {

    @Autowired
    private ICourseRepository iCourseRepository;

    public CourseService() {
    }

    public void setDataDefault () {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course(1, "Toán", "111 A1", "Đào Văn Tuyên"));
        courses.add(new Course(2, "Văn", "123 A2", "Nguyễn Khắc Thành"));
        courses.add(new Course(3, "Anh", "232 A3", "Phạm Ngọc Hùng"));
        courses.add(new Course(4, "Địa lý", "444 A1", "Đào Thị Trang"));
        iCourseRepository.saveAll(courses);
    }

    public Course addCourses(Course Course) {
        return iCourseRepository.save(Course);
    }

    public ArrayList<Course> getAllCourses() {
        try {
            Field field = Course.class.getDeclaredField("students");

        }
        catch (NoSuchFieldException nsfe) {
            nsfe.printStackTrace();
            return null;
        }
        catch (SecurityException se) {
            se.printStackTrace();
            return null;
        }
        return (ArrayList<Course>) iCourseRepository.findAll();
    }

    // lấy môn học
    public Course getCourseById(int couId) {
        try {
            return iCourseRepository.findById(couId).get();
        } catch (Exception e) {
        }
        return null;
    }

    public boolean deleteCourseById(int couId) {
        // không có id này để xóa
        if (getCourseById(couId) == null) return false;
        iCourseRepository.deleteById(couId);
        // xóa thành công
        if (getCourseById(couId) == null) return true;
        // xóa không thành công
        return false;
    }
    
}
