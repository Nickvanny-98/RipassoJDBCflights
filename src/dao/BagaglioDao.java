package dao;

import java.util.Map;

import entities.Bagaglio;
import interfaces.GenericDao;
import interfaces.IDatabase;

public class BagaglioDao implements GenericDao<Bagaglio> {
    
    private final IDatabase DATABASE;

    private final String INSERT = "INSERT INTO baggage (tag_code,weight_kg, is_oversize) VALUES (?,?,?)";
    private final String READ = "SELECT * FROM baggage";
    private final String DELETE = "DELETE FROM baggage WHERE bag_id = ?";
    private final String UPDATE = "UPDATE baggage SET tag_code = ?, weight_kg = ?, is_oversize = ? WHERE bag_id = ?";
    private final String READONE = "SELECT * FROM baggage WHERE bag_id = ?";
    private final String UPDATEFK = "UPDATE baggage SET ticket_id = ? WHERE bag_id = ?";
    private final String READBYIDTICKET = "select b.* from baggage b inner join ticket t on t.ticket_id = b.ticket_id where t.ticket_id = ?";


    public static BagaglioDao instance;

    private BagaglioDao(){
        DATABASE = Database.getInstance();
    }

    public synchronized static BagaglioDao getInstance(){

        if (instance == null){
            instance = new BagaglioDao();
        }
        return instance;
    }

    @Override
    public Integer addEntity(Bagaglio b) {

        if (b == null) {
            return null;
        }

        Integer id = DATABASE.executeUpdate(
            INSERT,
            b.getCodiceEtichetta(),
            b.getPeso() == null ? null : String.valueOf(b.getPeso()),
            b.isOversize() ? "1" : "0"
            
        );

        b.setId(id);
        return id;
    }

    @Override
    public Map<Integer,Map<String, String>> readAll() {
        Map<Integer, Map<String, String>> mappa =  DATABASE.executeDQL(READ);
        mappa.forEach((k, v) -> v.put("tipoOggetto", "bagaglio"));
        return mappa;
    }
    @Override
    public void update(Bagaglio b) {
        
        if (b == null || b.getId() == null){
            return;
        }

        DATABASE.executeUpdate(
            UPDATE,
            b.getCodiceEtichetta(),
            b.getPeso() == null ? null : String.valueOf(b.getPeso()),
            b.isOversize() ? "1" : "0",
            String.valueOf(b.getId()));
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

        ris.forEach((k, v) -> v.put("tipoOggetto", "bagaglio"));

        return ris.get(id);
    }

    public Map<Integer, Map<String, String>> readByIdTicket(Integer id) {
        if(id == null) return null;
        Map<Integer, Map<String, String>> ris = DATABASE.executeDQL(READBYIDTICKET, id + "");
        ris.forEach((k, v) -> v.put("tipoOggetto", "bagaglio"));
        return ris;
    }

    public void updateFK(Integer id, Integer newFK){

        if (id == null || newFK == null){
            return;
        }
        DATABASE.executeUpdate(UPDATEFK, id + "", newFK + "");

    }
}
