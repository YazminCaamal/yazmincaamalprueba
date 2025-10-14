package com.calidad.unittest.calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;

public class CalculadoraTest {

    public double operador1;
    public double operador2;
    public Calculadora calc;

    @BeforeEach //Se ejecuta antes del inicio de cada método
    public void setup(){
        operador1 = 10;
        operador2 = 5;
        calc = new Calculadora();
        System.out.println("Inicializando....");
    }
    @AfterEach //Se ejecuta al final de cada método
    public void cleanUp(){
        System.out.println("Prueba finalizada");
    }

    @Test
     void testSumaNumeroPositivos () {
        //1. Inicializar los datos
        double resultadoEsperado = 15;
        //2. Ejercitar el código
        double resultado = calc.suma(operador1, operador2);
        //3. Verificar
        assertThat(resultadoEsperado, is(resultado));

     }

     @Test
     void testRestaNumeroPositivos () {
        //1. Inicializar los datos
        double resultadoEsperado = 5;
        //2. Ejercitar el código
        double resultado = calc.resta(operador1, operador2);
        //3. Verificar
         assertThat(resultadoEsperado, is(resultado));
     }

     @Test
     void testDivideNumeroPositivos () {
        //1. Inicializar los datos
        double resultadoEsperado = 2;
        //2. Ejercitar el código
        double resultado = calc.divide(operador1, operador2);
        //3. Verificar
         assertThat(resultadoEsperado, is(resultado));

     }

     @Test
     void testMultiplicacionNumeroPositivos () {
        //1. Inicializar los datos
        double resultadoEsperado = 50;
        //2. Ejercitar el código
        double resultado = calc.multiplica(operador1, operador2);
        //3. Verificar
         assertThat(resultadoEsperado, is(resultado));

     }

     /*@Test(expected = NullPointerException.class)
     public void whenExceptionThrow_thenExpectationSatisfied(){
        String test = null;
        test.length();
     }

     @Test
     void testInvalidInputThrowsException(){
        assertThrows(IllegalArgumentException.class, () ->{
           someMethodThatThrowsException(-1);
        });
    }
    private void someMethodThatThrowsException(int value){
        if (value < 0){
            throw new IllegalArgumentException("Value cannot be negative");
        }
    }*/


}
