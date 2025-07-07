public class AlternatingPrintExample {
    private static final Object lock = new Object();
    private static boolean turn = true;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (!turn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.print("1 ");
                    turn = false;
                    lock.notify();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if (turn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.print("2 ");
                    turn = true;
                    lock.notify();
                }
            }
        });

        t1.start();
        t2.start();
    }
}