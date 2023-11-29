package com.example.autocinema.threads;

import com.example.autocinema.models.Message;
import com.example.autocinema.models.Vehicle;
import com.example.autocinema.monitors.VehicleMonitor;
import com.example.autocinema.publ.MessagePublisher;
import javafx.scene.shape.Circle;

import java.util.Stack;
import java.util.concurrent.Flow;

public class VehicleThread extends Thread implements Flow.Subscriber<Message> {

    private final Vehicle vehicle;
    private final VehicleMonitor monitor;
    private final Stack<Message> messages = new Stack<>();
    private MessagePublisher publisherThis = new MessagePublisher();
    private Flow.Subscription subscription;

    public VehicleThread(MessagePublisher publisher, Circle vehicleImage, VehicleMonitor monitor) {
        publisherThis.subscribe(this);
        this.vehicle = new Vehicle(publisherThis, publisher, vehicleImage);
        this.monitor = monitor;
    }

    @Override
    public void run() {
        vehicle.reception();
        while (true) {
            if (!messages.isEmpty()) {
                Message message = messages.pop();
                switch (message.vehicle().getVehicleState()) {
                    case PIDIENDO, SALIENDO -> {
                        try {
                            monitor.enterReception(vehicle);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case ENTRANDO -> {
                        try {
                            monitor.enter(vehicle);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case INICIANDO_SALIDA -> {
                        try {
                            monitor.exit(vehicle);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case ESTACIONANDO -> vehicle.begin();
                    case SALIDO -> vehicle.finish();
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
