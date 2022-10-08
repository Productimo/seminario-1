package com.seminario.repositories;

import com.seminario.entitys.History;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface HistoryRepository extends PagingAndSortingRepository<History, Long> {
}