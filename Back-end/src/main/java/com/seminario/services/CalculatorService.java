package com.seminario.services;

import com.seminario.dtos.ResultCalculatorDTO;
import com.seminario.entitys.ExternalPorcentage;
import com.seminario.exceptions.NotPorcentageApiExcenption;

public interface CalculatorService {

    ResultCalculatorDTO sum(double num1, double num2) throws NotPorcentageApiExcenption;
    void updatePorcentage();
	ExternalPorcentage forceUpdatePorcentage();
}
