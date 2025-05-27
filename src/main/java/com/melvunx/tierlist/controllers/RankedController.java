package com.melvunx.tierlist.controllers;

import com.melvunx.tierlist.entities.Ranked;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "ranked")
public class RankedController {
    private RankedController rankedController;

    public void create(@RequestBody Ranked ranked){
        this.rankedController.create(ranked);
    }

    public List<Ranked> getAll(){
        return this.rankedController.getAll();
    }
}
