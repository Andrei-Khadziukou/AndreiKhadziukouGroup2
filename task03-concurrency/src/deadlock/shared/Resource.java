package deadlock.shared;

/**
 * Created by User-PC on 26.10.2014.
 */
public class Resource {
    private String name;

    public Resource(String name) {
        this.name = name;
    }

    public synchronized void useItWith(Resource with) throws InterruptedException {
        System.out.println(Thread.currentThread() + " using resource " + name);
        Thread.sleep(100);
        with.lookAt();
    }

    public synchronized void lookAt() {
        System.out.println("Taking a look into " + name);
    }
}
