package com.mandarin.mplus.controller;

import com.mandarin.mplus.dto.CardSetDTO;
import com.mandarin.mplus.dto.ResponseDTO;
import com.mandarin.mplus.model.CardSet;
import com.mandarin.mplus.service.CardSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mylist")
@RequiredArgsConstructor
public class CardSetController {

    private final CardSetService cardSetService;

    @GetMapping
    public ResponseEntity<?>  retrieveList(@RequestParam(required = false) String search) {

        List<CardSet> entities = cardSetService.retrieve(search);

        List<CardSetDTO> dtos = entities.stream().map(CardSetDTO::new).collect(Collectors.toList());

        ResponseDTO<CardSetDTO> response = ResponseDTO.<CardSetDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<?> createCardSet(@RequestBody CardSetDTO dto) {

        CardSet cardSet = CardSetDTO.toEntity(dto);
        cardSet.setId(null);

        List<CardSet> entities = cardSetService.create(cardSet);

        List<CardSetDTO> dtos = entities.stream().map(CardSetDTO::new).collect(Collectors.toList());

        ResponseDTO<CardSetDTO> response = ResponseDTO.<CardSetDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping()
    public ResponseEntity<?> updateCardSet(@RequestBody CardSetDTO dto) {
        CardSet cardSet = CardSetDTO.toEntity(dto);

        List<CardSet> entities = cardSetService.update(cardSet.getId(), cardSet);

        List<CardSetDTO> dtos = entities.stream().map(CardSetDTO::new).collect(Collectors.toList());

        ResponseDTO<CardSetDTO> response = ResponseDTO.<CardSetDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{cardSetId}")
    public ResponseEntity<?> deleteCardSet(@PathVariable Long cardSetId) {

        try {
            List<CardSet> entities = cardSetService.delete(cardSetId);
            List<CardSetDTO> dtos = entities.stream().map(CardSetDTO::new).collect(Collectors.toList());
            ResponseDTO<CardSetDTO> response = ResponseDTO.<CardSetDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<CardSetDTO> response = ResponseDTO.<CardSetDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
