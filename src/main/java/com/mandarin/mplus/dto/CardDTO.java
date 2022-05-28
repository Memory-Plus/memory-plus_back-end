package com.mandarin.mplus.dto;

import com.mandarin.mplus.model.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardDTO {

    private Long cardId;
    private String cardTitle;
    private String cardContent;

    public CardDTO(final Card card) {
        this.cardId = card.getId();
        this.cardTitle = card.getCardTitle();
        this.cardContent = card.getCardContent();
    }

    public static Card toEntity(final CardDTO dto) {
        return Card.builder()
                .id(dto.getCardId())
                .cardTitle(dto.getCardTitle())
                .cardContent(dto.getCardContent())
                .build();
    }
}
