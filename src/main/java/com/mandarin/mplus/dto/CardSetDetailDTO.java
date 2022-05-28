package com.mandarin.mplus.dto;

import com.mandarin.mplus.model.Card;
import com.mandarin.mplus.model.CardSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CardSetDetailDTO {

    private Long id;
    private String cardSetTitle;
    private String cardSetCreator;
    private String cardSetCategories;
    private List<Card> cardList;

    public CardSetDetailDTO(final CardSet cardSet) {
        this.id = cardSet.getId();
        this.cardSetTitle = cardSet.getCardSetTitle();
        this.cardSetCreator = cardSet.getCardSetCreator();
        this.cardSetCategories = cardSet.getCardSetCategories();
        this.cardList = cardSet.getCardList();
    }
}
