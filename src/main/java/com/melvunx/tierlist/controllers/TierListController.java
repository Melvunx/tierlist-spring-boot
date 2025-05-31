package com.melvunx.tierlist.controllers;

import com.melvunx.tierlist.entities.TierList;
import com.melvunx.tierlist.services.TierListService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tier-list")
@AllArgsConstructor
public class TierListController {
    private final TierListService tierListService;

    @PostMapping
    public ResponseEntity<Void> createTierList(@RequestBody TierList tierList) {
        this.tierListService.create(tierList);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TierList>> getAllTierList(@RequestParam(required = false) String sort) {
        List<TierList> lists;
        if (sort != null && !sort.isEmpty()) {
            try {
                Sort sortBy = Sort.by(sort);
                lists = this.tierListService.findAll(sortBy);
            } catch (Exception e) {
                lists = this.tierListService.findAll();
            }
        }
        else lists = this.tierListService.findAll();

        return ResponseEntity.ok(lists);
    }

    @GetMapping("/{tierListId}")
    public ResponseEntity<TierList> getTierListById(@RequestParam Integer tierListId) {
        TierList tierList = this.tierListService.findById(tierListId);

        if (tierList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(tierList);
    }

    @PutMapping("/{tierListId}")
    public ResponseEntity<Void> updateTierList(@RequestParam Integer tierListId, @RequestBody TierList newTierList) {
        TierList tierList = this.tierListService.findById(tierListId);
        if (tierList == null) return ResponseEntity.notFound().build();

        this.tierListService.update(tierListId, newTierList);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{tierListId}")
    public ResponseEntity<Void> deleteTierList(@RequestParam Integer tierListId) {
        TierList tierList = this.tierListService.findById(tierListId);
        if (tierList == null) return ResponseEntity.notFound().build();

        this.tierListService.delete(tierList);
        return ResponseEntity.noContent().build();
    }
}
