/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package tema1;

/**
 *
 * @author danyr
 */
public abstract class Subscriptie {
    private String nume;
    private int timestamp;
    private int nrAccesari;
    private int ordineAdaugare;
    
    public Subscriptie(String nume){
        this.nume = nume;
        timestamp = 0;
        nrAccesari = 0;
        ordineAdaugare = 0;
    }
    public String getNume()
    {
        return nume;
    }
    public void setTimestamp(int timestamp){
        this.timestamp = timestamp;
    }
    public int getTimestamp(){
        return timestamp;
    }
    public void incNrAccesari()
    {
        nrAccesari = nrAccesari+1;
    }
    public void setNrAccesari(int nrAccesari)
    {
        this.nrAccesari = nrAccesari;
    }
    public int getNrAccesari(){
        return nrAccesari;
    }
    public void setOrdineAdaugare(int ordineAdaugare){
        this.ordineAdaugare = ordineAdaugare;
    }
    public int getOrdineAdaugare()
    {
        return ordineAdaugare;
    }
    public abstract String getSubscription();
    public abstract void decNrSubscriptii();
}
