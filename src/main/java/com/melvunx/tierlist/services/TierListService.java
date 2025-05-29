package com.melvunx.tierlist.services;

import com.melvunx.tierlist.entities.TierList;
import com.melvunx.tierlist.repositories.TierListRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TierListService {
    private final TierListRepository tierListRepository;

    public void create(TierList tierList) {
        tierListRepository.save(tierList);
    }

    public List<TierList> findAll() {
        return tierListRepository.findAll();
    }

    public List<TierList> findAll(Sort sort) {
        return tierListRepository.findAll(sort);
    }

    public TierList findById(Integer tierListId) {
        Optional<TierList> rankList = tierListRepository.findById(tierListId);
        return rankList.orElse(null);
    }

    public void update(Integer tierListId, TierList newTierList) {
        if (!Objects.equals(tierListId, newTierList.getId())) return;
        TierList tierList = this.findById(tierListId);

        tierList.setTitle(newTierList.getTitle());
        tierList.setAuthor(newTierList.getAuthor());
        tierList.setTheme(newTierList.getTheme());

        tierListRepository.save(tierList);
    }

    public void delete(TierList tierList) {
        tierListRepository.deleteById(tierList.getId());
    }
}
