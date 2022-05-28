package com.mandarin.mplus.controller;

import com.mandarin.mplus.dto.ResponseDTO;
import com.mandarin.mplus.model.CardSet;
import com.mandarin.mplus.persistence.CardSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cardset")
@RequiredArgsConstructor
public class CardController {

    private final CardSetRepository cardSetRepository;

    @GetMapping("/{cardSetId}")
    public ResponseEntity<?> retrieveCards(@PathVariable Long cardSetId) {

        CardSet cardSet = cardSetRepository.findById(cardSetId).get();

        if (cardSet == null) {
            return ResponseEntity.badRequest().body(null);
        }

        List<CardSet> dtos = new ArrayList<>();
        dtos.add(cardSet);
        ResponseDTO<CardSet> response = ResponseDTO.<CardSet>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }
}
