package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import interfaces.IDatabase;

public class Database implements IDatabase{

    private String user;
    private String password;
    private String percorso;
    private Connection connection;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    
    private static IDatabase instance = null;
    
    
    public synchronized static IDatabase getInstance() {
        
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

   
    private Database(){
        this.user = "root";
        this.password = "root";
        setPercorso(percorso);
    }

    public void setPercorso(String percorso) {
        String url = "jdbc:mysql://127.0.0.1:3306/music_jdbc";
        String timezone = "?useSSL=false&serverTimezone=UTC";
        this.percorso = url+ timezone;
    }

    private void openConn(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(percorso, user, password);  
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("controlla la proprietà DRIVER oppure di avere aggiunto "+ 
            " il jar che contiene la libreria JDBC");
        }catch(SQLException e){
                e.printStackTrace();
                System.out.println("la connessione non è aperta controlla che il percorso, lo user e la password " +
                " siano corretti");
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    public void closeConn(){
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public Map<Integer,Map<String,String>> executeDQL(String query,String... parametri){
        Map<Integer,Map<String,String>> result = new HashMap<>();
        openConn();
        try {
            
            PreparedStatement ps = connection.prepareStatement(query);
            for (int i = 0; i < parametri.length; i++) {
                
                ps.setString(i+1, parametri[i]);
                
            }
            ResultSet rs = ps.executeQuery();
            Map<String,String> mappa;
            while (rs.next()) {
                mappa = new HashMap<>();
                
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    mappa.put(rs.getMetaData().getColumnName(i), rs.getString(i));
                }
                result.put(rs.getInt("id"), mappa);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConn();
        }
        return result;
    }
    
    
    
    public Integer executeUpdate(String comando,String... parametri){
        Integer ris = null;
        openConn();
        String[] colonne = {"id"};
        try {
            PreparedStatement ps = connection.prepareStatement(comando,colonne);
            
            for (int i = 0; i < parametri.length; i++) {
                ps.setString(i+1, parametri[i]);
                
            }
            
            ris = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs != null){
                ris = rs.getInt(1);
                rs.close();
                return ris;
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConn();
        }
        return ris;
    }
   
    
    
}
