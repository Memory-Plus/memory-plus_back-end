package com.mandarin.mplus.dto;

import com.mandarin.mplus.model.CardSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardSetDTO {

    private Long id;
    private String cardSetTitle;
    private String cardSetCreator;
    private String cardSetCategories;

    public CardSetDTO(final CardSet cardSet) {
        this.id = cardSet.getId();
        this.cardSetTitle = cardSet.getCardSetTitle();
        this.cardSetCreator = cardSet.getCardSetCreator();
        this.cardSetCategories = cardSet.getCardSetCategories();
    }

    public static CardSet toEntity(final CardSetDTO dto) {
        return CardSet.builder()
                .id(dto.getId())
                .cardSetTitle(dto.getCardSetTitle())
                .cardSetCreator(dto.getCardSetCreator())
                .cardSetCategories(dto.getCardSetCategories())
                .build();
    }
}
