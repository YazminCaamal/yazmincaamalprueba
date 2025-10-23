package com.calidad.login.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.calidad.login.dao.IDAOLogin;
import com.calidad.login.modelo.Usuario;

public class LoginServiceTest {
    private IDAOLogin idaologin;
    private Usuario usuario;
    private LoginService login; //esta es la clase que vamos a probar
    
//@BeforeEach --> SETUP

//Test --> PRUEBA
    @Test
    void loginExitosoTest(){
        String email = "yaz@gmail.com";
        String password = "contraseña 123";
        usuario = new Usuario(email, password);
        //String esperado = "idaologin.class";
        //Definir el mock del método findUserByEmail
        //Crear mock de la dependencia
        idaologin = mock(IDAOLogin.class);
        //
        when(idaologin.findUserByEmail(email)).thenReturn(usuario);

        //Instanciar la clase que voy a probar
        login = new LoginService(idaologin);
        //2. ejercicio
        Boolean resultadoEjecucion = login.login(email, password);
        //verificación
        Boolean resultadoEsperado = true;
        assertThat(resultadoEsperado,is(resultadoEjecucion));

    }
//@AfterEach clean up






}
