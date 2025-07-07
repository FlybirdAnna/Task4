public class DeadlockExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: catch lock1");
                try { Thread.sleep(100); } catch (InterruptedException _) {}
                System.out.println("Thread 1: try to catch lock2");
                synchronized (lock2) {
                    System.out.println("Thread 1: catch lock2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2: catch lock2");
                try { Thread.sleep(100); } catch (InterruptedException _) {}
                System.out.println("Thread 2: try to catch lock1");
                synchronized (lock1) {
                    System.out.println("Thread 2: catch lock1");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
