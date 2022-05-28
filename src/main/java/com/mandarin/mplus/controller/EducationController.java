package com.mandarin.mplus.controller;

import com.mandarin.mplus.dto.CardSetDetailDTO;
import com.mandarin.mplus.model.CardSet;
import com.mandarin.mplus.persistence.CardSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
public class EducationController {

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
}
