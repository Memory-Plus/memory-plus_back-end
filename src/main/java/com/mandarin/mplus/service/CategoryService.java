package com.mandarin.mplus.service;

import com.mandarin.mplus.model.CardSet;
import com.mandarin.mplus.persistence.CardSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CardSetRepository cardSetRepository;

    public List<CardSet> findCardSet(final String search, final String category) {

        if (search == null) {
            if (category == null) {
                // find all
                return cardSetRepository.findAll();
            } else {
                // find category
                return cardSetRepository.findByCardSetCategoriesContaining(category);
            }
        } else {
            if (category == null) {
                // find search
                return cardSetRepository.findByCardSetTitleContains(search);
            } else {
                // find category, search
                return cardSetRepository.findByCardSetTitleContainsAndCardSetCategoriesContains(search, category);
            }
        }
    }
}
