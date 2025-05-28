package com.melvunx.tierlist.controllers;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.services.ClassementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/classement")
public class ClassementController {
    private final ClassementService classementService;

    //Requête pour créer un classement
    @PostMapping
    public ResponseEntity<Void> createClassement(@RequestBody Classement classement) {
        this.classementService.create(classement);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //Requête pour afficher tous les classements
    @GetMapping
    public ResponseEntity<List<Classement>> getAllClassement(@RequestParam(required = false) String sort) {
        List<Classement> classements;
        if (sort != null && !sort.isEmpty()) {
            try {
                Sort sortBy = Sort.by(sort);
                classements = this.classementService.findAll(sortBy);
            } catch (Exception e) {
                classements = this.classementService.findAll();
            }
        }
        else classements = this.classementService.findAll();

        return ResponseEntity.ok(classements);
    }

    //Requête pour afficher un classement par ID spécifique
    @GetMapping(path = "/{id}")
    public ResponseEntity<Classement> getClassementById(@PathVariable Integer id){
        var classement = this.classementService.findById(id);
        if (classement == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(classement);
    }

    public ResponseEntity<List<Classement>> getAllClassementByTierList(
            @RequestParam Integer tierListId,
            @RequestParam(required = false) String sort
    ) {
        List<Classement> classements;
        if (sort != null && !sort.isEmpty()) {
            try {
                Sort sortBy = Sort.by(sort);
                classements = this.classementService.findAllClassementByTierList(tierListId, sortBy);
            } catch (Exception e) {
                classements = this.classementService.findAllClassementByTierList(tierListId);
            }
        }
        else classements = this.classementService.findAllClassementByTierList(tierListId);

        return ResponseEntity.ok(classements);
    }


}
