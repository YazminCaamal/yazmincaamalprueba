package com.calidad.login.dao;

import com.calidad.login.modelo.Usuario;

public interface IDAOLogin {

    Usuario findUsuarioByEmail(String email);




}
