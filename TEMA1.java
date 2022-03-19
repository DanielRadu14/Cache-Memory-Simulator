/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package tema1;
import java.util.*;
import java.io.*;

public class TEMA1 {

    public static void main(String[] args) throws IOException{

        File file = new File("configuratie.in");
        FileOutputStream g = new FileOutputStream("output.in");
        PrintStream gchar = new PrintStream(g);


        int contorTimestampLRUCache = 0;
        int contorLFU = 1;
        
        Subscriptie memorie[] = new Subscriptie[100];
        int nrObiecteMemorie = 0;//contor obiecte in memorie
       
        Scanner input = new Scanner(file);
        String tipCache = input.nextLine();
        int dimensiuneCache = input.nextInt();
        int numarComenzi = input.nextInt();
        int nrObiecteCache = 0;
        
        Cache cache;
        if("FIFO".equals(tipCache)){
            cache = new FIFOCache(dimensiuneCache);
        }
        else if("LFU".equals(tipCache)){
            cache = new LFUCache(dimensiuneCache);
        }
        else cache = new LRUCache(dimensiuneCache);
        
       // System.out.println(tipCache+" "  + dimensiuneCache +" " + numarComenzi);
         
        String comanda;
        String nume;
        int prescriptiiBasic = 0, prescriptiiPremium = 0;
        String linie;
        
        for(int j = 0; j < numarComenzi; j++)
        {
            comanda = input.next();
            if("ADD".equals(comanda))
            {
                int i = 0;
                linie = input.nextLine();
                
                StringTokenizer st = new StringTokenizer(linie);
                nume = st.nextToken();
                String token = null;
                while (st.hasMoreTokens()) {
                    token = st.nextToken();
                    if(i == 0) prescriptiiBasic = Integer.parseInt(token);
                    i++;
                }
                if(i == 2) prescriptiiPremium = Integer.parseInt(token);
                else prescriptiiPremium = 0;
                
                //System.out.println(comanda+" " +nume+" " +prescriptiiBasic+" " +prescriptiiPremium);
                
                int okCache = 0;
                int pozitieCache = 0;
                int okMemoriaPrincipala = 0;
                int pozitieMemoriaPrincipala = 0;
                for(int k = 0; k < nrObiecteMemorie; k++)
                {
                    if(nume.equals(memorie[k].getNume()))
                    {
                        okMemoriaPrincipala = 1;
                        pozitieMemoriaPrincipala = k;
                        
                    }
                }
                for(int k = 0; k < cache.getNrObiecteCache(); k++)
                {
                    if(cache.getNumeCache(nume) == true)
                    {
                        okCache = 1;
                        pozitieCache = k;
                        
                    }
                }
                if(okMemoriaPrincipala == 0)
                {
                    memorie[nrObiecteMemorie] = new Premium(nume,prescriptiiPremium, prescriptiiBasic);
                    contorTimestampLRUCache++;
                    memorie[nrObiecteMemorie].setTimestamp(nrObiecteMemorie+1);
                    memorie[nrObiecteMemorie].setOrdineAdaugare(nrObiecteMemorie+1);
                    nrObiecteMemorie++;
                }
                else if(okMemoriaPrincipala == 1 && okCache == 0)
                {
                    memorie[pozitieMemoriaPrincipala] = new Premium(nume,prescriptiiPremium, prescriptiiBasic);
                    memorie[pozitieMemoriaPrincipala].setTimestamp(memorie[pozitieMemoriaPrincipala].getTimestamp());
                }
                else if(okMemoriaPrincipala == 1 && okCache == 1)
                {
                    memorie[pozitieMemoriaPrincipala] = new Premium(nume,prescriptiiPremium, prescriptiiBasic);
                    memorie[pozitieMemoriaPrincipala].setTimestamp(memorie[pozitieMemoriaPrincipala].getTimestamp());
                    
                    cache.remove(null, memorie[pozitieMemoriaPrincipala]);
                    memorie[pozitieMemoriaPrincipala].setNrAccesari(0);
                }
            }
            else if("GET".equals(comanda))
            {
                int okMemoriaPrincipala = 0;
                int pozitieMemoriaPrincipala = 0;
                
                int okCache = 0;
                int pozitieCache = 0;
                nume = input.next();
                for(int k = 0; k < nrObiecteMemorie; k++)
                {
                    if(nume.equals(memorie[k].getNume()))
                    {
                        okMemoriaPrincipala = 1;
                        pozitieMemoriaPrincipala = k;
                        
                    }
                }
                for(int k = 0; k < cache.getNrObiecteCache(); k++)
                {
                    if(cache.getNumeCache(nume) == true)
                    {
                        okCache = 1;
                        pozitieCache = k;
                        
                    }
                }
                if(okCache == 1 || okMemoriaPrincipala == 1)//am accesat elementul => actualizez timestamp ptr LRU
                {
                    memorie[pozitieMemoriaPrincipala].setTimestamp(contorTimestampLRUCache+1);
                    contorTimestampLRUCache++;
                }
                if(okCache == 1)
                {
                    memorie[pozitieMemoriaPrincipala].incNrAccesari();
                    
                    gchar.printf("0 ");
                    
                    gchar.println(memorie[pozitieMemoriaPrincipala].getSubscription());//afiseaza tip subscriptie
                   
                    memorie[pozitieMemoriaPrincipala].decNrSubscriptii();//decrementeaza numar de subscriptii

                }
                else if(okMemoriaPrincipala == 1 && okCache == 0)
                {
                    memorie[pozitieMemoriaPrincipala].setOrdineAdaugare(contorLFU);
                    contorLFU++;
                    cache.add(memorie[pozitieMemoriaPrincipala]);
                    
                    gchar.printf("1 ");
                        
                    gchar.println(memorie[pozitieMemoriaPrincipala].getSubscription());//afiseaza tip subscriptie

                    memorie[pozitieMemoriaPrincipala].decNrSubscriptii();//decrementeaza numar de subscriptii
                }
                else if(okMemoriaPrincipala == 0) gchar.println("2");
            }
        }
        
    }
    
}
