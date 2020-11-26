package PhoneFactory;

import java.util.ArrayList;
import java.util.List;

public class CreateApple extends Factory implements AppleField{

    private List info = new ArrayList<>();
    private String[] information = {"Model No: ","IMEI: ","Version: ","RAM: ","Inc: "};

    public CreateApple(){
        System.out.println("Dokunmatik ekrana sahip Apple telefonunuz oluşturuldu!!!");
    }

    public void setInfo(List info) {
        this.info = info;
    }

    public void videoCall() {
        System.out.println("Görüntülü arama yapıldı");
    }

    public void screenSharing() {
        System.out.println("Ekran paylaşıldı");
    }

    @Override
    public void showInformation() {
        for (int i = 0; i< info.size(); i++){
            System.out.println(information[i]+info.get(i));
        }
    }

    @Override
    public void call() {
        System.out.println("Apple telefonunuzdan arama yapıldı");
    }

    @Override
    public void sms() {
        System.out.println("Apple telefonunuzdan sms atıldı");

    }
    public void delete(){
        info.clear();
    }
}
