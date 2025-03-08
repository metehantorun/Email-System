package com.example.emailsistemisonhali;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class TSAController {
    //java fx bileşenlerinin kontrolü için fxml etiketi ile değişkenler tanımlanıyor
    @FXML
    private TextField girisKullaniciadi;
    @FXML
    private PasswordField girisSifre;
    @FXML
    private AnchorPane girisEkrani;
    @FXML
    private TabPane mailEkrani;
    @FXML
    private AnchorPane kayitEkrani;
    @FXML
    private TextField kayitIsim;
    @FXML
    private TextField kayitSoyisim;
    @FXML
    private TextField kayitKullaniciAdi;
    @FXML
    private PasswordField kayitParola;
    @FXML
    private PasswordField kayitParolayiOnayla;
    @FXML
    private Button gelenmailler;
    @FXML
    private Button gelenmaibuton;
    @FXML
    private TextField gonderici;
    @FXML
    private TextField alici;
    @FXML
    private TextArea mail;
    @FXML
    private Button maillerigoster;
    @FXML
    private TextArea mailicerik;
    @FXML
    private TextArea gidenkutusu;
    @FXML
    private TextField konu;
    @FXML
    private Button mailGizleButon;
    @FXML
    private Button mailGizleButonGiden;

    //giriş tuşuna enter ile basınca tuşun aktif hale gelmesini sağlar
    @FXML
    public void initialize() {
        girisKullaniciadi.setOnAction(event -> {
            try {
                girisYap();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        girisSifre.setOnAction(event -> {
            try {
                girisYap();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    //giriş yap butonuna basınca tetiklenecek metod
    @FXML
    public void girisYap() throws IOException {

        String kullaniciAdi = girisKullaniciadi.getText();//kullanıcı adı ve şifreyi kullanıcıdan alır
        String parola = girisSifre.getText();
        ArrayList<String> kullanicilar = new ArrayList<>(); //kullanıcıların dosyadan okunup kaydedildiği arraylist

        KullaniciGiris kullaniciGiris = new KullaniciGiris(kullaniciAdi,parola,kullanicilar);
        kullaniciGiris.dosyaOkuma(kullanicilar);//kullanıcıların kayıtlı olduğu dosyadaki verileri bir arraylist içerisine kaydeder

        //kullanıcının arraylist içerisinde hangi sırada olduğunu bulur ve o sıradaki kullanıcının hesap bilgileriyle eşleşme sağlayıp uygulamaya giriş yapar
        int sira = -1;
        for(int i = 0; i< kullanicilar.size();i++){
            if(kullaniciAdi.equals(kullanicilar.get(i))){
                sira = i;
            }
        }

        //kullanıcı adı veya parola boşsa
        if (kullaniciAdi.isEmpty() || parola.isEmpty()){
            JOptionPane.showMessageDialog(null, "Lütfen kullanıcı adı ve şifreyi boş bırakmayın");
        }

        //kullanıcı sistemde tanımlı mı diye bakar
        else if(!kullaniciGiris.kullaniciVarMi(kullanicilar)){
            JOptionPane.showMessageDialog(null,"Kullanıcı sistemde kayıtlı değil");
        }

        //parolayi kontrol eder
        else if (parola.equals(kullanicilar.get(sira+1))){
            JOptionPane.showMessageDialog(null, "Giriş başarılı");
            mailEkrani.setVisible(true);
            mailicerik.setText("");
            gidenkutusu.setText("");
            alici.setText("");
            konu.setText("");
            mail.setText("");
        }

        //şifre yanlış ise
        else{
            JOptionPane.showMessageDialog(null, "Şifre hatalı");
        }


    }

    //kayıt işlemi gerçekleştiren fonksiyon
    @FXML
    public void kaydol() throws IOException {
        //kullanıcının kayıt ekranında girdiği bilgileri değişkenler içerisinde tutar
        String isim= kayitIsim.getText();
        String soyisim= kayitSoyisim.getText();
        String kullaniciAdi= kayitKullaniciAdi.getText();
        String sifre= kayitParola.getText();
        String sifreOnay= kayitParolayiOnayla.getText();
        ArrayList<String> kayitliKullanicilar=new ArrayList();

        KullaniciKayit kullaniciKayit = new KullaniciKayit(isim,soyisim,kullaniciAdi,sifre,sifreOnay,kayitliKullanicilar);

        //eğer kayıt alanları boş olursa kullanıcıyı uyarır ve kaydetmez
        if(isim.isEmpty() || soyisim.isEmpty() || kullaniciAdi.isEmpty()|| sifre.isEmpty()||sifreOnay.isEmpty()){
            JOptionPane.showMessageDialog(null, "Lütfen boş alanı doldurun!");
        }

        //şifre ve şifre onay eşleşmelidir
        else if(!sifre.equals(sifreOnay)){
            JOptionPane.showMessageDialog(null, "Şifreler eşleşmiyor!");
        }

        //eğer aynı kullanıcı adına sahip bir kullanıcı varsa o kullanıcı adının kullanılmasına izin vermez
        else if (kullaniciKayit.kullaniciVarMi(kayitliKullanicilar)){
            JOptionPane.showMessageDialog(null,kullaniciAdi+" kullanıcı adı zaten mevcut.");
        }

        //eğer kayıt işlemi başarılı olursa
        else{
            JOptionPane.showMessageDialog(null, "Kayıt başarılı!");
            kayitEkrani.setVisible(false);//giriş ekranına yönlendirir
            girisEkrani.setVisible(true);
            mailEkrani.setVisible(false);
            kayitIsim.setText("");//text alanlarını boşaltır
            kayitSoyisim.setText("");
            kayitKullaniciAdi.setText("");
            kayitParola.setText("");
            kayitParolayiOnayla.setText("");


            kullaniciKayit.dosyayaKaydetme();
        }

    }

    //kulllanıcı evet derse hesaptan çıkar, hayır derse hesapta kalmaya devam eder
    @FXML
    public void hesaptanCikisYap(){
        int result = JOptionPane.showConfirmDialog(null, "Çıkış yapmak istediğinizden emin misiniz?", "Çıkış Onayı", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            girisEkrani.setVisible(true);
            mailEkrani.setVisible(false);
            girisKullaniciadi.setText("");
            girisSifre.setText("");}
        else if (result == JOptionPane.NO_OPTION) {
            // "Hayır" seçildiyse işlemi iptal et
            JOptionPane.showMessageDialog(null, "Çıkış iptal edildi.");
        }
    }

    //kayıt ekranındaki geri butonunu tetikler
    public void geriGit(){
        girisEkrani.setVisible(true);
        kayitEkrani.setVisible(false);
        kayitIsim.setText("");
        kayitSoyisim.setText("");
        kayitKullaniciAdi.setText("");
        kayitParola.setText("");
        kayitParolayiOnayla.setText("");
        girisKullaniciadi.setText("");
        girisSifre.setText("");

    }

    //giriş ekranı açıldığında, hesabı olmayan kullanıcıları hayıt ekranına yönlendiren link
    @FXML
    public void hesapOlusturmaEkrani(){
        kayitEkrani.setVisible(true);
    }

    //kullanıcıdan e-posta bilgileri alınıyor, alınan bilgiler nesnede tutuluyor
    @FXML
    public void mailGonder() {
        String gidenGonderici = girisKullaniciadi.getText();//göndericiye ait bilgiler
        String gidenAlici = alici.getText();
        String gidenMail = mail.getText();
        String gidenKonu = konu.getText();

        GidenKutusu giden = new GidenKutusu(gidenGonderici, gidenAlici, gidenMail, gidenKonu);
        giden.dosyayaKaydetme();

        konu.setText("");//mail gönderildikten sonra alanları sıfırlar
        alici.setText("");
        mail.setText("");
        JOptionPane.showMessageDialog(null, "Mail başarıyla gönderildi");
    }

    //gelen kutusunu görüntüleyen metod
    @FXML
    public void gelenMailleriGoster(){
        //gelen kutusundaki mail bilgilerini alıyor
        String gelenAlici = this.alici.getText();
        String gelenGonderici = girisKullaniciadi.getText();
        String gelenMail = mail.getText();
        String gelenKonu = konu.getText();
        ArrayList<String> gelenMailler = new ArrayList<>();

        mailicerik.setText("");

        //gelen kutusu sınıfından nesne oluşturup o sınıf üzerinden mail bilgilerini çeliyor ve dosyaya kaydetme işlemi gerçekleştiriyor
        GelenKutusu gelen = new GelenKutusu(gelenGonderici, gelenAlici, gelenKonu, gelenMail);
        gelen.dosyaOkuma(gelenMailler);  //kullanıcıdan aldığı mailleri dosyadan okuyor

        //gelen kutusundan gelen verileri çeşitli arraylistler içerisinde gruplayarak kaydeder
        ArrayList<String> gonderici = new ArrayList<>();
        ArrayList<String> alici = new ArrayList<>();
        ArrayList<String> gelenmail = new ArrayList<>();
        ArrayList<Integer> sira = new ArrayList<>();
        ArrayList<String> konular = new ArrayList<>();

        //gelenMailler arraylisti içerisindeki elemanları gönderici, alıcı gibi farklı listele kaydederek gruplar
        for(int i = 0; i < (gelenMailler.size()); i+=4){
            gonderici.add(gelenMailler.get(i));
            alici.add(gelenMailler.get(i+1));
            gelenmail.add(gelenMailler.get(i+2));
            konular.add(gelenMailler.get(i+3));
        }


        //alıcı listesindeki mail hesaplarını gezerek mailin gönderildiği hesabın indexini tutar
        for(int i = 0; i < alici.size(); i++){
            String kullanici = girisKullaniciadi.getText();
            if(kullanici.equals(alici.get(i))){
                sira.add(i);
            }
        }

        for(int numara:sira){
            mailicerik.appendText("Konu: " + konular.get(numara) + " - Mail: " + gelenmail.get(numara) +" - Gönderici: " + gonderici.get(numara) + "\n");
        }
    }

    //gelen mailleri gizleme buton
    @FXML
    public void gelenMailGizle(){
        mailicerik.setText("");
    }

    //giden mailleri gizleme butonu
    public void gidenMailGizle(){
        gidenkutusu.setText("");
    }

    //giden maillerii gösterme butonu
    public void gidenMailleriGoster(){
        String alici2 = alici.getText();
        String gonderici2 = girisKullaniciadi.getText();
        String mail2 = mail.getText();
        String konu2 = konu.getText();

        GidenKutusu giden = new GidenKutusu(gonderici2, alici2, mail2, konu2);
        ArrayList<String> mailler1 = new ArrayList<>();

        giden.maillerigoster(mailler1);
        gidenkutusu.setText("");
        ArrayList<String> gonderen = new ArrayList<>();
        ArrayList<String> alan = new ArrayList<>();
        ArrayList<String> gelenler1 = new ArrayList<>();
        ArrayList<Integer> sira = new ArrayList<>();
        ArrayList<String> konular = new ArrayList<>();

        for(int i = 0; i < (mailler1.size()); i+=4){
            if (i + 3 < mailler1.size()) {
                gonderen.add(mailler1.get(i));
                alan.add(mailler1.get(i + 1));
                konular.add(mailler1.get(i + 2));
                gelenler1.add(mailler1.get(i + 3));}
        }

        for(int i = 0; i < alan.size();i++){
            String kullanici = girisKullaniciadi.getText();
            if(kullanici.equals(gonderen.get(i))){
                sira.add(i);
            }
        }
        for(int numara:sira){
            gidenkutusu.appendText("Konu: "+ konular.get(numara) + " - Mail: " + gelenler1.get(numara) +" - Alıcı: " + alan.get(numara) + "\n");
        }
    }
}

