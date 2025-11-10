package com.calidad.login.integracion.service; 
import java.io.File;
import java.io.FileInputStream;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.calidad.login.dao.IDAOLogin;
import com.calidad.login.dao.UserMysqlDAO;
import com.calidad.login.service.UserService;

public class LoginServiceTest extends DBTestCase{

    private IDAOLogin dao;
    private UserService service;

    public LoginServiceTest(){
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, 
        "com.mysql.cj.jdbc.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, 
        "jdbc:mysql://localhost:3307/calidad");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, 
        "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, 
        "123456");
        

    }

    @BeforeEach
    void setup() throws Exception {

        dao = new UserMysqlDAO();
        service = new UserService (dao);
        
        IDatabaseConnection connection = getConnection();
        if (connection == null){
        fail("Failed to establish a connection to the database.");
        } else {

            System.out.println("Connection established successfully");
        }

        try {
            DatabaseOperation.TRUNCATE_TABLE.execute(connection, getDataSet());
            DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
        } catch (Exception e){
            fail("Error in setup: "+ e.getMessage());
        } finally {
            connection.close();
        }
    }

    protected IDataSet getDataSet() throws Exception{

        return new FlatXmlDataSetBuilder().build(new FileInputStream ("src/resources/create.xml"));
    }

    @Test
    public void whenSaveUser_test() {
        service.createUser("user1", "user1@email.com", "12345678");

        try {
            //Conexión
            IDatabaseConnection conn = getConnection();
            conn.getConfig().setProperty(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true);
            //--------------------------------Base de datos real
            //Dataset - base de datos
            IDataSet databaseDataSet = conn.createDataSet();
            //Tabla que quiero consultar - nombre de mi tabla en la base de datos
            ITable actualTable = databaseDataSet.getTable("Usuario");
            //--------------------------------
            //--------------------------------Base de datos esperada - XML
            //Resultado esperado
            //Dataset - base de datos
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/addUser.xml")); 
            //Tabla - resultado esperado
            ITable expectedTable = expectedDataSet.getTable("Usuario");
            //--------------------------------
            //Verificación
            Assertion.assertEquals(expectedTable, actualTable);
        

        } catch (Exception e){
            fail ("Error in insert test: " + e.getMessage());
        }


    }

}
