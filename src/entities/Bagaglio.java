package entities;

public class Bagaglio extends Entity{
   
  private String codiceEtichetta;
  private Double peso;
  private boolean isOversize;

  
  @Override
  public String toString() {
    return "Bagaglio ["+ super.toString() +", codiceEtichetta=" + codiceEtichetta + ", peso=" + peso + ", isOversize=" + isOversize
        + "]";
  }

   public Bagaglio(){

   }

  public Bagaglio(Integer id, String codiceEtichetta, Double peso, boolean isOversize) {
    super(id);
    this.codiceEtichetta = codiceEtichetta;
    this.peso = peso;
    this.isOversize = isOversize;
  }
 
  public String getCodiceEtichetta() {
    return codiceEtichetta;
  }
  public void setCodiceEtichetta(String codiceEtichetta) {
    this.codiceEtichetta = codiceEtichetta;
  }
  public Double getPeso() {
    return peso;
  }
  public void setPeso(Double peso) {
    this.peso = peso;
  }
  public boolean isOversize() {
    return isOversize;
  }
  public void setOversize(boolean isOversize) {
    this.isOversize = isOversize;
  }


 // private Album album;  //contiene la Fk album_id int , quindi creo un oggetto album 
                        //FOREIGN KEY (album_id) REFERENCES album (id)


  
  
 
 

  
    
}
