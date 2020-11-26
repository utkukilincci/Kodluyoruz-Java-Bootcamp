package PhoneFactory;
/**
 * Hocam biraz uzun ve karışık yapmış olabilirim :P ama  idare eder şekilde çalışıyo.
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List information = new ArrayList();


    public static void main(String[] args) {

        Factory phone;
        int select;
        while (true){
            while(true){
                System.out.print("Apple üretmek için 1, Samsung üretmek için 2, çıkmak için 0 tuşlayınız: ");
                try {
                    select = scanner.nextInt();
                    scanner.nextLine();
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Yanlış tuşlama, tekrar deneyin!");
                    scanner.nextLine();
                }
            }
            if(select == 0){
                break;
            }

            /**
             * Oluşan Apple telefonunun bilgilerini gösterme ve kullanma...
             */
            if(select == 1) {
                determinePhoneInformation();
                phone = new CreateApple();
                ((CreateApple)phone).setInfo(information);
                int action = 0;
                while (true) {
                    while(true) {
                        System.out.println("Arama yapmak için 1, sms atmak için 2, videolu görüşme için 3, ekran paylaşımı için 4," +
                                "bilgileri göstermek için5, çıkmak için 0 tuşlayınız.");
                        try {
                            action = scanner.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Yanlış tuşlama yaptınız");
                            scanner.nextLine();
                        }
                    }
                    if(action == 0){
                        ((CreateApple)phone).delete();
                        break;
                    }
                    switch (action) {
                        case (1):
                            phone.call();
                            break;
                        case (2):
                            phone.sms();
                            break;
                        case (3):
                            ((CreateApple) phone).videoCall();
                            break;
                        case (4):
                            ((CreateApple) phone).screenSharing();
                            break;
                        case (5):
                            phone.showInformation();
                            break;
                        default:
                            System.out.println("Yanlış seçim");
                    }
                }
            }

            /**
             * Oluşan Samsung telefonunun bilgilerini gösterme ve kullanma...
             */
            else if(select == 2) {
                determinePhoneInformation();
                phone = new CreateSamsung();
                ((CreateSamsung)phone).setInfo(information);
                int action = 0;
                while (true) {
                    while(true) {
                        System.out.println("Arama yapmak için 1, sms atmak için 2, dosya paylaşmak için 3, müzik dinlemek için 4," +
                                "bilgileri göstermek için 5, çıkmak için 0 tuşlayınız.");
                        try {
                            action = scanner.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Yanlış tuşlama yaptınız");
                            scanner.nextLine();
                        }
                    }
                    if(action == 0){
                        ((CreateSamsung)phone).delete();
                        break;
                    }
                    switch (action) {
                        case (1):
                            phone.call();
                            break;
                        case (2):
                            phone.sms();
                            break;
                        case (3):
                            ((CreateSamsung) phone).shareFile();
                            break;
                        case (4):
                            ((CreateSamsung) phone).listenMusic();
                            break;
                        case (5):
                            phone.showInformation();
                            break;
                        default:
                            System.out.println("Yanlış seçim");
                    }
                }
            }

            }
        }

    /**
     * Ortak telefon bilgilerinin toplandığı kısım.
     */
    public static List determinePhoneInformation(){
            while(true) {
                System.out.println("Harf ve sayıdan oluşan model no girin: ");
                try {
                    String model = scanner.nextLine();
                    information.add(model);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Yanlış giriş yaptınız tekrar deneyin");
                    scanner.nextLine();
                }
            }
            while(true) {
                System.out.println("Harf ve sayıdan oluşan IMEI no girin: ");
                try {
                    String imei = scanner.nextLine();
                    information.add(imei);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Yanlış giriş yaptınız tekrar deneyin");
                    scanner.nextLine();
                }
            }
            while(true) {
                System.out.println("Harf ve sayıdan oluşan version no girin: ");
                try {
                    String version = scanner.nextLine();
                    information.add(version);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Yanlış giriş yaptınız tekrar deneyin");
                    scanner.nextLine();
                }
            }
            while(true) {
                System.out.println("RAM kapasitesini sayısal olarak mb cinsinden girin: ");
                try {
                    int ram = scanner.nextInt();
                    information.add(ram);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Yanlış giriş yaptınız tekrar deneyin");
                    scanner.nextLine();
                }
            }
            while(true) {
                System.out.println("ekranın inc değerini girin (ör:15,2): ");
                try {
                    float screen = scanner.nextFloat();
                    information.add(screen);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Yanlış giriş yaptınız tekrar deneyin");
                    scanner.nextLine();
                }
            }
            return information;
        }
    }

