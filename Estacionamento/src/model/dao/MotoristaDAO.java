package model.dao;

import javax.swing.JOptionPane;
import model.bean.Motorista;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class MotoristaDAO {
    
    public void create(Motorista m) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO motorista (nomeCompleto, genero, rg, cpf, celular, email, senha) VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1, m.getNomeCompleto());
            stmt.setBoolean(2, m.isGenero());
            stmt.setInt(3, m.getRg());
            stmt.setInt(4, m.getCpf());
            stmt.setInt(5, m.getCelular());
            stmt.setString(6, m.getEmail());
            stmt.setString(7, m.getSenha());          
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Motorista salvo com sucesso!");     
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Motorista> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Motorista> motoristas = new ArrayList<>();
        try{
            stmt = con.prepareStatement("SELECT * FROM motorista;");
            rs = stmt.executeQuery();
            while(rs.next()){
                Motorista m = new Motorista();
                m.setIdMotorista(rs.getInt("idMotorista"));
                m.setNomeCompleto(rs.getString("nomeCompleto"));
                m.setGenero(rs.getBoolean("genero"));
                m.setRg(rs.getInt("rg"));
                m.setCpf(rs.getInt("cpf"));
                m.setCelular(rs.getInt("celular"));
                m.setEmail(rs.getString("email"));
                m.setSenha(rs.getString("senha"));
                motoristas.add(m);
                
                }
            
            }
            catch(SQLException e){
                    throw new RuntimeException("Erro ao buscar os dados: ", e);
            }finally{
                    ConnectionFactory.closeConnection(con, stmt, rs);
            }        
            return motoristas; 
        }
        
        
    }
