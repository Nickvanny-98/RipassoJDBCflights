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
    public void save(Biglietto t) {
        
         if (t==null)   
            return;
        if(t.getId() == null || t.getId() == 0)
            bigliettoDao.addEntity(t); 
        else
             bigliettoDao.update(t);
        }

    

    @Override
    public List<Biglietto> findAll() {
        Map<Integer, Map<String,String>> ris = bigliettoDao.readAll();
        List<Biglietto> lb = new ArrayList<>();
        for(Map<String,String> m : ris.values()){
            Biglietto b = (Biglietto)Factory.getInstance().make(m);
            b.setBagagli(bagaglioService.findByTicket(b.getId()));
            lb.add(b);
        }
        return lb;
    }

    @Override
    public void delete(Integer id) {
        if(id != null && id>0 ) 
          bigliettoDao.delete(id);
    }

    @Override
    public Biglietto findById(Integer id) {
        Map<String,String> ris = bigliettoDao.findById(id);
        Biglietto b = (Biglietto)Factory.getInstance().make(ris);
        b.setBagagli(bagaglioService.findByTicket(b.getId()));
        return b;
    }
    
    public List<Biglietto> findByPassenger(Integer id) {
        if(id == null) return null;
        Map<Integer, Map<String,String>> ris = bigliettoDao.readByIdPassenger(id);
        List<Biglietto> lb = new ArrayList<>();
        for(Map<String,String> m : ris.values()){
            lb.add((Biglietto)Factory.getInstance().make(m));
        }
        return lb;
}
}
