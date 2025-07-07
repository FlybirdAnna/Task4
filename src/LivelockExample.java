class LiveLockExample {
    private static int counter = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (LiveLockExample.class) {
                    if (counter < 5) {
                        counter++;
                        System.out.println("Thread 1  counter to " + counter);
                    }
                }
                try { Thread.sleep(100); } catch (InterruptedException _) {}
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (LiveLockExample.class) {
                    if (counter > 0) {
                        counter--;
                        System.out.println("Thread 2 counter to " + counter);
                    }
                }
                try { Thread.sleep(100); } catch(InterruptedException _) {}
            }
        });

        thread1.start();
        thread2.start();
    }
}