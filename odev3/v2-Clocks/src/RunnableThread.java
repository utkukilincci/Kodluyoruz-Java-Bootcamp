
public class RunnableThread implements Runnable {
    private City city;

    public RunnableThread(City city) {
        this.city = city;
    }

    @Override
    public void run() {
        synchronized (this) {
            city.showTime();
        }

    }

}
