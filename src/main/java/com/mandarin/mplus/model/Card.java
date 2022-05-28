package com.mandarin.mplus.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private CardSet cardSet;
}
