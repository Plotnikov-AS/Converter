package com.converter.repo;

import com.converter.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyRepo extends JpaRepository<Currency, Long> {
    Currency findByCharcode(String charcode);

    List<Currency> findAll();
}
