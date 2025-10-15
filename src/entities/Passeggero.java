package entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Passeggero  extends Entity{
  
  private String nome;
  private String cognome;
  private Date dataNascita;
  private String numeroDocumento;  
  private String nazionalita;
  private String email;
  private String numeroTelefono;  
  private List<Biglietto> listaBiglietti = new ArrayList<>();

   public Passeggero(){}

   public Passeggero(Integer id, String nome, String cognome, Date dataNascita, String numeroDocumento,
      String nazionalita, String numeroTelefono) {
    super(id);
    this.nome = nome;
    this.cognome = cognome;
    this.dataNascita = dataNascita;
    this.numeroDocumento = numeroDocumento;
    this.nazionalita = nazionalita;
    this.numeroTelefono = numeroTelefono;
  }

   @Override
   public String toString() {
    return "Passeggero ["+ super.toString() + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita + ", numeroDocumento="
        + numeroDocumento + ", nazionalita=" + nazionalita + ", numeroTelefono=" + numeroTelefono +  "]";
   }
   
   
   

   public String getNome() {
     return nome;
   }

   public void setNome(String nome) {
     this.nome = nome;
   }

   public String getCognome() {
     return cognome;
   }

   public void setCognome(String cognome) {
     this.cognome = cognome;
   }

   public Date getDataNascita() {
     return dataNascita;
   }

   public void setDataNascita(Date dataNascita) {
     this.dataNascita = dataNascita;
   }

   public String getNumeroDocumento() {
     return numeroDocumento;
   }

   public void setNumeroDocumento(String numeroDocumento) {
     this.numeroDocumento = numeroDocumento;
   }

   public String getNazionalita() {
     return nazionalita;
   }

   public void setNazionalita(String nazionalita) {
     this.nazionalita = nazionalita;
   }

   public String getNumeroTelefono() {
     return numeroTelefono;
   }

   public void setNumeroTelefono(String numeroTelefono) {
     this.numeroTelefono = numeroTelefono;
   }

   public String getEmail() {
     return email;
   }

   public void setEmail(String email) {
     this.email = email;
   }

   public List<Biglietto> getLb() {
     return listaBiglietti;
   }

   public void setListaBiglietti(List<Biglietto> listaBiglietti) {
    if(listaBiglietti == null) listaBiglietti = new ArrayList<>();
     this.listaBiglietti = listaBiglietti;
   }
 



  
    
}
