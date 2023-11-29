package com.example.autocinema.threads;

import com.example.autocinema.models.Message;
import com.example.autocinema.models.Vehicle;
import com.example.autocinema.publ.MessagePublisher;

import java.util.Stack;
import java.util.concurrent.Flow;

public class AutoCinemaThread extends Thread implements Flow.Subscriber<Message> {

    private final Stack<Message> messages = new Stack<>();
    private final MessagePublisher publisher;
    private Flow.Subscription subscription;

    public AutoCinemaThread(MessagePublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void run() {
        while (true) {
            if (!messages.isEmpty()) {
                Vehicle vehicle = messages.pop().vehicle();
                switch (vehicle.getVehicleState()) {
                    case ENTRANDO -> {

                    }
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Message item) {
        messages.push(item);
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throw new RuntimeException();
    }

    @Override
    public void onComplete() {
        System.out.println("completed");
    }
}
