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
public interface Cache{
    public void afiseazaCache();
    public void add(Subscriptie o);
    public void remove(Subscriptie o, Subscriptie elimina);
    public int getNrObiecteCache();
    public boolean getNumeCache(String nume);
}
