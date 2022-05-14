package com.mandarin.mplus.service;

import com.mandarin.mplus.model.CardSet;
import com.mandarin.mplus.persistence.CardSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardSetService {

    private final CardSetRepository cardSetRepository;

    public List<CardSet> create(final CardSet cardSet) {
        cardSetRepository.save(cardSet);
        return cardSetRepository.findByCardSetCreator(cardSet.getCardSetCreator());
    }
}
