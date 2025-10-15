package dao;

import java.util.Map;

import entities.Bagaglio;
import entities.Biglietto;
import interfaces.GenericDao;
import interfaces.IDatabase;

public class BigliettoDao implements GenericDao<Biglietto> {
    
    private final IDatabase DATABASE;

    private final String INSERT = "INSERT INTO ticket (ticket_no, fare_class, cabin_class, seat_number, price_amount, price_currency, status) VALUES (?,?,?,?,?,?,?)";
    private final String READ = "SELECT * FROM ticket";
    private final String DELETE = "DELETE FROM ticket WHERE ticket_id = ?";
    private final String UPDATE = "UPDATE ticket SET ticket_no = ?, fare_class = ?, cabin_class = ?, seat_number = ? , price_amount = ? , price_currency = ?, status = ? WHERE ticket_id = ?";
    private final String READONE = "SELECT * FROM ticket WHERE ticket_id = ?";
    private final String UPDATEFK = "UPDATE ticket SET passenger_id = ? WHERE ticket_id = ?";
    private final String READBYPASSENGER = "select b.* from passenger b inner join ticket t on t.passenger_id = b.passenger_id where t.passenger_id = ?";

    public static BigliettoDao instance;

    private BigliettoDao(){
        DATABASE = Database.getInstance();
    }

    public synchronized static BigliettoDao getInstance(){

        if (instance == null){
            instance = new BigliettoDao();
        }
        return instance;
    }

    @Override
    public Integer addEntity(Biglietto b) {

        if (b == null) {
            return null;
        }

        Integer id = DATABASE.executeUpdate(
            INSERT,
            b.getNumeroBiglietto(),
            b.getClasseTariffaria()+"",
            b.getCabinClass(),
            b.getNumeroPosto(),
            b.getPrezzo() == null ? null : b.getPrezzo()+"",
            b.getValuta()+"",
            b.getStatoBiglietto()
            
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
    public void update(Biglietto b) {
        
        if (b == null || b.getId() == null){
            return;
        }

        DATABASE.executeUpdate(
            UPDATE,
            b.getNumeroBiglietto(),
            b.getClasseTariffaria()+"",
            b.getCabinClass(),
            b.getNumeroPosto(),
            b.getPrezzo() == null ? null : b.getPrezzo()+"",
            b.getValuta()+"",
            b.getStatoBiglietto(),
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

        ris.forEach((k, v) -> v.put("tipoOggetto", "biglietto"));
        
        return ris.get(id);
    }

    public void updateFK(Integer id, Integer newFK){

        if (id == null || newFK == null){
            return;
        }
        DATABASE.executeUpdate(UPDATEFK, id + "", newFK + "");

    }

    public Map<Integer, Map<String, String>> readByIdPassenger(Integer id) {
        if(id == null) return null;
        Map<Integer, Map<String, String>> ris = DATABASE.executeDQL(READBYPASSENGER, id + "");
        ris.forEach((k, v) -> v.put("tipoOggetto", "biglietto"));
        return ris;
    }
}
