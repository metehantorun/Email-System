package com.example.emailsistemisonhali;

import java.io.*;
import java.util.ArrayList;

public class KullaniciKayit extends Kullanici implements Kaydedilebilir {
    private String isim;
    private String soyisim;
    private String sifreOnay;

    public KullaniciKayit() {

    }

    //constructor hem parametreli hem parametresiz tanımlanarak overload edildi
    public KullaniciKayit(String isim, String soyisim, String kullaniciAdi, String sifre, String sifreOnay, ArrayList<String> kayitliKullanicilar) {
        super(kullaniciAdi, sifre, kayitliKullanicilar);
        this.isim = isim;
        this.soyisim = soyisim;
        this.sifreOnay = sifreOnay;
    }

    //interface e ait özelliği override etti
    @Override
    public void dosyayaKaydetme() {
        try {
            File file = new File("kullanicilar.txt");
            if (file.createNewFile()) {
                System.out.println("Dosya oluşturuldu: " + "kullanicilar.txt");
            }

            //String filePath = String.valueOf(getClass().getResource("/kullanicilar.txt").getPath());
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(isim + "," + soyisim + "," + getKullaniciAdi() + "," + getParola() + "\n");
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //parent class a ait metodu override etti
    @Override
    public boolean kullaniciVarMi(ArrayList<String> kayitliKullanicilar) {

        try {
            File file = new File("kullanicilar.txt");
            if (file.createNewFile()) {
                System.out.println("Dosya oluşturuldu: " + "kullanicilar.txt");
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] elemanlar = satir.split(",");
                for (String eleman : elemanlar) {
                    kayitliKullanicilar.add(eleman.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean isKullaniciAdi = false;
        for (int i = 2; i < kayitliKullanicilar.size(); i += 4) {
            if (getKullaniciAdi().equals(kayitliKullanicilar.get(i))) {
                isKullaniciAdi = true;
            }
        }

        return isKullaniciAdi;
    }

}

