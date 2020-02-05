package com.converter.repo;

import com.converter.model.ConvertHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConvertHistoryRepo extends JpaRepository<ConvertHistory, Long> {
    Optional<ConvertHistory> findById(Long id);

    List<ConvertHistory> findAllByUserId(Long id);
}
