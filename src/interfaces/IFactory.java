package interfaces;

import java.util.Map;

import entities.Entity;

public interface IFactory { //IFactory <F,P>
    //chi utilizza questa interfaccia deve indicare:
    // il tipo formale (uso segnaposto F) 
    //e parametri (P) che deve prendere in input per restituirmi l'oggetto creato
    
    Entity make(Map<String, String> map);




}