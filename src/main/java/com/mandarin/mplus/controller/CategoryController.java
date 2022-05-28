package com.mandarin.mplus.controller;

import com.mandarin.mplus.dto.CardSetDTO;
import com.mandarin.mplus.dto.ResponseDTO;
import com.mandarin.mplus.model.CardSet;
import com.mandarin.mplus.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> retrieveList(@RequestParam(required = false) String search, @RequestParam(required = false) String category) {

        List<CardSet> entities = categoryService.findCardSet(search, category);

        List<CardSetDTO> dtos = entities.stream().map(CardSetDTO::new).collect(Collectors.toList());

        ResponseDTO<CardSetDTO> response = ResponseDTO.<CardSetDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }
}
