package com.example.emailsistemisonhali;

import java.util.ArrayList;

public abstract class Kullanici {
    private String kullaniciAdi;
    private String parola;
    private ArrayList<String> kayitliKullanicilar;

    public Kullanici(){

    }

    public Kullanici(String kullaniciAdi,String parola,ArrayList<String> kayitliKullanicilar){
        this.kullaniciAdi=kullaniciAdi;
        this.parola=parola;
        this.kayitliKullanicilar=kayitliKullanicilar;

    }

    public String getKullaniciAdi(){
        return kullaniciAdi;
    }

    public String getParola(){
        return parola;
    }

    public ArrayList<String> getKayitliKullanicilar(){
        return kayitliKullanicilar;
    }

    //Miras alan sınıfların override etmesi gereken soyut metod
    public abstract boolean kullaniciVarMi(ArrayList<String> arrayList);
}

