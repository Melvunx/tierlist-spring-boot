package com.melvunx.tierlist.controllers;

import com.melvunx.tierlist.services.TierListService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "tier-list")
@AllArgsConstructor
public class TierListController {
    private final TierListService tierListService;
}
