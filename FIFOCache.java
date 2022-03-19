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
public class FIFOCache implements Cache{
    
    private Subscriptie cache[];
    private int nrObiecteCache;
    private int dimensiuneCache;
    
    public FIFOCache(int dimensiuneCache){
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
            //System.out.println("Compar numele de la index " + i);
            if(nume.equals(cache[i].getNume())){
                
                return true;
            }
        }
        return false;
    }
    public void add(Subscriptie o){
        if(nrObiecteCache == dimensiuneCache)
        {
            //System.out.println("Scot obiectul " + cache[0].getNume());
            remove(o, cache[0]);
        }
        else
        {
            //System.out.println("Introduc element la index " + nrObiecteCache);    
            cache[nrObiecteCache] = o;
            nrObiecteCache++;
        }
    }
    public void afiseazaCache()
    {
        
    }
    public void remove(Subscriptie adauga, Subscriptie elimina){
        int pozitieDeEliminat = 0;
        for(int i=0; i < nrObiecteCache; i++)
        {
            if(elimina.getNume().equals(cache[i].getNume()))
            {
                pozitieDeEliminat = i;
            }
        }
        for(int i = pozitieDeEliminat; i < nrObiecteCache-1; i++)
        {
            cache[i] = cache[i+1];
        }
        cache[nrObiecteCache-1] = adauga;
        if(adauga == null) nrObiecteCache--;
    }
}
