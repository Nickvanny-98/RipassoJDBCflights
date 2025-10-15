package interfaces;


import java.util.Map;

import entities.Entity;

public interface GenericDao <T extends Entity>{

    Integer addEntity(T e);            
    Map<Integer,Map<String, String>> readAll(); 
    void update(T e );          
    void delete (Integer id);      
    Map<String, String> findById(Integer id);
    
}
