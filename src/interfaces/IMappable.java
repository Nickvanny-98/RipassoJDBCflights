package interfaces;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public interface IMappable{

    //legge i dati da mappa e li assegna alle proprietà dell'oggetti 
   default void fromMap(Map<String,String> map){
        for(Method m : this.getClass().getMethods()){
            if(m.getName().startsWith("set") && m.getParameterCount()==1){
                String nome = m.getName().substring(3);//DataRilascio -> dataRilascio
                nome = Character.toLowerCase(nome.charAt(0)) + nome.substring(1);
                if(map.containsKey(nome)){
                    //se c'è la chiave prendo il valore associato
                    String valore = map.get(nome);
                    //o salto l'iterazione se il valore da passare al set è null
                    if(valore == null) continue;
                    String tipoParametro = m.getParameters()[0].getType().getSimpleName().toLowerCase();
                    try{
                        switch(tipoParametro){
                            case"string"->m.invoke(this,valore);
                            case"double"->{if(valore!=null)m.invoke(this, Double.parseDouble(valore));}//aggiungo controllo null se non lo metto a riga 16
                            case"Integer"->m.invoke(this,Integer.parseInt(valore));

                        }
                    }catch (Exception e ){

                    }
                }
            }
        }
    }

    default Map<String,String> toMap(){
        Map<String,String> ris=new HashMap<>();
       
        for(Method m:this.getClass().getMethods()){ 
           
           // controllo GETTERS (O isX)
           if (m.getName().startsWith("get")     || (m.getName().startsWith("is"))
                &&!m.getName().equalsIgnoreCase("getClass") &&m.getParameterCount()==0){
                 int index=m.getName().startsWith("get")?3:2;  
                 String nome=m.getName().substring(index);
                 nome= Character.toLowerCase(nome.charAt(0))+nome.substring(1);
               
                try {
                 //senza controlli: String valore=String.valueOf(m.invoke(this)); //metodo invocato dall'oggetto usato come parametro, funziona al contrario!
                
                String valore=null;
                 if (index==2){valore =m.invoke(this).toString().equalsIgnoreCase("true")?"1":"0";
                 }else if (index==3){valore=String.valueOf(m.invoke(this));}
                 
                ris.put(nome,valore);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println("errore nel metodo toMap(), durante l'invocazione di "+m.getName());
                    e.printStackTrace();
                } 
            }


        }
        return ris;

       

    }

}