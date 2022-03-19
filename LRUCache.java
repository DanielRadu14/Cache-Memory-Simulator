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
public class LRUCache implements Cache{
    
    private Subscriptie cache[];
    private int nrObiecteCache;
    private int dimensiuneCache;
    
    public LRUCache(int dimensiuneCache){
        cache = new Subscriptie[dimensiuneCache];
        this.nrObiecteCache = 0;
        this.dimensiuneCache = dimensiuneCache;
    }
    public int getNrObiecteCache(){
        return nrObiecteCache;
    }
    public boolean getNumeCache(String nume){
        for(int i = 0; i < nrObiecteCache; i++)
        {
            if(nume.equals(cache[i].getNume())){
                
                return true;
            }
        }
        return false;
    }
    public void add(Subscriptie o){
        if(nrObiecteCache == dimensiuneCache)
        {
            int pozitieDeEliminat = 0;
            int min = 9999;
            for(int i = 0; i < nrObiecteCache; i++)
            {
                //System.out.println("Compar timestamp " + cache[i].getTimestamp() + " cu " + min);
                if(cache[i].getTimestamp() < min)
                {
                    min = cache[i].getTimestamp();
                    pozitieDeEliminat = i;
                }
            }
            remove(o,cache[pozitieDeEliminat]);
        }
        else
        {
            //System.out.println("Introduc element la index " + nrObiecteCache);    
            cache[nrObiecteCache] = o;
            nrObiecteCache++;
        }
    }
    public void remove(Subscriptie adauga, Subscriptie elimina){
        int pozitieDeEliminat = 0;
        for(int i = 0; i < nrObiecteCache; i++)
        {
            if(cache[i].getNume().equals(elimina.getNume()))
            {
                pozitieDeEliminat = i;
            }
        }
        //System.out.println("Scot obiectul " + cache[pozitieDeEliminat].getNume());
        cache[pozitieDeEliminat] = adauga;
        if(adauga == null)
        {
            for(int i = pozitieDeEliminat; i < nrObiecteCache-1; i++)
            {
                cache[i] = cache[i+1];
            }
            cache[nrObiecteCache-1] = null;
            nrObiecteCache--;
        }
    }
    public void afiseazaCache()
    {
        
    }
}
