package org.example;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CallableThread {

    private final ReentrantLock lock = new ReentrantLock();
    private volatile boolean ready = false;
    private final Condition condition = lock.newCondition();

    private Callable<String> createWorker(){
        return () -> {
            System.out.println("[Worker] " + Thread.currentThread().getName() + " запущен");

            lock.lock();
            try{
                while(!ready){
                    System.out.println("[Worker] жду сигнала...");
                    condition.await();
                    System.out.println("[Worker] проснулся, проверяю условие...");
                }
                System.out.println("[Worker] условие выполнено, работаю!");
                return "Worker завершил работу успешно";
            } finally {
                lock.unlock();
            }
        };
    }

    private void signalWorker() throws InterruptedException{
        System.out.println("[Main] жду 1 секунду перед сигналом...");
        Thread.sleep(1000);
        lock.lock();

        try {
            ready = true;
            condition.signal();
            System.out.println("[Main] сигнал отправлен");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        CallableThread app = new CallableThread();

        ExecutorService service = Executors.newSingleThreadExecutor();

        try{
            Future<String> future = service.submit(app.createWorker());
            app.signalWorker();

            String result = future.get(5, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("[Main] Worker не ответил за 5 секунд!");
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("[Main] ошибка в Worker: " + e.getCause().getMessage());
        } finally {
            service.shutdown();
            System.out.println("[Main] executor завершён");
        }

    }
}
