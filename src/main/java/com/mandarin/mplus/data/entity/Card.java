package com.mandarin.mplus.data.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Card {

    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardTitle;

    private String cardContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardset_id")
    private CardSet cardSet;
}
