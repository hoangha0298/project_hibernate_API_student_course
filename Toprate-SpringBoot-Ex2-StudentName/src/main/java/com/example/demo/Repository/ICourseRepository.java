package com.example.demo.Repository;

import com.example.demo.Model.Course;
import org.springframework.data.repository.CrudRepository;

public interface ICourseRepository extends CrudRepository<Course, Integer> {
}
