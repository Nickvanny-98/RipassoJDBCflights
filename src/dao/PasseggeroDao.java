package dao;

import java.util.Map;

import entities.Passeggero;
import interfaces.GenericDao;
import interfaces.IDatabase;

public class PasseggeroDao implements GenericDao<Passeggero>{
    
    private final IDatabase DATABASE;

    private final String INSERT = "INSERT INTO passenger (first_name, last_name, dob, document_no, nationality, email, phone) VALUES (?,?,?,?,?,?,?)";
    private final String READ = "SELECT * FROM passenger";
    private final String READONE = "SELECT * FROM passenger WHERE passenger_id = ?";
    private final String READBYNAME = "SLECT * FROM passenger where first_name like %?%";
    // TODO: implementare metodo
    private final String READBYFARECLASS = "SELECT p.* FROM passenger p INNER JOIN ticket t ON p.passenger_id = t.passenger_id ORDER BY t.fare_class";
    // TODO: implementare metodo
    private final String READBYTICKET = "SELECT p.* FROM passenger p INNER JOIN ticket t ON p.passenger_id = t.passenger_id WHERE t.ticket_id = ?";
    private final String UPDATE = "UPDATE passenger SET first_name = ?, last_name = ?, dob = ?, document_no = ? , nationality = ? , email = ?, phone = ? WHERE passenger_id = ?";
    private final String DELETE = "DELETE FROM passenger WHERE passenger_id = ?";
    
    
    


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
    public Integer addEntity(Passeggero passeggero, Integer... FK) {

        if (passeggero == null || FK.length!=0) {
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

        passeggero.setId(id);
        return id;
    }

    @Override
    public Map<Integer,Map<String, String>> readAll() {
        Map<Integer, Map<String, String>> mappa =  DATABASE.executeDQL(READ);
        mappa.forEach((k, v) -> v.put("tipoOggetto", "biglietto"));
        return mappa;
    }
    @Override
    public void update(Passeggero passeggero, Integer... FK) {
        
        if (passeggero == null || passeggero.getId() == null || FK.length!=0){
            return;
        }

        DATABASE.executeUpdate(
            UPDATE,
            passeggero.getNome(),
            passeggero.getCognome(),
            passeggero.getDataNascita() == null ? null : passeggero.getDataNascita()+"",
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

       ris = DATABASE.executeDQL(READBYNAME, name);

       ris.forEach((k,v) -> v.put("tipoOggetto", "passeggero"));

       return ris;
    }

    
}
