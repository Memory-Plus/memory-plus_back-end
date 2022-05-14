package com.mandarin.mplus.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
