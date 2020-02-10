package com.converter.repo;

import com.converter.model.ConvertHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ConvertHistoryRepo extends JpaRepository<ConvertHistory, Long> {
    Optional<ConvertHistory> findById(Long id);

    @Query(value = "SELECT * FROM convert_history ch where ch.from_cur = :fromCur and ch.to_cur = :toCur",
            nativeQuery = true)
    List<ConvertHistory> findAllByFromAndTo(@Param("fromCur") String fromName,
                                            @Param("toCur") String toName);

    List<ConvertHistory> findAllByDateAndFromCurrencyAndToCurrencyOrderByDate(Date date, String fromCurrency, String toCurrency);

    List<ConvertHistory> findTop5ByDateOrderByTimeDesc(Date date);

    @Query(value = "SELECT convert_id FROM convert_history ORDER BY convert_id DESC LIMIT 1",
            nativeQuery = true)
    Long findLastId();

}
