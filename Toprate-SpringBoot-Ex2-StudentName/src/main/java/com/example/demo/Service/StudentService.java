package com.example.demo.Service;

import com.example.demo.Model.Course;
import com.example.demo.Model.Student;
import com.example.demo.Repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private IStudentRepository iStudentRepository;
    @Autowired
    private CourseService courseService;

    public StudentService() {
    }

    public void setDataDefault () {
        ArrayList<Student> students = new ArrayList<>();

        Student temp = new Student(1, "Hà Duy Hoàng", "phú thọ", 22);
        // course thêm vào chỉ cần id các trường khác k có cũng được
        temp.addCourse(new Course(3));
        temp.addCourse(new Course(4, "Địa lý", "444 A1", "Đào Thị Trang"));
        temp.addCourse(new Course(2, "Vật lý", "444 A1", "Đào Thị Thu Hoa"));

        students.add(temp);
        students.add(new Student(2, "Nguyễn Trung Dũng", "nam định", 21));
        students.add(new Student(3, "Đào Văn Lâm", "yên bái", 25));
        students.add(new Student(4, "Đào Duy Hiển", "lâm đồng", 20));
        iStudentRepository.saveAll(students);
    }

    // thêm
    public Student addStudents(Student student) {
        // id == 0 là tạo mới sinh viên
        if (student.getId() == 0)
            return iStudentRepository.save(student);
        return null;
    }

    // update toàn bộ trừ id
    public Student updateStudents(Student student) {
        try {
            // id != 0 là cập nhật sinh viên , nếu đã tồn tại sv thì mới update
            if (student.getId() != 0 && iStudentRepository.existsById(student.getId()))
                return iStudentRepository.save(student);
        } catch (Exception e) {
            // có thể course truyền lên không tồn tại
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> temp = (ArrayList<Student>) iStudentRepository.findAll();

        // tại sao lấy về đối trượng lại không bị đệ quy mà chạy bình thường ( đệ quy thì heap bị tràn )
        // tại sao khi gọi đến hàm toString lại đệ quy và bị null ???
//        System.out.println(temp);
        System.out.println("=================================================================================");
        System.out.println("================================= error cần hỏi =================================");
        System.out.println("============= Comment trong Service.StudentService.getAllStudents()  ============");
        System.out.println("================================= error cần hỏi =================================");
        System.out.println("=================================================================================");


        return temp;
    }

    // lấy sinh viên theo id
    public Student getStudentById(int stdId) {
        try {
            return iStudentRepository.findById(stdId).get();
        } catch (Exception e) {
        }
        return null;
    }

    public boolean deleteStudentById(int stdId) {
        // không có id này
        if (getStudentById(stdId) == null) return false;
        iStudentRepository.deleteById(stdId);
        // xóa thành công
        if (getStudentById(stdId) == null) return true;
        // xóa không thành công
        return false;
    }

    // đăng ký môn học cho sinh viên theo id sinh viên , theo id môn học
    public Student addCourseForStudentById(int stdId, int couId) {
        Student student = getStudentById(stdId);
        Course course = courseService.getCourseById(couId);
        // môn học hoặc sinh viên không tồn tại
        if (student == null || course == null) return student;
        // sv đã đăng ký môn học
        if (student.isContainCourse(couId)) return student;
        student.addCourse(course);
        // cập nhật thông tin
        updateStudents(student);
        return student;
    }

    // update chỉ trường môn học cho sinh viên
    public Student updateCourses(Student student) {
        Student studentPresent = getStudentById(student.getId());
        // sinh viên không tồn tại
        if (studentPresent == null)
            return studentPresent;
        //̀ cập nhật thông tin môn học
        studentPresent.setCourses(student.getCourses());
        return updateStudents(studentPresent);
    }


}
