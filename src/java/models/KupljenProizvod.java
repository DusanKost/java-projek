/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.SQLException;
import connection.SQLLiteConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Dusan
 */
public class KupljenProizvod 
{
    private int id;
    private int user_id;
    private String proizvodi;
    private String date;
//    private int poslato;
    
    public KupljenProizvod(int id, int user_id, String proizvodi,String date)
    {
        this.id = id;
        this.user_id = user_id;
        this.proizvodi = proizvodi;
        this.date = date;
//        this.poslato = 0;
    }
    
    public static void updateNePoslan(int id)
    {
        String sql = "UPDATE kupljeni_proizvodi SET poslato = 1 WHERE id = ?";
        try (
                Connection conn = SQLLiteConn.getConn();
                PreparedStatement ps = conn.prepareStatement(sql);
            )
        {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<KupljenProizvod> getNePoslane()
    {
        ArrayList<KupljenProizvod> lista = new ArrayList<>();
        String sql = "SELECT * FROM kupljeni_proizvodi WHERE poslato = 0";
        try (
                Connection conn = SQLLiteConn.getConn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
            ){
            while(rs.next())
            {
                lista.add(new KupljenProizvod(rs.getInt("id"),
                        rs.getInt("user_id"),rs.getString("proizvodi")
                        ,rs.getString("kup_date")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static void insert(int user_id, String proizvodi) throws SQLException
    {
        String sql = "INSERT INTO kupljeni_proizvodi(user_id,proizvodi) VALUES(?,?)";
        try (
             Connection conn = SQLLiteConn.getConn();
             PreparedStatement ps = conn.prepareStatement(sql);   
            )
        {
            ps.setInt(1,user_id);
            ps.setString(2,proizvodi);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static KupljenProizvod getAll()
    {
        String sql = "SELECT * FROM kupljeni_proizvodi";
        try(
            Connection conn = SQLLiteConn.getConn();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ) 
        {
            KupljenProizvod kr = new KupljenProizvod(rs.getInt("id"), 
                    rs.getInt("user_id"),rs.getString("proizvodi"),
                    rs.getString("kup_date"));
            return kr;
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(String proizvodi) {
        this.proizvodi = proizvodi;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
