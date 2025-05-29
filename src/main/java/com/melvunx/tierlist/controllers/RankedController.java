package com.melvunx.tierlist.controllers;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.entities.Ranked;
import com.melvunx.tierlist.services.ClassementService;
import com.melvunx.tierlist.services.RankedService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/ranked")
public class RankedController {
    private final RankedService rankedService;
    private final ClassementService classementService;

    @GetMapping
    public ResponseEntity<List<Ranked>> getAllRanked(@RequestParam(required = false) String sort) {
        List<Ranked> rankedList;
        if (sort != null && !sort.isEmpty()) {
            try {
                Sort sortBy = Sort.by(sort);
                rankedList = this.rankedService.findAll(sortBy);
            } catch (Exception e) {
                rankedList = this.rankedService.findAll();
            }
        }
        else rankedList = this.rankedService.findAll();

        return ResponseEntity.ok(rankedList);
    }

    @GetMapping("/{rankedId}")
    public ResponseEntity<Ranked> getRanked(@PathVariable Integer rankedId) {
        var ranked = rankedService.findById(rankedId);
        if (ranked == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ranked);
    }

    @GetMapping("/classement/{classementId}")
    public ResponseEntity<List<Ranked>> findAllRankedByClassement(
            @PathVariable Integer classementId,
            @RequestParam(required = false) String sort
    ) {
        List<Ranked> rankedList;
        if (sort != null && !sort.isEmpty()) {
            try {
                Sort sortBy = Sort.by(sort);
                rankedList = this.rankedService.findAllByClassement(classementId, sortBy);
            } catch (Exception e) {
                rankedList = this.rankedService.findAllByClassement(classementId);
            }
        }
        else rankedList = this.rankedService.findAllByClassement(classementId);

        return ResponseEntity.ok(rankedList);
    }

    @PostMapping
    public ResponseEntity<Void> createRanked(@RequestBody Ranked ranked){
        this.rankedService.create(ranked);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{rankedId}")
    public ResponseEntity<Void> updateRanked(@PathVariable Integer rankedId, @RequestBody Ranked ranked){
        var rankedServiceById = rankedService.findById(rankedId);
        if (rankedServiceById == null) {
            return ResponseEntity.notFound().build();
        }

        this.rankedService.update(rankedId, ranked);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/add/{rankedId}/classement/{classementId}")
    public ResponseEntity<Void> addRankedByIdToClassement(@PathVariable Integer classementId, @PathVariable Integer rankedId) {
        Classement classement = classementService.findClassementById(classementId);
        Ranked ranked = rankedService.findById(rankedId);

        if (ranked == null) return ResponseEntity.notFound().build();

        if (classement == null) return ResponseEntity.notFound().build();

        this.rankedService.addRankedToClassement(classement, ranked);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/multiple-add/classement/{classementId}")
    public ResponseEntity<Void> addMultipleRankedById(@PathVariable Integer classementId, @RequestBody List<Integer> rankedIds) {
        Classement classement = classementService.findClassementById(classementId);
        if (classement == null) return ResponseEntity.notFound().build();

        this.rankedService.addMultipleRankedToClassement(classement, rankedIds);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/remove/{rankedId}/classement/{classementId}")
    public ResponseEntity<Void> removeRankedByIdFromClassement(@PathVariable Integer classementId, @PathVariable Integer rankedId) {
        Classement classement = classementService.findClassementById(classementId);
        Ranked ranked = rankedService.findById(rankedId);

        if (ranked == null) return ResponseEntity.notFound().build();
        if (classement == null) return ResponseEntity.notFound().build();

        this.rankedService.removeRankedFromClassement(classement, ranked);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/multiple-remove/{rankedId}/classement/{classementId}")
    public ResponseEntity<Void> removeMultipleRankedById(@PathVariable Integer classementId, @RequestBody List<Integer> rankedIds) {
        Classement classement = classementService.findClassementById(classementId);

        if (classement == null) return ResponseEntity.notFound().build();

        this.rankedService.removeMultipleRankedFromClassement(classement, rankedIds);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{rankedId}")
    public ResponseEntity<Void> deleteRanked(@PathVariable Integer rankedId){
        Ranked ranked = rankedService.findById(rankedId);
        if (ranked == null) return ResponseEntity.notFound().build();

        this.rankedService.delete(ranked);
        return ResponseEntity.noContent().build();
    }
}
