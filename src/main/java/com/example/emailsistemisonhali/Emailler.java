package com.example.emailsistemisonhali;


import java.util.ArrayList;


public abstract class Emailler {
    private String gonderici;
    private String alici;
    private String mail;
    private String konu;

    public Emailler(){

    }

    public Emailler(String gonderici,String alici,String konu,String mail){
        this.gonderici=gonderici;
        this.alici=alici;
        this.konu=konu;
        this.mail=mail;
    }


    public String getGonderici(){
        return gonderici;
    }

    public String getAlici(){
        return alici;
    }

    public String getKonu(){
        return konu;
    }

    public String getMail(){
        return mail;
    }





}

