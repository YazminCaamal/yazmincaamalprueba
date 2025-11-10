package com.calidad.login.service;

import static org.hamcrest.MatcherAssert.assertThat; 

import static org.hamcrest.Matchers.is; 

import org.junit.jupiter.api.Test; 

import static org.mockito.Mockito.mock; 

import static org.mockito.Mockito.when; 

 

import com.calidad.login.dao.IDAOLogin; 

import com.calidad.login.modelo.Usuario; 

import com.calidad.login.service.LoginService; 

 

public class LoginServiceTest { 

 

    private IDAOLogin idaoUser; 

    private Usuario usuario; 

    private LoginService login; // esta es la clase que vamos a probar 

    

 

    //@BeforeEach -> SETUP 

    //@Test -> PRUEBA 

 

    @Test 

    void loginExitosoTest(){ 

        //1. setup 

        String email = "micorreo@correo.com"; 

        String contrasenia = "contraseña123";  

        String nombre = "Juan"; 

 

        //Definir el mock del método findUsuarioByEmail 

        usuario = new Usuario (nombre, email, contrasenia); 

 

         //Crear mock de la dependencia 

        idaoUser = mock(IDAOLogin.class); 

         

        //definir el mock del método 

        when(idaoUser.findUsuarioByEmail(email)).thenReturn(usuario); 

 

        // Instanciar la clase que voy a probar 

        login = new LoginService(idaoUser); 

 

        //2. Ejercicio 

        //Aqui llamo al metodo que quiero probar 

        boolean resultadoEsperado = login.login(email, contrasenia); 

 

        //3. Verificación 

        boolean resultadoEjecucion = true; 

        assertThat(resultadoEsperado, is(resultadoEjecucion)); 

    } 

 

    //@AfterEach ->CleanUp 

 

} 

 

 