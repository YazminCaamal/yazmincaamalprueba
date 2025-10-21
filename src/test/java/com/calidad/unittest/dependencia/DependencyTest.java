package com.calidad.unittest.dependencia;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.when;

import com.calidad.unittest.dependency.SubDependency;
import com.calidad.unittest.dependency.Dependency;

public class DependencyTest {
 private SubDependency subDependency;
 private Dependency dependency;

 
@BeforeEach
void setUp() throws Exception {
    //Setear mock
    subDependency = mock(SubDependency.class);
    dependency = new Dependency(subDependency);
}
@Test
void testSubDependencyClassName(){
    //System.out.println(subDependency.getClassName());
    //Resultado esperado
    //subDependency.suma(10);
    String esperado = "SubDependency.class";

    when(subDependency.getClassName()).thenReturn(esperado);
    //Ejercicio
    String resultadoEjecucion = dependency.getSubdependencyClassName();

    //Verificación
    assertThat(resultadoEjecucion,is(esperado));

    }
@Test
 void testSumaDos(){
    int esperado = 12;

    when(subDependency.addTwo(10)).thenReturn(12);
    
    //Ejercicio
    int resultadoEjecucion = subDependency.addTwo(10);

    //Verificación
    assertThat(resultadoEjecucion,is(esperado));
 }
 @Test
	void testAddTwo() {
	when(subDependency.addTwo(anyInt())).thenAnswer(new Answer<Integer>() {
		public Integer answer(InvocationOnMock invocation) throws Throwable {
			int arg = (Integer) invocation.getArguments()[0];
			return arg + 20;
		}
	});
	Integer resultadoEjecucion = subDependency.addTwo(100);
	assertThat(120, is(resultadoEjecucion));
	
	    }




        
    }

