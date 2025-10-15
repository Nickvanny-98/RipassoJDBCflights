package entities;

import java.util.ArrayList;
import java.util.List;

public class Biglietto extends Entity {

    private String numeroBiglietto;
    private char classeTariffaria;
    private String cabinClass; // enum  nomy, Business, First
    private String numeroPosto;
    private Double prezzo;
    private char valuta;
    private String statoBiglietto; // enum CONFIRMED, CANCELED,USED

    private List<Bagaglio> bagagli;

    public List<Bagaglio> getBagagli() {
        return bagagli;
    }

    public void setBagagli(List<Bagaglio> bagagli) {
        if(bagagli == null) bagagli = new ArrayList<>();
        this.bagagli = bagagli;
    }

    public Biglietto(){

    }
    
    public Biglietto(Integer id, String numeroBiglietto, char classeTariffaria, String cabinClass, String numeroPosto,
            Double prezzo, char valuta, String statoBiglietto) {
        super(id);
        this.numeroBiglietto = numeroBiglietto;
        this.classeTariffaria = classeTariffaria;
        this.cabinClass = cabinClass;
        this.numeroPosto = numeroPosto;
        this.prezzo = prezzo;
        this.valuta = valuta;
        this.statoBiglietto = statoBiglietto;
    }

    
   
    @Override
    public String toString() {
        return "Biglietto ["+ super.toString() +", numeroBiglietto=" + numeroBiglietto + ", classeTariffaria=" + classeTariffaria
                + ", cabinClass=" + cabinClass + ", numeroPosto=" + numeroPosto + ", prezzo=" + prezzo + ", valuta="
                + valuta + ", statoBiglietto=" + statoBiglietto +  "]";
    }

    public String getNumeroBiglietto() {
        return numeroBiglietto;
    }
    public void setNumeroBiglietto(String numeroBiglietto) {
        this.numeroBiglietto = numeroBiglietto;
    }
    public char getClasseTariffaria() {
        return classeTariffaria;
    }
    public void setClasseTariffaria(char classeTariffaria) {
        this.classeTariffaria = classeTariffaria;
    }
    public String getCabinClass() {
        return cabinClass;
    }
    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }
    public String getNumeroPosto() {
        return numeroPosto;
    }
    public void setNumeroPosto(String numeroPosto) {
        this.numeroPosto = numeroPosto;
    }
    public Double getPrezzo() {
        return prezzo;
    }
    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }
    public char getValuta() {
        return valuta;
    }
    public void setValuta(char valuta) {
        this.valuta = valuta;
    }
    public String getStatoBiglietto() {
        return statoBiglietto;
    }
    public void setStatoBiglietto(String statoBiglietto) {
        this.statoBiglietto = statoBiglietto;
    }




}
