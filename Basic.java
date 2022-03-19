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
public class Basic extends Free{
    private int nrBasic;
    public Basic(String nume, int nrBasic) {
        super(nume);
        this.nrBasic = nrBasic;
    }
    public String getSubscription(){
        if(nrBasic > 0) return "Basic";
        else return super.getSubscription();
    }
    public void decNrSubscriptii(){
        if(nrBasic > 0) nrBasic--;
    }
    
}
