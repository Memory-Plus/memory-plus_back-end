package com.mandarin.mplus.persistence;

import com.mandarin.mplus.model.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardSetRepository extends JpaRepository<CardSet, Long> {
    List<CardSet> findByCardSetCreator(String cardSetCreator);
    List<CardSet> findByCardSetTitleContains(String search);
    List<CardSet> findByCardSetCategoriesContaining(String category);
    List<CardSet> findByCardSetTitleContainsAndCardSetCategoriesContains(String search, String category);
}
