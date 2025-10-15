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
        if (e instanceof Bagaglio s){
            template+=renderBagaglio(s);
        } else if (e instanceof Biglietto a){
            template+=renderBiglietto(a);
        } else if (e instanceof Passeggero b) {
            template+= renderPasseggeri(b);
        }
        return template;
    }

    public String renderBagaglio (Entity bagaglio){
        String template =loadTemplate("Bagaglio.txt");
        
        if(bagaglio instanceof Bagaglio a){
            template=template.replace("[ETICHETTA]", a.getCodiceEtichetta())   
                            .replace("[PESO]", String.valueOf(a.getPeso()))
                            .replace("[SOVRAPPESO]", a.isOversize()? "SI": "NO");

        } return template;
    }

    public String renderBiglietto (Entity e){
        String template =loadTemplate("Biglietto.txt");
        
        if(e instanceof Biglietto a){
            template=template.replace("[NUMERO]", a.getNumeroBiglietto())   
                             .replace("[CLASSE]", ""+a.getClasseTariffaria())
                             .replace("[CABINA]", a.getCabinClass())
                             .replace("[POSTO]", a.getNumeroPosto())
                             .replace("[PREZZO]", String.valueOf(a.getPrezzo()))
                             .replace("[VALUTA]", ""+ a.getValuta())
                             .replace("[STATO]", a.getStatoBiglietto())
                             .replace("LISTA BAGAGLI", renderListaBagagli(a.getBagagli()));

        } return template;
    }

    public String renderListaBagagli (List<Bagaglio> lb){
        String ris="";          
        for (Bagaglio s:lb){
            ris+=renderBagaglio(s);

        } return ris;
    }

     public String renderPasseggeri (Entity e){
        String template =loadTemplate("Passeggeri.txt");
        
        if(e instanceof Passeggero a){
            template=template.replace("[NOME]", a.getNome())   
                             .replace("[COGNOME]", a.getCognome())
                             .replace("[DOB]", ""+ a.getDataNascita())
                             .replace("[N.DOCUMENTO]", a.getNumeroDocumento())
                             .replace("[NAZIONALITA]", a.getNazionalita())
                             .replace("[EMAIL]", ""+ a.getEmail())
                             .replace("[TELEFONO]", a.getNumeroTelefono())
                             .replace("[LISTA BIGLIETTI]", renderListaBiglietti(a.getLb()));

        } return template;
    }

     public String renderListaBiglietti (List<Biglietto> lb){
        String ris="";          
        for (Biglietto s:lb){
            ris+=renderBiglietto(s);

        } return ris;
    }

}
