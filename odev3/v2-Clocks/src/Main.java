import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Map<String, City> unSortedMap = new HashMap<>();

        City delphi = new NewDelhi("New Delphi");
        unSortedMap.put(delphi.getCode(), delphi);
        City moscow = new Moscow("Moscow");
        unSortedMap.put(moscow.getCode(), moscow);
        City newyork = new NewYork("New York");
        unSortedMap.put(newyork.getCode(), newyork);
        City london = new London("London");
        unSortedMap.put(london.getCode(), london);
        City berlin = new Berlin("Berlin");
        unSortedMap.put(berlin.getCode(), berlin);

        sortCityMapAndPrint(unSortedMap);

        while (true) {
            List<String> inputs = getInputs(" ");
            if (inputs.size() >= 3 && inputs.size() <= 5) {
                try {
                    for (String check : inputs) {
                        if (City.getCityCodeList().contains(check)) {
                            continue;
                        } else {
                            throw new CityException();
                        }
                    }
                    for (String list : inputs) {
                        switch (list) {
                            case "MOW":
                                executorService.execute(new RunnableThread(moscow));
                                break;
                            case "LND":
                                executorService.execute(new RunnableThread(london));
                                break;
                            case "BER":
                                executorService.execute(new RunnableThread(berlin));
                                break;
                            case "DEL":
                                executorService.execute(new RunnableThread(delphi));
                                break;
                            case "NYC":
                                executorService.execute(new RunnableThread(newyork));
                                break;
                        }
                    }
                    break;
                } catch (CityException e) {
                    e.CityException();
                }

            } else {
                System.out.println("Eksik veya Fazla Giriş yaptınız. Tekrar deneyiniz.");
            }
        }
    }

    private static void sortCityMapAndPrint(Map<String, City> map) {

        List<City> cityName = new ArrayList<>(map.values());
        Collections.sort(cityName);
        for (City sehir : cityName) {
            System.out.println(sehir.getCode() + " = " + sehir.getName());
        }
    }

    public static List<String> getInputs(String inputseparator) {
        System.out.println("Listede saatini görmek istediğiniz\n" +
                "şehirlerden en az 3 en fazla 5 tanesinin kodlarını aralarında boşluk\n" +
                "bırakarak yazınız.(Örn: LND BER MOW)");

        String line = scanner.nextLine().toUpperCase();
        return Arrays.asList(line.split(inputseparator));
    }
}
