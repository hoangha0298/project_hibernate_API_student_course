package com.example.demo;

import com.example.demo.Model.Student;
import com.example.demo.Repository.IStudentRepository;
import com.example.demo.Service.CourseService;
import com.example.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner mappingDemo(StudentService studentService, CourseService courseService) {
        return args -> {
            System.out.println("===================================================");
            System.out.println("=============== set data default ==================");
            System.out.println("===================================================");
            // create courses default
            courseService.setDataDefault();
            // create student default
            studentService.setDataDefault();
        };
    }

}
