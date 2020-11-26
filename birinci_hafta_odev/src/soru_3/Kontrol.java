package soru_3;

public class Kontrol {
    public static void main(String[] args) {
        // aşağıdaki kod ile obje üretemiyoruz.
        //PrivateConstructor deneme = new PrivateConstructor();

        /**
         * aşağıdaki şekilde obje ürettik. yapıcı method bir defa çalıştı
         * bundan sonra yeni obje üretmeye çalışsakda ilk objenin kopyasını kullanıcaz, yapıcı method birdaha çalışmayacak.
         */
       PrivateConstructor deneme2 = PrivateConstructor.getTek_obje();


    }
}
