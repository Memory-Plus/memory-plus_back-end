package com.mandarin.mplus.service;

import com.mandarin.mplus.model.CardSet;
import com.mandarin.mplus.persistence.CardSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardSetService {

    private final CardSetRepository cardSetRepository;

    public List<CardSet> create(final CardSet cardSet) {
        cardSetRepository.save(cardSet);
        return cardSetRepository.findByCardSetCreator(cardSet.getCardSetCreator());
    }

    public List<CardSet> update(final Long cardSetId, final CardSet cardSet) {
        final Optional<CardSet> original = cardSetRepository.findById(cardSetId);

        original.ifPresent(entity -> {
            entity.setCardSetTitle(cardSet.getCardSetTitle());
            entity.setCardSetCategories(cardSet.getCardSetCategories());

            cardSetRepository.save(entity);
        });

        return cardSetRepository.findByCardSetCreator(cardSet.getCardSetCreator());
    }
}
