package com.melvunx.tierlist.controllers;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.services.ClassementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(path = "classement")
public class ClassementController {
    private ClassementService classementService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    //Requête pour créer un classement
    public void create(@RequestBody Classement classement) {
        this.classementService.create(classement);
    }

    //Requête pour afficher tous les classements
    public List<Classement> getAll() {
        return this.classementService.findAll();
    }

    //Requête pour afficher un classement par ID spécifique
    @GetMapping(path = "{id}")
    public Classement getById(@PathVariable int id){
        return this.classementService.findById(id);
    }
}
