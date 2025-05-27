package com.melvunx.tierlist.services;

import com.melvunx.tierlist.entities.Classement;
import com.melvunx.tierlist.repositories.ClassementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClassementService {
    private ClassementRepository classementRepository;

    //Permet de créer un classement avec la méthode JPA DATA
    public void create(Classement classement) {
        classementRepository.save(classement);
    }

    //Renvoie la liste des classements
    public List<Classement> findAll() {
        return classementRepository.findAll();
    }

    public List<Classement> findAllClassementByRankList(Integer rankListId) {
        return classementRepository.findAllClassementByRankList(rankListId);
    }

    //Renvoie un classement selon son ID
    public Classement findById(Integer id){
        Optional<Classement> classement = this.classementRepository.findById(id);
        return classement.orElse(null);
    }
}
