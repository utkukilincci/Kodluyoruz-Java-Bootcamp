
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class City implements Clock, Comparable<City> {

    private String name;
    private String code;
    private String gmt;
    private static List<String> cityCodeList = new ArrayList<>();

    public static List<String> getCityCodeList() {
        return cityCodeList;
    }

    public static void addCityCodeList(String city) {
        City.cityCodeList.add(city);
    }


    public City() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGmt() {
        return gmt;
    }

    public void setGmt(String gmt) {
        this.gmt = gmt;
    }

    @Override
    public void showTime() {


        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(gmt));
        System.out.println(Thread.currentThread().getName() +" : "+getName() + " : " + sdf.format(time));

        /**
         * hocam yukardi kodu beğenmezseniz aşağıdakini deneyebilirsiniz xd
         */

        /*while (true) {
            Date time = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone(gmt));
            String clock = sdf.format(time);
            System.out.print(getName() + " : " + clock + " " + "\r");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }


    @Override
    public int compareTo(City o) {
        return this.getName().compareTo(o.getName());
    }
}
