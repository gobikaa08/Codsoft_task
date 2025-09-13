package mynewproject;

class SharedResource {
    synchronized void accessResource(String threadName) {
        System.out.println(threadName + " entered synchronized block.");
        try {
            System.out.println(threadName + " is going to sleep (TIMED_WAITING)...");
            Thread.sleep(2000); // Simulates TIMED_WAITING
            System.out.println(threadName + " woke up and is exiting synchronized block.");
        } catch (InterruptedException e) {
            System.out.println(threadName + " was interrupted.");
        }
    }
}

class WorkerThread extends Thread {
    private SharedResource resource;

    WorkerThread(SharedResource resource, String name) {
        super(name);
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.accessResource(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " has finished execution (TERMINATED).");
    }
}

public class ThreadStateDemo {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        WorkerThread t1 = new WorkerThread(resource, "Thread-1");
        WorkerThread t2 = new WorkerThread(resource, "Thread-2");

        System.out.println("Starting threads...");
        t1.start();
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {
            System.out.println("Thread-1 state: " + t1.getState());
            System.out.println("Thread-2 state: " + t2.getState());
            try {
                Thread.sleep(500); // Polling interval
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Both threads have completed.");
        System.out.println("name:Gobikaa.E");
        System.out.println("Reg No:2117240020106");
    }
}