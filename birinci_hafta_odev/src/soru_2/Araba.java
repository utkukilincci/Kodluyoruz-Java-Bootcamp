package soru_2;

public class Araba {
    /**
     * Bir araba nesnesi oluşturup bu arabaya ait özellikleri
     * kurucu methodlar sayesinde tanımlayabildiğimiz bir sınıf yazınız.
     */
    public String marka;
    public String model;
    public String max_hiz;

    public Araba(String marka, String model, String max_hiz){
        this.marka = marka;
        this.model = model;
        this.max_hiz = max_hiz;
    }
    public void arabaOzellikleriniYazdır(){
        System.out.println("Araba : "+marka+" "+model+" Maximum hız : "+max_hiz);
    }
}
