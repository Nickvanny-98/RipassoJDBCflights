package dao;

import java.util.Map;

import entities.Passeggero;
import interfaces.GenericDao;
import interfaces.IDatabase;

public class PasseggeroDao implements GenericDao<Passeggero>{
    
    private final IDatabase DATABASE;

    private final String INSERT = "INSERT INTO passenger (first_name, last_name, dob, document_no, nationality, email, phone) VALUES (?,?,?,?,?,?,?)";
    private final String READ = "SELECT * FROM passenger";
    private final String DELETE = "DELETE FROM passenger WHERE passenger_id = ?";
    private final String UPDATE = "UPDATE passenger SET first_name = ?, last_name = ?, dob = ?, document_no = ? , nationality = ? , email = ?, phone = ? WHERE passenger_id = ?";
    private final String READONE = "SELECT * FROM passenger WHERE passenger_id = ?";
    private final String FINDBYNAME = "select * from passenger where first_name like %?%";
    

    public static PasseggeroDao instance;

    private PasseggeroDao(){
        DATABASE = Database.getInstance();
    }

    public synchronized static PasseggeroDao getInstance(){

        if (instance == null){
            instance = new PasseggeroDao();
        }
        return instance;
    }

    @Override
    public Integer addEntity(Passeggero passeggero) {

        if (passeggero == null) {
            return null;
        }

        Integer id = DATABASE.executeUpdate(
            INSERT,
            passeggero.getNome(),
            passeggero.getCognome(),
            passeggero.getDataNascita() == null ? null : passeggero.getDataNascita()+"",
            passeggero.getNumeroDocumento(),
            passeggero.getNazionalita(),
            passeggero.getEmail(),
            passeggero.getNumeroTelefono()
            
        );

        b.setId(id);
        return id;
    }

    @Override
    public Map<Integer,Map<String, String>> readAll() {
        Map<Integer, Map<String, String>> mappa =  DATABASE.executeDQL(READ);
        mappa.forEach((k, v) -> v.put("tipoOggetto", "biglietto"));
        return mappa;
    }
    @Override
    public void update(Passeggero passeggero) {
        
        if (passeggero == null || passeggero.getId() == null){
            return;
        }

        DATABASE.executeUpdate(
            UPDATE,
            passeggero.getNome(),
            passeggero.getCognome(),
            passeggero.getDataNascita() == null ? null : b.getDataNascita()+"",
            passeggero.getNumeroDocumento(),
            passeggero.getNazionalita(),
            passeggero.getEmail(),
            passeggero.getNumeroTelefono(),
            String.valueOf(passeggero.getId()));
    }
    @Override
    public void delete(Integer id) {
        
        if (id == null){
            return;
        }
        DATABASE.executeUpdate(DELETE, id + "");


    }
    @Override
    public Map<String,String> findById(Integer id) {
        Map<Integer, Map<String, String>> ris;
            if (id == null){
            return null;
        }

        ris = DATABASE.executeDQL(READONE, id + "");

        ris.forEach((k, v) -> v.put("tipoOggetto", "passeggero"));
        
        return ris.get(id);
    }

    public Map<Integer, Map<String,String>> findByName(String name){
       Map<Integer, Map<String,String>> ris;

       ris = DATABASE.executeDQL(FINDBYNAME, name);

       ris.forEach((k,v) -> v.put("tipoOggetto", "passeggero"));

       return ris;
    }

    
}
