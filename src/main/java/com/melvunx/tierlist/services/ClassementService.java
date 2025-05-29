package com.melvunx.tierlist.services;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.entities.TierList;
import com.melvunx.tierlist.repositories.ClassementRepository;
import com.melvunx.tierlist.repositories.TierListRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClassementService {
  private final ClassementRepository classementRepository;
  private final TierListRepository tierListRepository ;

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

    public List<Classement> findAllClassementByTierList(TierList tierList) {
        return classementRepository.findAllClassementByTierListId(tierList);
    }

    public List<Classement> findAllClassementByTierList(TierList tierList, Sort sort) {
        return classementRepository.findAllClassementByTierListId(tierList, sort);
    }

    //Renvoie un classement selon son ID
    public Classement findClassementById(Integer classementId){
        Optional<Classement> classement = this.classementRepository.findById(classementId);
        return classement.orElse(null);
    }

    public void update(Integer classementId, Classement newClassement) {
       if (!Objects.equals(classementId, newClassement.getId())) return;
       Classement classement = this.findClassementById(classementId);

       classement.setTitle(newClassement.getTitle());
       classementRepository.save(classement);
    }

    public void addClassementToTierList(TierList tierList, Classement classement) {
       if (!tierList.getClassements().contains(classement)) {
           tierList.getClassements().add(classement);
           classement.setTierList(tierList);
           this.tierListRepository.save(tierList);
       }
    }

    public void addMultipleClassementToTierList(TierList tierList, List<Integer> classementIds) {
       classementIds.forEach(classementId -> {
          Classement classement = this.findClassementById(classementId);

          if (classement != null && !tierList.getClassements().contains(classement)) {
              tierList.getClassements().add(classement);
              classement.setTierList(tierList);
          }
       });

       tierListRepository.save(tierList);
    }

    public void removeClassementFromTierList(TierList tierList, Classement classement) {
       if (tierList.getClassements().contains(classement)) {
           tierList.getClassements().remove(classement);
           classement.setTierList(null);
           this.tierListRepository.save(tierList);
       }
    }

    public void removeMultipleClassementFromTierList(TierList tierList, List<Integer> classementIds) {
       classementIds.forEach(classementId -> {
           Classement classement = this.findClassementById(classementId);

           if (classement != null && tierList.getClassements().contains(classement)) {
               tierList.getClassements().remove(classement);
               classement.setTierList(null);
           }
       });

       tierListRepository.save(tierList);
    }

    public void delete(Classement classement) {
       classementRepository.deleteById(classement.getId());
    }

}
