package com.seminario.repositories;

import com.seminario.entitys.History;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HistoryRepository extends PagingAndSortingRepository<History, Long> {
}