package com.mandarin.mplus.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardSet {

    @Id
    @Column(name = "cardset_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardSetTitle;

    private String cardSetCategories;

    private String cardSetCreator;

    @OneToMany(mappedBy = "cardSet", cascade = CascadeType.ALL)
    private List<Card> cardList = new ArrayList<>();
}
