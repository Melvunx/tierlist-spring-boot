package com.melvunx.tierlist.controllers;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.entities.TierList;
import com.melvunx.tierlist.services.ClassementService;
import com.melvunx.tierlist.services.TierListService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/classement")
public class ClassementController {
    private final ClassementService classementService;
    private final TierListService tierListService;

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
    @GetMapping(path = "/{classementId}")
    public ResponseEntity<Classement> getClassementById(@PathVariable Integer classementId){
        Classement classement = this.classementService.findClassementById(classementId);
        if (classement == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(classement);
    }

    @GetMapping("/tier-list/{tierListId}")
    public ResponseEntity<List<Classement>> getAllClassementByTierList(
            @RequestParam Integer tierListId,
            @RequestParam(required = false) String sort
    ) {
        List<Classement> classements;
        TierList tierList = tierListService.findById(tierListId) ;

        if (tierList == null) return ResponseEntity.notFound().build();

        if (sort != null && !sort.isEmpty()) {
            try {
                Sort sortBy = Sort.by(sort);
                classements = this.classementService.findAllClassementByTierList(tierList, sortBy);
            } catch (Exception e) {
                classements = this.classementService.findAllClassementByTierList(tierList);
            }
        }
        else classements = this.classementService.findAllClassementByTierList(tierList);

        return ResponseEntity.ok(classements);
    }

    @PutMapping("/{classementId}")
    public ResponseEntity<Void> updateClassement(@PathVariable Integer classementId, @RequestBody Classement newClassement) {
        Classement classement = this.classementService.findClassementById(classementId);
        if (classement == null) return ResponseEntity.notFound().build();

        this.classementService.update(classementId, newClassement);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/add/{classementId}/tier-list/{tierListId}")
    public ResponseEntity<Void> addClassementByIdToTierList(@PathVariable Integer tierListId, @PathVariable Integer classementId) {
        TierList tierList = this.tierListService.findById(tierListId);
        Classement classement = this.classementService.findClassementById(classementId);

        if (classement == null) return ResponseEntity.notFound().build();
        if (tierList == null) return ResponseEntity.notFound().build();

        this.classementService.addClassementToTierList(tierList, classement);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/multiple-add/tier-list/{tierListId}")
    public ResponseEntity<Void> addMultipleClassementIdToTierList(@PathVariable Integer tierListId, @RequestBody List<Integer> classementIds) {
        TierList tierList = this.tierListService.findById(tierListId);
        if (tierList == null) return ResponseEntity.notFound().build();

        this.classementService.addMultipleClassementToTierList(tierList, classementIds);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/remove/{classementId}/tier-list/{tierListId}")
    public ResponseEntity<Void> removeClassementByIdFromTierList(@PathVariable Integer tierListId, @PathVariable Integer classementId) {
        TierList tierList = this.tierListService.findById(tierListId);
        Classement classement = this.classementService.findClassementById(classementId);

        if (tierList == null) return ResponseEntity.notFound().build();
        if (classement == null) return ResponseEntity.notFound().build();

        this.classementService.removeClassementFromTierList(tierList, classement);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/multiple-remove/tier-list/{tierListId}")
    public ResponseEntity<Void> removeMultipleClassementIdFromTierList(@PathVariable Integer tierListId, @RequestBody List<Integer> classementIds) {
        TierList tierList = this.tierListService.findById(tierListId);

        if (tierList == null) return ResponseEntity.notFound().build();

        this.classementService.removeMultipleClassementFromTierList(tierList, classementIds);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{classementId}")
    public ResponseEntity<Void> deleteClassement(@PathVariable Integer classementId) {
        Classement classement = this.classementService.findClassementById(classementId);
        if (classement == null) return ResponseEntity.notFound().build();

        this.classementService.delete(classement);
        return ResponseEntity.noContent().build();
    }
}
