package com.melvunx.tierlist.services;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.entities.Ranked;
import com.melvunx.tierlist.repositories.ClassementRepository;
import com.melvunx.tierlist.repositories.RankedRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RankedService {
    private final RankedRepository rankedRepository;
    private final ClassementRepository classementRepository;

    public void create(Ranked ranked) {
        rankedRepository.save(ranked);
    }

    public void addRankedToClassement(Classement classement, Ranked ranked) {

//        Si le ranked n'est pas à l'intérieur du classement, on l'ajoute
        if (!classement.getRankedItems().contains(ranked)) {
            classement.getRankedItems().add(ranked);
            ranked.setClassement(classement);
            this.classementRepository.save(classement);
        }
    }

    public void addMultipleRankedToClassement(Classement classement, List<Integer> rankedIds) {
        rankedIds.forEach(rankedId -> {
           Ranked ranked = this.findById(rankedId);

           if (ranked != null && !classement.getRankedItems().contains(ranked)) {
               classement.getRankedItems().add(ranked);
               ranked.setClassement(classement);
           }
        });

        this.classementRepository.save(classement);
    }

    public void removeRankedFromClassement(Classement classement, Ranked ranked) {

        // Si le ranked est à l'intérieur du classement, on le retire
        if (classement.getRankedItems().contains(ranked)) {
            classement.getRankedItems().remove(ranked);
            ranked.setClassement(null);
            this.classementRepository.save(classement);
        }
    }

    public void removeMultipleRankedFromClassement(Classement classement, List<Integer> rankedIds) {
        rankedIds.forEach(rankedId -> {
            Ranked ranked = this.findById(rankedId);

            if (ranked != null && classement.getRankedItems().contains(ranked)) {
                classement.getRankedItems().remove(ranked);
                ranked.setClassement(null);
            }
        });

        this.classementRepository.save(classement);
    }

    public List<Ranked> findAll(Sort sort) {
        return rankedRepository.findAll(sort);
    }

    public List<Ranked> findAll() {
        return rankedRepository.findAll();
    }

    public List<Ranked> findAllByClassement(Classement classement) {
        return rankedRepository.findByClassementId(classement);
    }

    public List<Ranked> findAllByClassement(Classement classement, Sort sort) {
        return rankedRepository.findByClassementId(classement, sort);
    }

    public Ranked findById(Integer rankedId) {
        Optional<Ranked> ranked = rankedRepository.findById(rankedId);
        return ranked.orElse(null);
    }

    public void update(Integer rankedId, Ranked newRanked) {
        if (!Objects.equals(rankedId, newRanked.getId())) return;

        Ranked ranked = this.findById(rankedId);

        ranked.setName(newRanked.getName());
        ranked.setImage(newRanked.getImage());

        rankedRepository.save(ranked);
    }

    public void delete(Ranked ranked) {
        rankedRepository.deleteById(ranked.getId());
    }
}
