package controller;

import entities.Bagaglio;
import service.BagaglioService;
import view.View;

public class BagaglioController {
private final BagaglioService bagaglioService;
private final View view;

    public BagaglioController(){
         bagaglioService= BagaglioService.getInstance();
        view =new View("templates");
    }
    

    //richiesta che risponde alla visualizzazione della lista degli album.

    public String list(){
        //StringBuilder stringBuilder= new StringBuilder();
       String bagaglioGraficato=new String();
        for (Bagaglio b: bagaglioService.findAll()){

            //stringBuilder.append(view.renderAlbum(a));
            bagaglioGraficato+=view.renderBagaglio(b)+"\n";
        }
        return bagaglioGraficato;
    }
    // L-abbiamo commentato perche il nostro FindAll ritorna gia la lista con i bagagli
    // public String listWithBaggage(){
    //    String bigliettoGraficato =new String();
    //     for (Biglietto b: bigliettoService.findAllAndBaggage()){

    //         bigliettoGraficato+=view.renderBiglietto(b)+"\n";
    //     }
    //     return bigliettoGraficato;
    // }

    // Inserire metodi di inserimento etc..
    
}
