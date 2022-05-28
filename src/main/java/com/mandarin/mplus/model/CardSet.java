package com.mandarin.mplus.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private List<Card> cardList = new ArrayList<>();
}
