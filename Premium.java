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
public class Premium extends Basic{
    private int nrPremium;
    public Premium(String nume, int nrPremium, int nrBasic) {
        super(nume,nrBasic);
        this.nrPremium = nrPremium;
    }
    public String getSubscription(){
        if(nrPremium > 0) return "Premium";
        else return super.getSubscription();
    }
    public void decNrSubscriptii(){
        if(nrPremium > 0) nrPremium--;
        else super.decNrSubscriptii();
    }
    
}
