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
        bagaglioDao= BagaglioDao.getInstance();
    }
    
    public static synchronized  BagaglioService getInstance(){
        if (instance ==null) instance=new  BagaglioService();
        return  instance;
    }
    //---------------------


 //metodo save che racchiude sia insert che update 
    @Override
    public void save (Bagaglio a){
        if (a==null)   
            return;
        if(a.getId() == null || a.getId() == 0)
            bagaglioDao.addEntity(a); 
        else
            bagaglioDao.update(a);
        } 

    public List<Bagaglio> findAll (){
        Map<Integer, Map<String,String>> ris = bagaglioDao.readAll();
        List<Bagaglio> lb = new ArrayList<>();
        for(Map<String,String> m : ris.values()){
            lb.add((Bagaglio)Factory.getInstance().make(m));
        }
        return lb;
        

    }

   

    public void delete (Integer id){
        if(id != null && id>0 ) 
          bagaglioDao.delete(id);
    }

    public Bagaglio findById (Integer id) {
        Map<String,String> ris = bagaglioDao.findById(id);
        return (Bagaglio)Factory.getInstance().make(ris);
    }

    public List<Bagaglio> findByTicket(Integer id) {
        if(id == null) return null;
        Map<Integer, Map<String,String>> ris = bagaglioDao.readByIdTicket(id);
        List<Bagaglio> lb = new ArrayList<>();
        for(Map<String,String> m : ris.values()){
            lb.add((Bagaglio)Factory.getInstance().make(m));
        }
        return lb;
    }
    

    
}
