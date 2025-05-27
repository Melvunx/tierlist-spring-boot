package com.melvunx.tierlist.services;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.entities.Ranked;
import com.melvunx.tierlist.repositories.RankedRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RankedService {
    private RankedRepository rankedRepository;

    public void create(Ranked ranked) {
        rankedRepository.save(ranked);
    }

    public List<Ranked> findAll() {
        return rankedRepository.findAll();
    }

    public List<Ranked> findAllByClassement(Classement classement) {
        int classementId = classement.getId();
    }

    public Ranked findById(int id) {
        Optional<Ranked> ranked = rankedRepository.findById(id);
        return ranked.isPresent() ? ranked.get() : null;
    }
}
