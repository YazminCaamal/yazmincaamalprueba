package com.calidad.login.service;

import com.calidad.login.dao.IDAOLogin;
import com.calidad.login.modelo.Usuario;

public class LoginService {
    
    private IDAOLogin daoLogin;
    //Implementar el método login(String email,String password)
     //verificar que el usuario exista en la bd (lo hacemos llamando al método que tenermos en el DAO) y si existe, verificar que el password recibido coincida con el de la bd

    // Constructor (opcional, pero recomendable)
    public LoginService(IDAOLogin daoLogin) {
        this.daoLogin = daoLogin;
    }

    // Método login solicitado
    public boolean login(String email, String password) {
        // Llamamos al DAO para buscar al usuario por su email
        Usuario usuario = daoLogin.findUsuarioByEmail(email);

        // Si no existe el usuario, devolvemos false
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return false;
        }

        // Si el password coincide, marcamos como logueado
        if (usuario.getPassword().equals(password)) {
            usuario.setIsLogged(true);
            System.out.println("Inicio de sesión exitoso. Bienvenido " + usuario.getName());
            return true;
        } else {
            System.out.println("Contraseña incorrecta.");
            return false;
        }
    }
}