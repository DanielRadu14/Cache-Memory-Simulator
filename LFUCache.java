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
public class LFUCache implements Cache{
    
    private Subscriptie cache[];
    private int nrObiecteCache;
    private int dimensiuneCache;
    public LFUCache(int dimensiuneCache){
        
        cache = new Subscriptie[dimensiuneCache];
        this.nrObiecteCache = 0;
        this.dimensiuneCache = dimensiuneCache;
    }
    public void afiseazaCache()
    {
        for(int i = 0; i < nrObiecteCache; i++)
        {
            System.out.printf("%s-%d-%d\n", cache[i].getNume(), cache[i].getNrAccesari(), cache[i].getOrdineAdaugare());
        }
        System.out.printf("\n");
    }
    public void add(Subscriptie o){
        if(nrObiecteCache == dimensiuneCache)
        {
            int min = 9999;
            int pozitieDeEliminat = 0;
            for(int i = 0; i < nrObiecteCache; i++)
            {
                if(cache[i].getNrAccesari() <= min)
                {
                    if(min == cache[i].getNrAccesari())
                    {
                        if(cache[pozitieDeEliminat].getOrdineAdaugare() < cache[i].getOrdineAdaugare())
                        {
                            min = cache[pozitieDeEliminat].getNrAccesari();
                            pozitieDeEliminat = pozitieDeEliminat;
                        }
                        else
                        {
                            min = cache[i].getNrAccesari();
                            pozitieDeEliminat = i;
                        }
                    }
                    else if(cache[i].getNrAccesari() < min)
                    {
                        min = cache[i].getNrAccesari();
                        pozitieDeEliminat = i;
                    }
                }
            }
            remove(o, cache[pozitieDeEliminat]);
        }
        else
        {   
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
    public int getNrObiecteCache()
    {
        return nrObiecteCache;
    }
    public boolean getNumeCache(String nume){
        
        for(int i = 0; i < nrObiecteCache; i++)
        {
            if(nume.equals(cache[i].getNume()))
            {
                return true;
            }
        }
        return false;
    }
}
