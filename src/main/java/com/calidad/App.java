package com.calidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.calidad.login.modelo.Usuario;
import com.calidad.login.dao.UserMysqlDAO;

/**
 * Hello world!
 */
public class App {
   public static void main(String args[]) throws SQLException {
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3307/calidad";
            System.out.println("jdbcurl=" + dbURL);
            String strUserID = "root";
            String strPassword = "123456";
            con=DriverManager.getConnection(dbURL,strUserID,strPassword);
            System.out.println("Connected to the database.");
            Statement stmt=(Statement) con.createStatement();
            System.out.println("Executing query");
            

            Usuario usuario = new Usuario("Nalle", "nalle@gmail.com", "123456789");

            UserMysqlDAO dao = new UserMysqlDAO ();
            dao.save(usuario);
            ResultSet rs=((java.sql.Statement) stmt).executeQuery("SELECT * FROM Usuario");


            while(rs.next())
                System.out.println(rs.getInt("1"));
            con.close();
        }catch(Exception e){ System.out.println(e);}
        finally {
            con.close();
        }
}
}


