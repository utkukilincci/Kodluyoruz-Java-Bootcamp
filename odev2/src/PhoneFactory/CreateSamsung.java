package PhoneFactory;

import java.util.ArrayList;
import java.util.List;

public class CreateSamsung extends Factory implements SamsungField{

    private List info = new ArrayList<>();
    private String[] information = {"Model No: ","IMEI: ","Version: ","RAM: ","Inc: "};

    public CreateSamsung(){
        System.out.println("Çağın gerisinde olmasına rağmen tuşlu Samsung ürettiniz...");
    }

    public void setInfo(List info) {
        this.info = info;
    }

    @Override
    public void shareFile() {
        System.out.println("Dosya Paylaşıldı");
    }

    @Override
    public void listenMusic() {
        System.out.println("Müzik Dinlendi");
    }

    @Override
    public void showInformation() {
        for (int i = 0; i< info.size(); i++){
            System.out.println(information[i]+info.get(i));
        }
    }

    @Override
    public void call() {
        System.out.println("Samsung telefonunuzdan arama yapıldı");
    }

    @Override
    public void sms() {
        System.out.println("Samsung telefonunuzdan sms atıldı");

    }
    public void delete(){
        info.clear();
    }
}
