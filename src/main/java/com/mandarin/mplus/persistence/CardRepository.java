package com.mandarin.mplus.persistence;

import com.mandarin.mplus.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
