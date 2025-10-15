package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.PasseggeroDao;
import entities.Passeggero;
import entities.Factory;

public final class PasseggeroService implements IService<Passeggero>{ 
    
    private final PasseggeroDao passeggeroDao;
    private final BigliettoService bigliettoService;

    //Singleton ------------

    private static PasseggeroService instance;

    private PasseggeroService(){
        passeggeroDao=PasseggeroDao.getInstance();
        bigliettoService = BigliettoService.getInstance();
    }
    
    public static synchronized PasseggeroService getInstance(){
        if (instance ==null) instance=new PasseggeroService();
        return  instance;
    }
    //---------------------


    public void save (Passeggero t){
        if (t==null)   
                    return;
                if(t.getId() == null || t.getId() == 0)
                    passeggeroDao.addEntity(t); 
                else
                    passeggeroDao.update(t);
        }  


    public List<Passeggero> findAll (){
        Map<Integer, Map<String,String>> ris = passeggeroDao.readAll();
        List<Passeggero> lb = new ArrayList<>();
        for(Map<String,String> m : ris.values()){
            Passeggero b = (Passeggero)Factory.getInstance().make(m);
            b.setLb(bigliettoService.findByPassenger(b.getId()));
            lb.add(b);
        }
        return lb;
    }
 
    public void delete (Integer id){
         if(id != null && id>0 ) 
          passeggeroDao.delete(id);
    }

     public List<Passeggero> findByName (String name){
        List<Passeggero> lb = new ArrayList<>();
        if(name != null && !name.isBlank() ) {
           Map<Integer, Map<String,String>> ris= passeggeroDao.findByName(name);
            for (Map<String, String> m: ris.values()){
            Passeggero b=(Passeggero)Factory.getInstance().make(m);
            b.setLb(bigliettoService.findByPassenger(b.getId()));
            lb.add(b);
                
            }
        }
        return lb;
    }

     @Override
     public Passeggero findById(Integer id) {
        Passeggero p= null; 
        if(id != null ){
            p=(Passeggero)Factory.getInstance().make(passeggeroDao.findById(id));//crea oggetto
            p.setLb(bigliettoService.findByPassenger(p.getId())); //setta la lista di biglietti 

            
         }
         return p;
       
     }

     //metodo privato per creare la lista di biglietti dentro passeggero, per ogni dao con fK

    




}
