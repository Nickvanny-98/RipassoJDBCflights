package dao;

import java.util.Map;

import entities.Bagaglio;
import entities.Biglietto;
import interfaces.GenericDao;
import interfaces.IDatabase;

public class BigliettoDao implements GenericDao<Biglietto> {
    
    private final IDatabase DATABASE;

    private final String INSERT = "INSERT INTO ticket (ticket_no, passenger_id, fare_class, cabin_class, seat_number, price_amount, price_currency, status) VALUES (?,?,?,?,?,?,?,?)";
    private final String READ = "SELECT * FROM ticket";
    private final String READONE = "SELECT * FROM ticket WHERE ticket_id = ?";
    private final String READBYPASSENGER = "SELECT b.* FROM passenger b INNER JOIN ticket t ON t.passenger_id = b.passenger_id WHERE t.passenger_id = ?";
    private final String UPDATE = "UPDATE ticket SET ticket_no = ?, passenger_id = ?, fare_class = ?, cabin_class = ?, seat_number = ? , price_amount = ? , price_currency = ?, status = ? WHERE ticket_id = ?";
    private final String DELETE = "DELETE FROM ticket WHERE ticket_id = ?";


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
    public Integer addEntity(Biglietto biglietto, Integer... FK) {

        if (biglietto == null || FK.length!=1 || FK[0] == null) {
            return null;
        }

        Integer id = DATABASE.executeUpdate(
            INSERT,
            biglietto.getNumeroBiglietto(),
            String.valueOf(FK[0]),
            biglietto.getClasseTariffaria()+"",
            biglietto.getCabinClass(),
            biglietto.getNumeroPosto(),
            biglietto.getPrezzo() == null ? null : biglietto.getPrezzo()+"",
            biglietto.getValuta()+"",
            biglietto.getStatoBiglietto()
            
        );

        biglietto.setId(id);
        return id;
    }

    @Override
    public Map<Integer,Map<String, String>> readAll() {
        Map<Integer, Map<String, String>> mappa =  DATABASE.executeDQL(READ);
        mappa.forEach((k, v) -> v.put("tipoOggetto", "biglietto"));
        return mappa;
    }

    @Override
    public void update(Biglietto biglietto, Integer... FK) {
        
        if (biglietto == null || biglietto.getId() == null || FK.length!=1 || FK[0] == null){
            return;
        }

        DATABASE.executeUpdate(
            UPDATE,
            biglietto.getNumeroBiglietto(),
            String.valueOf(FK[0]),
            biglietto.getClasseTariffaria()+"",
            biglietto.getCabinClass(),
            biglietto.getNumeroPosto(),
            biglietto.getPrezzo() == null ? null : biglietto.getPrezzo()+"",
            biglietto.getValuta()+"",
            biglietto.getStatoBiglietto(),
            String.valueOf(biglietto.getId()));
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

    public Map<Integer, Map<String, String>> readByIdPassenger(Integer id) {
        if(id == null) return null;
        Map<Integer, Map<String, String>> ris = DATABASE.executeDQL(READBYPASSENGER, id + "");
        ris.forEach((k, v) -> v.put("tipoOggetto", "biglietto"));
        return ris;
    }
}
