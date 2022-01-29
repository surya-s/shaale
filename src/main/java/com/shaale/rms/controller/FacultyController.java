package com.shaale.rms.controller;

import com.shaale.rms.dao.FacultyUpdateDAO;
import com.shaale.rms.entity.Faculty;
import com.shaale.rms.entity.Payment;
import com.shaale.rms.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private FacultyRepository facultyRepository;

    @Autowired
    public void setFacultyRepository(FacultyRepository facultyRepository){
        this.facultyRepository = facultyRepository;
    }

    @GetMapping
    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public List<Faculty> updatePayments(final @RequestBody List<FacultyUpdateDAO> list) {
        List<Faculty> toDelete = list.stream().filter(o -> o.getAction() == FacultyUpdateDAO.Action.DELETE)
                .map(FacultyUpdateDAO::getData).collect(Collectors.toList());
        List<Faculty> toUpdate = list.stream().filter(o -> o.getAction() == FacultyUpdateDAO.Action.UPDATE)
                .map(FacultyUpdateDAO::getData).collect(Collectors.toList());

        List<Faculty> result = new ArrayList<>();

        if(!toDelete.isEmpty()){
            facultyRepository.deleteInBatch(toDelete);
        }
        if(!toUpdate.isEmpty()){
            result = facultyRepository.saveAll(toUpdate);
        }
        return result;
    }
}
