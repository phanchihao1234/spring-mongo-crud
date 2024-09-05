package com.example.ontap_CRUD;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepo;

    @GetMapping("/")
    public ResponseEntity<List<Students>> getAll() {
        try {
            List<Students> stus = new ArrayList<Students>();

            studentRepo.findAll().forEach(stus::add);

            if (stus.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(stus, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<List<Students>> getById(@PathVariable("id") String id,
    // @RequestBody Students h) {
    // try {

    // List<Students> stus = new ArrayList<Students>();
    // studentRepo.findById(id).filter(null);

    // if (stus.isEmpty()) {
    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }
    // return new ResponseEntity<>(stus, HttpStatus.OK);

    // } catch (Exception e) {
    // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    // }
    // }

    @PostMapping("/create")
    public ResponseEntity<Students> createStudent(@RequestBody Students h) {
        try {
            Students _home = studentRepo.save(new Students(h.getName(), h.getAge()));
            return new ResponseEntity<>(_home, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Students> updateStudent(@PathVariable("id") String id,
            @RequestBody Students h) {
        try {
            Optional<Students> _home = studentRepo.findById(id);
            if (_home.isPresent()) {
                Students newStudent = _home.get();
                newStudent.setName(h.getName());
                newStudent.setAge(h.getAge());

                return new ResponseEntity<>(studentRepo.save(newStudent), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Students> deleteStudent(@PathVariable("id") String id) {
        try {
            studentRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
