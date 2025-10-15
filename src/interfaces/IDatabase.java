package interfaces;

import java.util.Map;

public interface IDatabase {

    public Map<Integer,Map<String,String>> executeDQL(String query,String... parametri);
    
    public Integer executeUpdate(String comando,String... parametri);

    
}
