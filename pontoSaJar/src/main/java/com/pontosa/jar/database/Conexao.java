package com.pontosa.jar.database;

import com.mysql.cj.x.protobuf.MysqlxSql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/PontoSa";
    private static final String user = "root";
    private static final String pass = "";
 
    

    
    public Connection getConnection(){
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão:", ex);
        }

    }

    public void setUsuarios(String nome, String email, Integer cargo, String senha) {
        try{
            System.out.println("Estou inserindo.... Agora vai");
            String sql = (String.format("INSERT INTO `usuario` (nome, email, cargo, senha) VALUES ('%s', '%s', '%d', '%s')", nome, email, cargo, senha));
            PreparedStatement pstmt = getConnection().prepareStatement(sql);
            pstmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setRegistro(Integer dispositivo, Integer componente, Double registro) {
        try{
            System.out.println("Estou inserindo.... Agora vai");
            String sql = (String.format("INSERT INTO `historico` (FK_dispositivo, FK_componente, registro) VALUES ('%d', '%d', %.0f)", dispositivo, componente, registro));
            PreparedStatement pstmt = getConnection().prepareStatement(sql);
            pstmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Boolean login(String email, String senha){
        
        int id = 0;
        try {
            
            System.out.println("Estou buscando...");
            String sql = (String.format("SELECT * FROM usuario where email = '%s' and senha = '%s'", email, senha));
            //PreparedStatement pstmt = getConnection().prepareStatement(sql);
            Statement st = getConnection().createStatement();

            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()){
                id = rs.getInt("id");
                String nome = rs.getString("nome");
                String emailResultado = rs.getString("email");
                int cargo = rs.getInt("cargo");
                String senhaResultado = rs.getString("senha");
                
                System.out.println(String.format("%s, %s, %s, %s, %s", id, nome, emailResultado, cargo, senhaResultado));
            }
            
          
            
            st.close();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        if (id > 0){
            return true;
        } else {
        return false;
        }
           
    }
}
