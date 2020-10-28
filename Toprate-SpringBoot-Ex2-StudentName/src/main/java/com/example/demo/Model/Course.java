package com.example.demo.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// lớp học
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String subjects;    // môn học
    private String room;        // phòng học
    private String teacher;     // giáo viên
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    @JsonIgnoreProperties("courses")
    private Set<Student> students = new HashSet<>();

    public Course() {
    }

    public Course(int id) {
        this.id = id;
    }

    public Course(int id, String subjects, String room, String teacher) {
        this.id = id;
        this.subjects = subjects;
        this.room = room;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", subjects='" + subjects + '\'' +
                ", room='" + room + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}
