package com.example.emailsistemisonhali;

import java.io.*;
import java.util.ArrayList;

public class GelenKutusu extends Emailler implements Okunabilir{

    public GelenKutusu(String gonderici, String alici,String konu ,String mail){
        super(gonderici,alici,konu,mail);
    }


    @Override
    public void dosyaOkuma(ArrayList<String> gelenMailler) {

        try{
            BufferedReader br = new BufferedReader(new FileReader("mailler.txt"));
            String satir;
            while ((satir = br.readLine()) != null){
                String[] gelenler = satir.split(",");
                for(String gelen:gelenler) {
                    gelenMailler.add(gelen.trim());
                }
            }
        }

        catch(IOException e){
            e.printStackTrace();
        }
    }
}

