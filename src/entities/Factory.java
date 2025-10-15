package entities;

import java.util.Map;

import interfaces.IFactory;

public class Factory implements IFactory {
    
   //SINGLETON------------
   private static Factory instance;

   private Factory(){}

   public static synchronized Factory getInstance(){
    if(instance==null){instance=new Factory(); 
    }
    return instance;
   }

   
    //  metodo che istanzia oggetti vuoti
    public static Entity make(String tipoOgetto){ 
        Entity e=null;
            switch (tipoOgetto.toUpperCase()){
                case "BAGAGLIO"->{
                    e=new Bagaglio();
                }
                case "PASSEGGERO"->{
                    e=new Passeggero();

                }
                 case "BIGLIETTO"->{
                    e=new Biglietto();

                }
                
                default -> System.out.println("controllare la tipologia passata ai metodi make");
        }
     return e;

    }

//  METODO che crea oggetti e li valorizza usando i dati della mappa-------------------
    public Entity make(Map<String,String> map){ 
        Entity e=null;
        if (map.containsKey("tipoOggetto")){
            String valore=map.get("tipoOggetto");
            e=make(valore);
        }
        map.remove("tipoOggetto"); //rimuovo la coppia tipo oggetto che non mi serve piu
        if (e!=null) e.fromMap(map);
        return e;

    }

    

    
}
