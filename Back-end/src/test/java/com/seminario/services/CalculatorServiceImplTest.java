package com.seminario.services;

import com.seminario.dtos.ResultCalculatorDTO;
import com.seminario.entitys.ExternalPorcentage;
import com.seminario.exceptions.NotPorcentageApiExcenption;
import com.seminario.repositories.ExternalPorcentageRepository;
import com.seminario.services.impl.CalculatorServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorServiceImplTest {

    private CalculatorService calculator;

    private ExternalPorcentageRepository expRepository;
    
    @BeforeEach
    public void setUp() {
    	expRepository = mock(ExternalPorcentageRepository.class);
    	calculator = new CalculatorServiceImpl(expRepository);
    }
    
    @Test
    public void GivenSum_WhenValuesAndPorcentageNotExist_ShouldReturnException() {
        // Arrange
        double a = 1.0;
        double b = 2.0;
        // Act
       
		assertThrows(NotPorcentageApiExcenption.class, () ->calculator.sum(a, b));
		
    }
    
    @Test
    public void GivenSum_WhenValuesAndPorcentageExist_ShouldReturnException() {
        // Arrange
        double a = 10.0;
        double b = 20.0;
        ExternalPorcentage porcentage = new ExternalPorcentage(10D);
        Double sum = a + b;
        Double porcent = (a + b) * (porcentage.getPorcentage() / 100);
        ResultCalculatorDTO expectedResult = new ResultCalculatorDTO(sum + porcent);
        when(expRepository.findTopByOrderByIdDesc()).thenReturn(porcentage);
        
        // Act
        ResultCalculatorDTO result;
		try {
			result = calculator.sum(a, b);
		} catch (NotPorcentageApiExcenption e) {
			result = new ResultCalculatorDTO(0.0);
		}

        // Assert
        assertEquals(result, expectedResult);
    }

}
