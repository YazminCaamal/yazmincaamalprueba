package com.calidad.prueba.unnittest.login.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.calidad.login.dao.IDAOLogin;
import com.calidad.login.modelo.Usuario;
import com.calidad.login.service.LoginService;

public class LoginServiceTest {
    private IDAOLogin idaologin;
    private Usuario usuario;
    private LoginService login; // Esta es la clase que vamos a probar.

    // @BeforeEach -> SETUP

    // @Test -> PRUEBA
    @Test
    void LoginExitosoTest(){
        // SETUP
        String email = "correo@gmail.com";
        String pass = "pass1234";
            // Mock findUsuarioByEmail
        usuario = new Usuario(email,"Yazmin",pass);
            // Mock de la dependencia
        idaologin = mock(IDAOLogin.class);
        when(idaologin.findUsuarioByEmail(email)).thenReturn(usuario);
            // Instancia la clase a probar
        login = new LoginService(idaologin);
            // EJERCICIO
        Boolean resultadoesperado = login.login(email, pass);
        Boolean resultadoejecucion = true;
            // VERIFICACIÓN
        assertThat(resultadoesperado,is(resultadoejecucion));
    }

    @Test
void LoginFallido() {
    // SETUP    
    String email = "correo@gmail.com";
    String pass = "contraseña_incorrecta";
    
    usuario = new Usuario(email, "Yazmin", "pass12");
    idaologin = mock(IDAOLogin.class);
    when(idaologin.findUsuarioByEmail(email)).thenReturn(usuario);
    
    login = new LoginService(idaologin);

    // Ejercicio 
    Boolean resultadoes = login.login(email, pass); // debería devolver false
    Boolean resultadoej = false;    

    // Verificación
    assertThat(resultadoes, is(resultadoej));
}


    @Test
    void LoginInexistente(){
        // SETUP  
        String email = "correo@gmail.com";
        String pass =  "pass12";
            // Mock findUsuarioByEmail
        usuario = null;
            // Mock de la dependencia
        idaologin = mock(IDAOLogin.class);    
        when(idaologin.findUsuarioByEmail(email)).thenReturn(usuario);
            // Instancia la clase a probar
        login = new LoginService(idaologin);
            // Ejercicio
        Boolean resultadoespe = login.login(email,pass);
        Boolean resultadoeje = false;
            // Verificación
        assertThat(resultadoespe,is(resultadoeje));                                                 
    }


    // @AfterEach -> Clean up


}
