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
public class Proizvod 
{
    
    private int id;
    private String naziv;
    private String slika;
    private int sladak;
    private int cena;
    private int kolicina;
    private int kup_kolicina;
    
    /**
     *
     * @param naziv
     * @param slika
     * @param sladak
     */
    public Proizvod(String naziv,String slika,int sladak, int cena, int kolicina)
    {
        this.naziv = naziv;
        this.slika = slika;
        this.sladak = sladak;
        this.cena = cena;
        this.kolicina = kolicina;
        this.kup_kolicina = 1;
    }
    
    public static ArrayList<Proizvod> getLow()
    {
        ArrayList<Proizvod> lista = new ArrayList<>();
        String sql = "SELECT * FROM proizvod WHERE kolicina = 0";
        try (
                Connection conn = SQLLiteConn.getConn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
            ){
            while(rs.next())
            {
                lista.add(new Proizvod(rs.getString("naziv"),
                        rs.getString("slika"),rs.getInt("sladak")
                        ,rs.getInt("cena"),rs.getInt("kolicina")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public void updateKolicina()
    {
        String sql = "UPDATE proizvod SET kolicina =? WHERE id = ?";
        try(
             Connection conn = SQLLiteConn.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql);   
            ) 
        {
            stmt.setInt(1, (this.kolicina - this.kup_kolicina));
            stmt.setInt(2, this.id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteById(int id)
    {
        String sql = "DELETE FROM proizvod WHERE id = ?";
        try(
             Connection conn = SQLLiteConn.getConn();
             PreparedStatement stmt = conn.prepareStatement(sql);   
            ) 
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void add1KupKolc()
    {
        this.kup_kolicina++;
    }
    public void rm1KupKolc()
    {
        this.kup_kolicina--;
    }
    
    public static void updateByIdNoImage(int id, String naziv, int sladak,int cena,int kolicina)
    {
        String sql = "UPDATE proizvod SET naziv=?,sladak=?,cena=?,kolicina=? WHERE id = ?";
        try (
                Connection conn = SQLLiteConn.getConn();
                PreparedStatement stmt = conn.prepareStatement(sql);
            )
        {
                stmt.setString(1, naziv);
                stmt.setInt(2, sladak);
                stmt.setInt(3, cena);
                stmt.setInt(4, kolicina);
                stmt.setInt(5, id);
                stmt.executeUpdate();
                
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateById(int id, String naziv, String slika, int sladak,int cena,int kolicina)
    {
        String sql = "UPDATE proizvod SET naziv=?,slika=?,sladak=?,cena=?,kolicina=? WHERE id = ?";
        try (
                Connection conn = SQLLiteConn.getConn();
                PreparedStatement stmt = conn.prepareStatement(sql);
            )
        {
                stmt.setString(1, naziv);
                stmt.setString(2, slika);
                stmt.setInt(3, sladak);
                stmt.setInt(4, cena);
                stmt.setInt(5, kolicina);
                stmt.setInt(6, id);
                stmt.executeUpdate();
                
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static ArrayList<Proizvod> getSlani()
    {
        ArrayList<Proizvod> lista = new ArrayList<>();
        String sql = "SELECT * FROM proizvod WHERE sladak = 0";
        try (
                Connection conn = SQLLiteConn.getConn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
            ){
            while(rs.next())
            {
                lista.add(new Proizvod(rs.getString("naziv"),
                        rs.getString("slika"),rs.getInt("sladak")
                        ,rs.getInt("cena"),rs.getInt("kolicina")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static ArrayList<Proizvod> getSlatki()
    {
        ArrayList<Proizvod> lista = new ArrayList<>();
        String sql = "SELECT * FROM proizvod WHERE sladak = 1";
        try (
                Connection conn = SQLLiteConn.getConn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
            ){
            while(rs.next())
            {
                lista.add(new Proizvod(rs.getString("naziv")
                        ,rs.getString("slika"),rs.getInt("sladak")
                        ,rs.getInt("cena"),rs.getInt("kolicina")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static ArrayList<Proizvod> getAll()
    {
        ArrayList<Proizvod> lista = new ArrayList<>();
        String sql = "SELECT * FROM proizvod";
        try (
                Connection conn = SQLLiteConn.getConn();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
            ){
            while(rs.next())
            {
                lista.add(new Proizvod(rs.getString("naziv"),
                        rs.getString("slika"),rs.getInt("sladak"),
                        rs.getInt("cena"),rs.getInt("kolicina")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static Proizvod getById(int id) throws  SQLException
    {
      
        Proizvod proizvod;
        Connection conn = SQLLiteConn.getConn();
        String sql = "SELECT * FROM proizvod WHERE  id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        proizvod  = new Proizvod(rs.getString("naziv"), rs.getString("slika"), 
                    rs.getInt("sladak"),rs.getInt("cena"),
                    rs.getInt("kolicina"));
        /*Zatvararnje kon*/
        stmt.close();
        rs.close();
        conn.close();
        
        return proizvod;
    }
    
    public static Proizvod getByNaziv(String nazivV) throws  SQLException
    {
      
        Proizvod proizvod;
        Connection conn = SQLLiteConn.getConn();
        String sql = "SELECT * FROM proizvod WHERE  naziv = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nazivV);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        proizvod  = new Proizvod(rs.getString("naziv"), rs.getString("slika"), 
                    rs.getInt("sladak"),rs.getInt("cena"),
                    rs.getInt("kolicina"));
        /*Zatvararnje kon*/
        stmt.close();
        rs.close();
        conn.close();
        
        return proizvod;
    }
    
    public void insert() throws SQLException
    {
        String sql = "INSERT INTO proizvod(naziv,slika,sladak,cena,kolicina) VALUES(?,?,?,?,?)";
        Connection conn = SQLLiteConn.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, this.naziv);
        ps.setString(2, this.slika);
        ps.setInt(3, this.sladak);
        ps.setInt(4, this.cena);
        ps.setInt(5, this.kolicina);
        ps.executeUpdate();
        /*Zatvararnje kon*/
        ps.close();
        conn.close();
    }
    
    public int getId() throws SQLException{
        
        String sql = "SELECT id FROM proizvod WHERE naziv = ?";
        Connection conn = SQLLiteConn.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, naziv);
        ResultSet rs = ps.executeQuery();
        rs.next();
        id = rs.getInt("id");
        
        rs.close();
        ps.close();
        conn.close();
        return id;
    }
    
    public String sladakToString(int sladak){
        if (sladak == 1) {
            return "Sladak";
        }
        return "Slan";
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public int getSladak() {
        return sladak;
    }

    public void setSladak(int sladak) {
        this.sladak = sladak;
    }
    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
    public int getKup_kolicina() {
        return kup_kolicina;
    }

    public void setKup_kolicina(int kup_kolicina) {
        this.kup_kolicina = kup_kolicina;
    }
    
    public int getKupCena(){
        return this.cena * this.kup_kolicina;
    }
}
