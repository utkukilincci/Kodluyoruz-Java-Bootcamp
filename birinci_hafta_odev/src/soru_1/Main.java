package soru_1;

public class Main {
    /**
     * 0 ile 2000 arasında 3,5,7 ve 53'e bölünebilen sayıların
     *  aritmetik ortalamasını bulan bir uygulama yazınız.
     */
    public static void main(String[] args) {
        int sayilarinToplami = 0;
        int sayiAdedi = 0;
        float ortalama;
        for (int i = 0; i<=2000; i++){
            if(i%3==0 && i%5==0 && i%7==0 && i%53==0){
                System.out.println("3,5,7 ve 53'e bölünebilen sayi : "+i);
                sayilarinToplami += i;
                sayiAdedi++;
            }
        }
        if(sayilarinToplami == 0){
            System.out.println("0-2000 arasında 3,5,7 ve 53'e tam bölünebilen 0 dan başka sayı yok.");
        }
        else{
            ortalama = (sayilarinToplami / sayiAdedi);
            System.out.println("0-2000 arasında 3,5,7 ve 53'e bölünen sayıların ortalaması = "+ortalama);
        }
    }
}
