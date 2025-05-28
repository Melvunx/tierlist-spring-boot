package com.melvunx.tierlist.controllers;

import com.melvunx.tierlist.entities.Ranked;
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

    @GetMapping("/{id}")
    public ResponseEntity<Ranked> getRanked(@PathVariable Integer id) {
        var ranked = rankedService.findById(id);
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

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRanked(@PathVariable Integer rankedId, @RequestBody Ranked ranked){
        var rankedServiceById = rankedService.findById(rankedId);
        if (rankedServiceById == null) {
            return ResponseEntity.notFound().build();
        }

        this.rankedService.update(rankedId, ranked);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRanked(@PathVariable Integer rankedId){
        var ranked = rankedService.findById(rankedId);
        if (ranked == null) {
            return ResponseEntity.notFound().build();
        }

        this.rankedService.delete(rankedId);
        return ResponseEntity.noContent().build();
    }
}
