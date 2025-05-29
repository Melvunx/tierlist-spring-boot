package com.melvunx.tierlist.services;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.repositories.ClassementRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClassementService {
  private final ClassementRepository classementRepository;

    //Permet de créer un classement avec la méthode JPA DATA
   public void create(Classement classement) {
        classementRepository.save(classement);
    }

    //Renvoie la liste des classements
    public List<Classement> findAll() {
        return classementRepository.findAll();
    }

    public List<Classement> findAll(Sort sort) {
        return classementRepository.findAll(sort);
    }

    public List<Classement> findAllClassementByTierList(Integer TierListId) {
        return classementRepository.findAllClassementByTierListId(TierListId);
    }

    public List<Classement> findAllClassementByTierList(Integer TierListId, Sort sort) {
        return classementRepository.findAllClassementByTierListId(TierListId, sort);
    }

    //Renvoie un classement selon son ID
    public Classement findClassementById(Integer classementId){
        Optional<Classement> classement = this.classementRepository.findById(classementId);
        return classement.orElse(null);
    }

    public void addRanked(Integer classementId, Integer rankedId){
        Classement classement = this.findClassementById(classementId);
    }

    public void addMultipleRanked(Integer classementId, List<Integer> rankedIds) {
       rankedIds.forEach(rankedId -> {

       });
    }

    public void update(Integer classementId, Classement newClassement) {
       Classement classement = this.findClassementById(classementId);

       classement.setTitle(newClassement.getTitle());

       classementRepository.save(classement);
    }

    public void delete(Integer classementId) {
       classementRepository.deleteById(classementId);
    }

}
