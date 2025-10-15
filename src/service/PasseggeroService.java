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
        if (instance == null) instance = new PasseggeroService();
        return  instance;
    }
    //---------------------


    public void save (Passeggero passeggero){
        if (passeggero==null)   
                    return;
                if(passeggero.getId() == null || passeggero.getId() == 0)
                    passeggeroDao.addEntity(passeggero); 
                else
                    passeggeroDao.update(passeggero);
        }  


    public List<Passeggero> findAll (){
        Map<Integer, Map<String,String>> mappaPasseggeri = passeggeroDao.readAll();
        List<Passeggero> listaPasseggeri = new ArrayList<>();
        for(Map<String,String> mappa : mappaPasseggeri.values()){
            Passeggero passeggero = (Passeggero)Factory.getInstance().make(mappa);
            passeggero.setListaBiglietti(bigliettoService.findByPassenger(passeggero.getId()));
            listaPasseggeri.add(passeggero);
        }
        return listaPasseggeri;
    }
 
    public void delete (Integer id){
         if(id != null && id>0 ) 
          passeggeroDao.delete(id);
    }

     public List<Passeggero> findByName (String name){
        List<Passeggero> listaPasseggeri = new ArrayList<>();
        if(name != null && !name.isBlank() ) {
           Map<Integer, Map<String,String>> mappaPasseggeri= passeggeroDao.findByName(name);
            for (Map<String, String> mappa: mappaPasseggeri.values()){
            Passeggero passeggero=(Passeggero)Factory.getInstance().make(mappa);
            passeggero.setListaBiglietti(bigliettoService.findByPassenger(passeggero.getId()));
            listaPasseggeri.add(passeggero);
                
            }
        }
        return listaPasseggeri;
    }

     @Override
     public Passeggero findById(Integer id) {
        Passeggero passeggero= null; 
        if(id != null ){
            passeggero=(Passeggero)Factory.getInstance().make(passeggeroDao.findById(id));//crea oggetto
            passeggero.setListaBiglietti(bigliettoService.findByPassenger(passeggero.getId())); //setta la lista di biglietti 

            
         }
         return passeggero;
       
     }

     //metodo privato per creare la lista di biglietti dentro passeggero, per ogni dao con fK

    




}
