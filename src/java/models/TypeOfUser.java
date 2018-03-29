/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import connection.SQLLiteConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Dusan
 */
public class TypeOfUser 
{

    private  int id;
    private String tip;
    
    /**
     *
     * @param tip
     */
    public TypeOfUser(String tip)
    {
        this.tip = tip;
    }
    
    public static String getById(int id) throws SQLException
    {
        String vratiTip;
        String sql = "SELECT tip FROM tip_usera WHERE id = ?";
        Connection conn = SQLLiteConn.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        vratiTip = rs.getString("tip");
        
        rs.close();
        ps.close();
        conn.close();
        return vratiTip;
    }
    
    public static ArrayList<TypeOfUser> getAll()
    {
        ArrayList<TypeOfUser> lista = new ArrayList<>();
        String sql = "SELECT * FROM tip_usera";
        try (
                Connection conn = SQLLiteConn.getConn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
            ){
            while(rs.next())
            {
                lista.add(new TypeOfUser(rs.getString("tip")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static TypeOfUser getByTip(String tipS) throws  SQLException
    {
      
        TypeOfUser tip;
        Connection conn = SQLLiteConn.getConn();
        String sql = "SELECT * FROM tip_usera WHERE  tip = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, tipS);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        tip  = new TypeOfUser(rs.getString("tip"));
        /*Zatvararnje kon*/
        stmt.close();
        rs.close();
        conn.close();
        
        return tip;
    }
    
    public void insert() throws SQLException
    {
        String sql = "INSERT INTO tip_usera(tip) VALUES(?)";
        Connection conn = SQLLiteConn.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, this.tip);
        ps.executeUpdate();
        /*Zatvararnje kon*/
        ps.close();
        conn.close();
    }
    
    
    public int getId() throws SQLException{
        
        String sql = "SELECT id FROM tip_usera WHERE tip = ?";
        Connection conn = SQLLiteConn.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tip);
        ResultSet rs = ps.executeQuery();
        rs.next();
        id = rs.getInt("id");
        
        rs.close();
        ps.close();
        conn.close();
        return id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
