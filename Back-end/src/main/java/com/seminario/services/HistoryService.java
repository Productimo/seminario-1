package com.seminario.services;

import com.seminario.entitys.History;
import com.seminario.exceptions.NegativeParamException;

import java.util.List;

public interface HistoryService {

    List<History> getHistory(Integer pageNumber, Integer pageSize, String sortBy) throws NegativeParamException;

}
