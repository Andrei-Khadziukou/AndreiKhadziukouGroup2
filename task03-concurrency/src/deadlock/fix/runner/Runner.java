package deadlock.fix.runner;

import deadlock.fix.Service.UsefulService;
import deadlock.shared.Resource;

/**
 * Created by User-PC on 26.10.2014.
 */
public class Runner {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Program started");
        final UsefulService service = new UsefulService();
        final Resource resourceA = new Resource("res A");
        final Resource resourceB = new Resource("res B");
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.useResource(resourceA, resourceB);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.useResource(resourceB, resourceA);
            }
        }).start();
    }
}
