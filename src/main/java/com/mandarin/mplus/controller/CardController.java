package com.mandarin.mplus.controller;

import com.mandarin.mplus.dto.CardDTO;
import com.mandarin.mplus.dto.ResponseDTO;
import com.mandarin.mplus.model.Card;
import com.mandarin.mplus.model.CardSet;
import com.mandarin.mplus.persistence.CardSetRepository;
import com.mandarin.mplus.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

        List<Card> entities = cardSet.getCardList();
        List<CardDTO> dtos = entities.stream().map(CardDTO::new).collect(Collectors.toList());
        ResponseDTO<CardDTO> response = ResponseDTO.<CardDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }


        return ResponseEntity.ok().body(response);
    }
}
