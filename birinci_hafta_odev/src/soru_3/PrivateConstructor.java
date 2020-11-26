package soru_3;

/**
 * Kurucu methodlarda private erişim belirteci ile method oluşturabilir miyiz?
 * OIuşturabiliyorsak buna neden ihtiyacımız var.
 */
public class PrivateConstructor {
    /**
     * Oluşturabiliriz. Bir sınıftan sadece bir obje üretilmesini istiyorsak bunu Singleton Design Pattern
     * ile sağlarız. Bu tasarımda tek nesne üretilmesini istediğimiz sınıfta private kurucu methodu tanımlarız.
     * Bu sayede dışarıdan new parametresi ile obje üretilemez. Üretilmesini istediğimiz tek objeyi o sınıfta statik bir değişken
     * ve method sayesinde içeriden üretir, dışarıya döndürürüz.
     * Bu tasarım ile sınıftan sadece bir obje üretilir ve sonraki tüm ihtiyaçlarda o objenin kopyası kullanılır.
      */
    public static PrivateConstructor tek_obje = new PrivateConstructor();

    private PrivateConstructor(){
        System.out.println("üretildi");
    }

    public static PrivateConstructor getTek_obje() {
        return tek_obje;
    }
}
