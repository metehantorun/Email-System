package com.example.emailsistemisonhali;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KullaniciGiris extends Kullanici implements Okunabilir{
    //Sınıfa ait parametresiz constructor
    public KullaniciGiris(){

    }

    //Sınıfa ait parametreli constructor
    public KullaniciGiris(String kullaniciAdi,String parola,ArrayList<String> kayitliKullanicilar){
        super(kullaniciAdi, parola,kayitliKullanicilar);


    }

    //kullanıcı adının sistemde olup olmadığını kontrol eder
    @Override
    public boolean kullaniciVarMi(ArrayList<String> kullanicilar) {
        boolean kayit = false;
        for(int i = 2; i< kullanicilar.size();i+=4){
            if(getKullaniciAdi().equals(kullanicilar.get(i))){
                kayit = true;
            }
        }
        return kayit;
    }


    //kayıtlı kullanıcıların olduğu dosyayı okuyor
    @Override
    public void dosyaOkuma(ArrayList<String> kullanicilar) {
        try{
            BufferedReader br = new BufferedReader(new FileReader("kullanicilar.txt"));
            String satir;
            while ((satir = br.readLine()) != null){
                String[] elemanlar = satir.split(",");
                for(String eleman:elemanlar){
                    kullanicilar.add(eleman.trim());
                }
            }
        }

        catch (IOException e){
            e.printStackTrace();
        }
    }
}

