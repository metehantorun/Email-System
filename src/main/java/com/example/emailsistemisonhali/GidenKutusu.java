package com.example.emailsistemisonhali;


import java.io.*;
import java.util.ArrayList;

public class GidenKutusu extends Emailler implements Kaydedilebilir{

    public GidenKutusu(){

    }

    public GidenKutusu(String gonderici, String alici, String mail, String konu) {
        super(gonderici, alici, konu, mail);
    }

    public void maillerigoster(ArrayList<String> mailler) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("mailler.txt"));
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] gelenler = satir.split(",");
                for (String gelen : gelenler) {
                    mailler.add(gelen.trim());
                }
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dosyayaKaydetme() {
        try {
            File file = new File("mailler.txt");
            if (file.createNewFile()) {
                System.out.println("Dosya oluşturuldu: " + "mailler.txt");
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(getGonderici() + "," + getAlici() + "," + getKonu() + "," + getMail() + "\n");
            bw.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
