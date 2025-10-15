package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.BigliettoDao;
import entities.Biglietto;
import entities.Factory;

public class BigliettoService implements IService<Biglietto>{

    private final BigliettoDao bigliettoDao;
    private final BagaglioService bagaglioService;

    //Singleton
    private static BigliettoService instance;

    private BigliettoService(){

        bigliettoDao = BigliettoDao.getInstance();
        bagaglioService = BagaglioService.getInstance();

    }

    public static synchronized  BigliettoService getInstance(){
        if (instance == null) instance = new  BigliettoService();
        return  instance;
    }
    // -----------------------


    @Override
    public void save(Biglietto biglietto) {
        
         if (biglietto==null)   
            return;
        if(biglietto.getId() == null || biglietto.getId() == 0)
            bigliettoDao.addEntity(biglietto); 
        else
             bigliettoDao.update(biglietto);
        }

    

    @Override
    public List<Biglietto> findAll() {
        Map<Integer, Map<String,String>> mappaBiglietti = bigliettoDao.readAll();
        List<Biglietto> listaBiglietti = new ArrayList<>();
        for(Map<String,String> mappa : mappaBiglietti.values()){
            Biglietto biglietto = (Biglietto)Factory.getInstance().make(mappa);
            biglietto.setBagagli(bagaglioService.findByTicket(biglietto.getId()));
            listaBiglietti.add(biglietto);
        }
        return listaBiglietti;
    }

    @Override
    public void delete(Integer id) {
        if(id != null && id>0 ) 
          bigliettoDao.delete(id);
    }

    @Override
    public Biglietto findById(Integer id) {
        Map<String,String> mappaBiglietti = bigliettoDao.findById(id);
        Biglietto biglietto = (Biglietto)Factory.getInstance().make(mappaBiglietti);
        biglietto.setBagagli(bagaglioService.findByTicket(biglietto.getId()));
        return biglietto;
    }
    
    public List<Biglietto> findByPassenger(Integer id) {
        if(id == null) return null;
        Map<Integer, Map<String,String>> mappaBiglietti = bigliettoDao.readByIdPassenger(id);
        List<Biglietto> listaBiglietti = new ArrayList<>();
        for(Map<String,String> mappa : mappaBiglietti.values()){
            listaBiglietti.add((Biglietto)Factory.getInstance().make(mappa));
        }
        return listaBiglietti;
}
}
