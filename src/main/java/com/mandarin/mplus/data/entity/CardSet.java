package com.mandarin.mplus.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class CardSet {

    @Id
    @Column(name = "cardset_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardSetTitle;

    private String cardSetCategories;

    @OneToMany(mappedBy = "cardSet", cascade = CascadeType.ALL)
    private List<Card> cardList = new ArrayList<>();
}
