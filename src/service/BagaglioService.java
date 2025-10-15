package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.BagaglioDao;
import entities.Factory;
import entities.Bagaglio;

public class BagaglioService implements IService<Bagaglio> {

     private final  BagaglioDao bagaglioDao;


    //Singleton ------------

    private static  BagaglioService instance;

    private  BagaglioService(){
        bagaglioDao = BagaglioDao.getInstance();
    }
    
    public static synchronized  BagaglioService getInstance(){
        if (instance == null) instance = new  BagaglioService();
        return  instance;
    }
    //---------------------


 //metodo save che racchiude sia insert che update 
    @Override
    public void save (Bagaglio bagaglio){
        if (bagaglio==null)   
            return;
        if(bagaglio.getId() == null || bagaglio.getId() == 0)
            bagaglioDao.addEntity(bagaglio); 
        else
            bagaglioDao.update(bagaglio);
        } 

    public List<Bagaglio> findAll (){
        Map<Integer, Map<String,String>> mappaBagagli = bagaglioDao.readAll();
        List<Bagaglio> listaBagagli = new ArrayList<>();
        for(Map<String,String> m : mappaBagagli.values()){
            listaBagagli.add((Bagaglio)Factory.getInstance().make(m));
        }
        return listaBagagli;
        

    }

   

    public void delete (Integer id){
        if(id != null && id>0 ) 
          bagaglioDao.delete(id);
    }

    public Bagaglio findById (Integer id) {
        Map<String,String> mappaBagagli = bagaglioDao.findById(id);
        return (Bagaglio)Factory.getInstance().make(mappaBagagli);
    }

    public List<Bagaglio> findByTicket(Integer id) {
        if(id == null) return null;
        Map<Integer, Map<String,String>> mappaBagagli = bagaglioDao.readByIdTicket(id);
        List<Bagaglio> listaBagagli = new ArrayList<>();
        for(Map<String,String> mappa : mappaBagagli.values()){
            listaBagagli.add((Bagaglio)Factory.getInstance().make(mappa));
        }
        return listaBagagli;
    }
    

    
}
