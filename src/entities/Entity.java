package entities;

import interfaces.IMappable;

public abstract class Entity implements IMappable{
    
     private Integer id; 


    public Entity(Integer id) {
        this.id = id;
    }

    public Entity() {
    }

    /*
    @Override //    METODO CHE GENERALIZZA IL TOSTRING, non c'è più bisogno di fare override
    public String toString() {
        String ris ="{"+getClass().getSimpleName()+"\n";
        for (Entry<String,String> coppia :toMap().entrySet()){
            ris+= "\n"+coppia.getKey()+":"+coppia.getValue();
        }
        
       ris+= "\n}";



        return ris;
    }
*/

    public String toString() {
        return "[id: "+id+"]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
}
