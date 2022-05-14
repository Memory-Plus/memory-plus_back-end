package com.mandarin.mplus.controller;

import com.mandarin.mplus.dto.CardSetDTO;
import com.mandarin.mplus.dto.ResponseDTO;
import com.mandarin.mplus.model.CardSet;
import com.mandarin.mplus.service.CardSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mylist")
@RequiredArgsConstructor
public class CardSetController {

    private final CardSetService cardSetService;

    @PostMapping
    public ResponseEntity<?> createCardSet(@RequestBody CardSetDTO dto) {

        CardSet cardSet = CardSetDTO.toEntity(dto);
        cardSet.setId(null);

        List<CardSet> entities = cardSetService.create(cardSet);

        List<CardSetDTO> dtos = entities.stream().map(CardSetDTO::new).collect(Collectors.toList());

        ResponseDTO<CardSetDTO> response = ResponseDTO.<CardSetDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }
}
