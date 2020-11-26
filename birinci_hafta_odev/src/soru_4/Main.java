package soru_4;
import java.util.Scanner;

/**
 * Kullanıcıdan alınan ondalıklı bir sayının ondalık kısmına göre
 * aşağı ve yukarı doğru yuvarlama işlemi yapacak bir program yazınız.
 * aşağı veya yukarı yuvalarlanacağını kullanıcıdan bir bilgi olarak alınız.
 */
public class Main {
    static Float sayi;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Yuvarlamak istediğiniz sayıyı virgül kullanarak girin (Ex: 10,5) : ");
            if (scanner.hasNextFloat()) {
                sayi = scanner.nextFloat();
                break;
            } else {
                System.out.println("Yanlış biçimde giriş yaptınız tekrar deneyin ...");
                scanner.nextLine();
            }
        }
        while(true){
            System.out.print("Aşağı yuavarlamak için '0' , yukarı yuvarlamak için '1' tuşlayınız : ");
            int secim = scanner.nextInt();
            if(secim == 0){
                System.out.println("Yeni sayınız : "+Math.floor(sayi));
                break;
            }
            else if(secim == 1){
                System.out.println("Yeni sayınız : "+Math.ceil(sayi));
                break;
            }
            else{
                System.out.println("Yanlış tuşlama...");
            }
        }
    }
}
