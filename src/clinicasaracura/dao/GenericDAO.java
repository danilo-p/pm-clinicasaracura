/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author danilo
 */
public abstract class GenericDAO {

    protected Connection getConnection() {
        return ConnectionDatabase.getConnection();
    }

    protected int save(String insertSql, Object... parametros) {
        int id = -1;
        System.out.println("ASDJFASL;DKJF");
        try {
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(insertSql,
                Statement.RETURN_GENERATED_KEYS);
            
            System.out.println(insertSql);

            for (int i = 0; i < parametros.length; i++) {
                pstmt.setObject(i + 1, parametros[i]);
            }

            pstmt.executeUpdate();
            
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()){ 
                id = generatedKeys.getInt(1);
                System.out.println("Deu bom " + id);
            } else {
                System.out.println("Deu ruim");
            }

            pstmt.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("@#$%ˆˆ*()");
            System.out.println(ex);
        }
        
        System.out.println("23456789");
        
        return id;
    }

    protected void update(String updateSql, Object id, Object... parametros) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(updateSql);

        for (int i = 0; i < parametros.length; i++) {
            pstmt.setObject(i + 1, parametros[i]);
        }
        pstmt.setObject(parametros.length + 1, id);
        pstmt.execute();
        pstmt.close();
        connection.close();
    }

    protected void delete(String deleteSql, Object... parametros) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(deleteSql);

        for (int i = 0; i < parametros.length; i++) {
            pstmt.setObject(i + 1, parametros[i]);
        }

        pstmt.execute();
        pstmt.close();
        connection.close();
    }
}
