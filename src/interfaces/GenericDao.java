package interfaces;


import java.util.Map;

import entities.Entity;

public interface GenericDao <T extends Entity>{

    Integer addEntity(T e, Integer... FK);
    Map<Integer,Map<String, String>> readAll();
    Map<String, String> findById(Integer id); 
    void update(T e, Integer... FK);          
    void delete (Integer id);      
    
    
}
