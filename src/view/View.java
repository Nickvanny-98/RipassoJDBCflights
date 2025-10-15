package view;

import java.util.List;
import java.util.Scanner;

import entities.Bagaglio;
import entities.Biglietto;
import entities.Entity;
import entities.Passeggero;

import java.io.File;


public class View {

    private String percorsoTemplate;

    public View (String percorsoTemplate){
        this.percorsoTemplate= percorsoTemplate;
    }
    
    public String getPercorsoTemplate(){
        return percorsoTemplate;
    }

    public void SetPercorsoTemplate(String percorsoTemplate){
        this.percorsoTemplate= percorsoTemplate;

    }

    public String loadTemplate(String nomeTemplate) {
        String templateCaricato="";
        String percorsoTemplateCompleto=percorsoTemplate+"/"+nomeTemplate;
        try (Scanner scanner =new Scanner(new File (percorsoTemplateCompleto))){
            while (scanner.hasNextLine()){
                templateCaricato +=scanner.nextLine()+"\n";
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        return templateCaricato;
    }

    public String render (Entity e) {
        String template=loadTemplate("Entity.txt");
        template=template.replace("[ID]", String.valueOf(e.getId()));
        if (e instanceof Bagaglio bagaglio){
            template+=renderBagaglio(bagaglio);
        } else if (e instanceof Biglietto bagaglio){
            template+=renderBiglietto(bagaglio);
        } else if (e instanceof Passeggero passeggero) {
            template+= renderPasseggeri(passeggero);
        }
        return template;
    }

    public String renderBagaglio (Entity e){
        String template =loadTemplate("Bagaglio.txt");
        
        if(e instanceof Bagaglio bagaglio){
            template=template.replace("[ETICHETTA]", bagaglio.getCodiceEtichetta())   
                            .replace("[PESO]", String.valueOf(bagaglio.getPeso()))
                            .replace("[SOVRAPPESO]", bagaglio.isOversize()? "SI": "NO");

        } return template;
    }

    public String renderBiglietto (Entity e){
        String template =loadTemplate("Biglietto.txt");
        
        if(e instanceof Biglietto biglietto){
            template=template.replace("[NUMERO]", biglietto.getNumeroBiglietto())   
                             .replace("[CLASSE]", ""+biglietto.getClasseTariffaria())
                             .replace("[CABINA]", biglietto.getCabinClass())
                             .replace("[POSTO]", biglietto.getNumeroPosto())
                             .replace("[PREZZO]", String.valueOf(biglietto.getPrezzo()))
                             .replace("[VALUTA]", ""+ biglietto.getValuta())
                             .replace("[STATO]", biglietto.getStatoBiglietto())
                             .replace("[LISTA BAGAGLI]", renderListaBagagli(biglietto.getBagagli()));

        } return template;
    }

    public String renderListaBagagli (List<Bagaglio> listaBagagli){
        String ris="";          
        for (Bagaglio bagaglio:listaBagagli){
            ris+=renderBagaglio(bagaglio);

        } return ris;
    }

     public String renderPasseggeri (Entity e){
        String template =loadTemplate("Passeggeri.txt");
        
        if(e instanceof Passeggero passeggero){
            template=template.replace("[NOME]", passeggero.getNome())   
                             .replace("[COGNOME]", passeggero.getCognome())
                             .replace("[DOB]", ""+ passeggero.getDataNascita())
                             .replace("[N.DOCUMENTO]", passeggero.getNumeroDocumento())
                             .replace("[NAZIONALITA]", passeggero.getNazionalita())
                             .replace("[EMAIL]", ""+ passeggero.getEmail())
                             .replace("[TELEFONO]", passeggero.getNumeroTelefono())
                             .replace("[LISTA BIGLIETTI]", renderListaBiglietti(passeggero.getLb()));

        } return template;
    }

     public String renderListaBiglietti (List<Biglietto> listaBiglietti){
        String ris="";          
        for (Biglietto biglietto:listaBiglietti){
            ris+=renderBiglietto(biglietto);

        } return ris;
    }

}
