package com.melvunx.tierlist.services;

import com.melvunx.tierlist.entities.RankList;
import com.melvunx.tierlist.repositories.RankListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RankListService {
    private RankListRepository rankListRepository;

    public void create(RankList rankList) {
        rankListRepository.save(rankList);
    }

    public List<RankList> findAll() {
        return rankListRepository.findAll();
    }

    public RankList findById(Integer id) {
        Optional<RankList> rankList = rankListRepository.findById(id);
        return rankList.orElse(null);
    }
}
