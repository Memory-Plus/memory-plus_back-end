package com.mandarin.mplus.service;

import com.mandarin.mplus.model.Card;
import com.mandarin.mplus.model.CardSet;
import com.mandarin.mplus.persistence.CardRepository;
import com.mandarin.mplus.persistence.CardSetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CardSetRepository cardSetRepository;

    @Transactional
    public CardSet create(final Long cardSetId, final Card card) {

        CardSet cardSet = cardSetRepository.findById(cardSetId).get();
        card.setCardSet(cardSet);

        cardRepository.save(card);

        return cardSet;
    }

    @Transactional
    public CardSet update(final Card card) {
        Card entity = cardRepository.findById(card.getId()).get();

        entity.setCardTitle(card.getCardTitle());
        entity.setCardContent(card.getCardContent());

        cardRepository.save(entity);

        return entity.getCardSet();
    }

    @Transactional
    public List<Card> delete(final Long cardId) {
        try {
            cardRepository.deleteById(cardId);
        } catch (Exception e) {
            log.error("error deleting entity ", e);
            throw new RuntimeException("error deleting entity " + cardId);
        }

        return new ArrayList<>();
    }
}
