package controller;

import entities. Biglietto;
import service. BigliettoService;
import view.View;

public class BigliettoController {

    private final BigliettoService bigliettoService;
    private final View view;

    public BigliettoController(){
         bigliettoService= BigliettoService.getInstance();
        view =new View("templates");
    }
    

    //richiesta che risponde alla visualizzazione della lista degli album.

    public String list(){
        //StringBuilder stringBuilder= new StringBuilder();
       String bigliettoGraficato=new String();
        for (Biglietto biglietto: bigliettoService.findAll()){

            //stringBuilder.append(view.renderAlbum(a));
            bigliettoGraficato+=view.renderBiglietto(biglietto)+"\n";
        }
        return bigliettoGraficato;
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
