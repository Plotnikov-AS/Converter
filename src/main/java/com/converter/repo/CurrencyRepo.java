package com.converter.repo;

import com.converter.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CurrencyRepo extends JpaRepository<Currency, Long> {
    Currency findByCharcode(String charcode);

    List<Currency> findAll();

    @Query(value = "SELECT CHARCODE, NAME FROM CURRENCY ORDER BY NAME ASC",
            nativeQuery = true)
    List<Map<Object, Object>> findAllCharcodesAndNames();
}
