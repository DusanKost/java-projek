/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import connection.SQLLiteConn;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Dusan
 */
public class User 
{
    private int id;
    private String username;
    private String password;
    private int type_of_user;
    
    /**
     *
     * @param username
     * @param password
     * @param type_of_user
     */
    public User( String username, String password, int type_of_user)
    {
        this.username = username;
        this.password = password;
        this.type_of_user = type_of_user;
    }
    
    public static String getUsernameById(int id)
    {
        String sql = "SELECT username FROM users WHERE id = ?";
        try (
                Connection conn = SQLLiteConn.getConn();
                PreparedStatement stmt = conn.prepareStatement(sql);
            )
        {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            String name = "";
            while(rs.next())
            {
                name = rs.getString("username");
            }
            return name;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static void updateById(int id, String username, String password, int type_of_user)
    {
        String sql = "UPDATE users SET username=?,password=?,type_of_user=? WHERE id = ?";
        try (
                Connection conn = SQLLiteConn.getConn();
                PreparedStatement stmt = conn.prepareStatement(sql);
            )
        {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setInt(3, type_of_user);
                stmt.setInt(4, id);
                stmt.executeUpdate();
                
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteById(int id)
    {
        String sql = "DELETE FROM users WHERE id = ?";
        try (
              Connection conn = SQLLiteConn.getConn();
              PreparedStatement stmt = conn.prepareStatement(sql); 
            )
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
 
    }
    
    public static ArrayList<User> getAll()
    {
        ArrayList<User> lista = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (
                Connection conn = SQLLiteConn.getConn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
            ){
            while(rs.next())
            {
                lista.add(new User(rs.getString("username"),rs.getString("password"),rs.getInt("type_of_user")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static User getByUserNmae(String uName) throws  SQLException
    {
      
        User user;
        Connection conn = SQLLiteConn.getConn();
        String sql = "SELECT * FROM users WHERE  username = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, uName);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        user  = new User(rs.getString("username"), rs.getString("password"), rs.getInt("type_of_user"));
        /*Zatvararnje kon*/
        stmt.close();
        rs.close();
        conn.close();
        
        return user;
    }
    
    public void insert() throws SQLException
    {
        String sql = "INSERT INTO users(username,password,type_of_user) VALUES(?,?,?)";
        Connection conn = SQLLiteConn.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, this.username);
        ps.setString(2, this.password);
        ps.setInt(3, this.type_of_user);
        ps.executeUpdate();
        /*Zatvararnje kon*/
        ps.close();
        conn.close();
    }
    
    public int getId() throws SQLException{
        
        String sql = "SELECT id FROM users WHERE username = ?";
        Connection conn = SQLLiteConn.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        rs.next();
        id = rs.getInt("id");
        
        rs.close();
        ps.close();
        conn.close();
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType_of_user() {
        return type_of_user;
    }

    public void setType_of_user(int type_of_user) {
        this.type_of_user = type_of_user;
    }
}
