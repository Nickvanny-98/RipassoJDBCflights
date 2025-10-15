package controller;

import entities.Passeggero;
import service.PasseggeroService;
import view.View;

public class PasseggeroController {
    private final PasseggeroService passeggeroService;
    private final View view;

    public PasseggeroController(){
         passeggeroService= PasseggeroService.getInstance();
        view =new View("templates");
    }
    

    //richiesta che risponde alla visualizzazione della lista degli album.

    public String list(){
        //StringBuilder stringBuilder= new StringBuilder();
       String passeggeroGraficato=new String();
        for (Passeggero passeggero: passeggeroService.findAll()){

            //stringBuilder.append(view.renderAlbum(a));
            passeggeroGraficato+=view.renderPasseggeri(passeggero)+"\n";
        }
        return passeggeroGraficato;
    }
    // L-abbiamo commentato perche il nostro FindAll ritorna gia la lista con i bagagli
    // public String listWithBaggage(){
    //    String bigliettoGraficato =new String();
    //     for (Biglietto b: bigliettoService.findAllAndBaggage()){

    //         bigliettoGraficato+=view.renderBiglietto(b)+"\n";
    //     }
    //     return bigliettoGraficato;
    // }

    // Inserire metodi di inserimento etc.. RICORDA


}

    

