package com.mandarin.mplus.controller;

import com.mandarin.mplus.dto.CardDTO;
import com.mandarin.mplus.dto.CardSetDetailDTO;
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

    private final CardService cardService;
    private final CardSetRepository cardSetRepository;

    @GetMapping("/{cardSetId}")
    public ResponseEntity<?> retrieveCards(@PathVariable Long cardSetId) {

        CardSet cardSet = cardSetRepository.findById(cardSetId).get();

        if (cardSet == null) {
            return ResponseEntity.badRequest().body(null);
        }

        CardSetDetailDTO dto = new CardSetDetailDTO(cardSet);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/{cardSetId}")
    public ResponseEntity<?> createCard(@PathVariable Long cardSetId, @RequestBody CardDTO dto) {
        Card card = CardDTO.toEntity(dto);
        card.setId(null);

        CardSet cardSet = cardService.create(cardSetId, card);

        List<Card> entities = cardSet.getCardList();
        List<CardDTO> dtos = entities.stream().map(CardDTO::new).collect(Collectors.toList());
        ResponseDTO<CardDTO> response = ResponseDTO.<CardDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateCard(@RequestBody CardDTO dto) {
        Card card = CardDTO.toEntity(dto);

        CardSet cardSet = cardService.update(card);

        List<Card> entities = cardSet.getCardList();
        List<CardDTO> dtos = entities.stream().map(CardDTO::new).collect(Collectors.toList());
        ResponseDTO<CardDTO> response = ResponseDTO.<CardDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable Long cardId) {
        try {
            List<Card> entities = cardService.delete(cardId);
            List<CardDTO> dtos = entities.stream().map(CardDTO::new).collect(Collectors.toList());
            ResponseDTO<CardDTO> response = ResponseDTO.<CardDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<CardDTO> response = ResponseDTO.<CardDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
