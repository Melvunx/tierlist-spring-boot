package com.melvunx.tierlist.services;

import com.melvunx.tierlist.entities.Ranked;
import com.melvunx.tierlist.repositories.RankedRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RankedService {
    private final RankedRepository rankedRepository;

    public void create(Ranked ranked) {
        rankedRepository.save(ranked);
    }

    public List<Ranked> findAll(Sort sort) {
        return rankedRepository.findAll(sort);
    }

    public List<Ranked> findAll() {
        return rankedRepository.findAll();
    }

    public List<Ranked> findAllByClassement(Integer classementId) {
        return rankedRepository.findByClassementId(classementId);
    }

    public List<Ranked> findAllByClassement(Integer classementId, Sort sort) {
        return rankedRepository.findByClassementId(classementId, sort);
    }

    public Ranked findById(Integer id) {
        Optional<Ranked> ranked = rankedRepository.findById(id);
        return ranked.orElse(null);
    }

    public void update(Integer rankedId, Ranked newRanked) {
        Ranked ranked = this.findById(rankedId);

        ranked.setName(newRanked.getName());
        ranked.setImage(newRanked.getImage());

        rankedRepository.save(ranked);
    }

    public void delete(Integer rankedId) {
        rankedRepository.deleteById(rankedId);
    }
}
