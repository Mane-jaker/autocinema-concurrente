package com.example.autocinema.controllers;

import com.example.autocinema.models.Message;
import com.example.autocinema.models.Vehicle;
import com.example.autocinema.monitors.VehicleMonitor;
import com.example.autocinema.publ.MessagePublisher;
import com.example.autocinema.threads.VehicleThread;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

public class SimulatorController implements Initializable, Flow.Subscriber<Message> {

    private final Stack<Vehicle> vehicles = new Stack<>();
    private final Stack<Message> messages = new Stack<>();
    private final VehicleMonitor monitor = new VehicleMonitor();
    private final MessagePublisher mainPublisher = new MessagePublisher();
    private Flow.Subscription subscription;

    @FXML
    private Pane cinema;

    @FXML
    private Circle entranceZone;

    @FXML private Circle reception1;
    @FXML private Circle reception2;
    @FXML private Circle entrance;

    @FXML private Circle place1;
    @FXML private Circle place2;
    @FXML private Circle place3;
    @FXML private Circle place4;
    @FXML private Circle place5;
    @FXML private Circle place6;
    @FXML private Circle place7;
    @FXML private Circle place8;
    @FXML private Circle place9;
    @FXML private Circle place10;
    @FXML private Circle place11;
    @FXML private Circle place12;
    @FXML private Circle place13;
    @FXML private Circle place14;
    @FXML private Circle place15;
    @FXML private Circle place16;
    @FXML private Circle place17;
    @FXML private Circle place18;
    @FXML private Circle place19;
    @FXML private Circle place20;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainPublisher.subscribe(this);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(this::start);

        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        executorService2.submit(this::handle);
    }

    private void handle() {
        while (true) {
            if(!messages.isEmpty()) {
                handleMessage(messages.pop());
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
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

    private void handleMessage(Message message) {
        Vehicle vehicle = message.vehicle();
        switch (vehicle.getVehicleState()) {
            case PIDIENDO, INICIANDO_SALIDA -> {
                Vehicle vehicle1 = message.vehicle();
                vehicle1.getVehiclePublisher().publishMessage(new Message(vehicle1, null));
            }
            case ENTRANDO, SALIDO -> {
                Vehicle vehicle1 = message.vehicle();
                Circle circle = vehicle1.getVehicleImage();

                Platform.runLater(() -> {
                    if (vehicle1.getReceptionIndex() == 0) {
                        circle.layoutXProperty().bind(reception1.layoutXProperty());
                        circle.layoutYProperty().bind(reception1.layoutYProperty());
                    } else {
                        circle.layoutXProperty().bind(reception2.layoutXProperty());
                        circle.layoutYProperty().bind(reception2.layoutYProperty());
                    }
                });


                vehicle1.getVehiclePublisher().publishMessage(new Message(vehicle1, null));
            }
            case ESTACIONANDO -> {
                Vehicle vehicle1 = message.vehicle();
                Circle circle = vehicle1.getVehicleImage();

                vehicles.push(vehicle1);

                Platform.runLater(() -> {
                    if (vehicle1.getSelectedPlaceId() == 1) {
                        circle.layoutXProperty().bind(place1.layoutXProperty());
                        circle.layoutYProperty().bind(place1.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 2) {
                        circle.layoutXProperty().bind(place2.layoutXProperty());
                        circle.layoutYProperty().bind(place2.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 3) {
                        circle.layoutXProperty().bind(place3.layoutXProperty());
                        circle.layoutYProperty().bind(place3.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 4) {
                        circle.layoutXProperty().bind(place4.layoutXProperty());
                        circle.layoutYProperty().bind(place4.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 5) {
                        circle.layoutXProperty().bind(place5.layoutXProperty());
                        circle.layoutYProperty().bind(place5.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 6) {
                        circle.layoutXProperty().bind(place6.layoutXProperty());
                        circle.layoutYProperty().bind(place6.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 7) {
                        circle.layoutXProperty().bind(place7.layoutXProperty());
                        circle.layoutYProperty().bind(place7.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 8) {
                        circle.layoutXProperty().bind(place8.layoutXProperty());
                        circle.layoutYProperty().bind(place8.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 9) {
                        circle.layoutXProperty().bind(place9.layoutXProperty());
                        circle.layoutYProperty().bind(place9.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 10) {
                        circle.layoutXProperty().bind(place10.layoutXProperty());
                        circle.layoutYProperty().bind(place10.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 11) {
                        circle.layoutXProperty().bind(place11.layoutXProperty());
                        circle.layoutYProperty().bind(place11.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 12) {
                        circle.layoutXProperty().bind(place12.layoutXProperty());
                        circle.layoutYProperty().bind(place12.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 13) {
                        circle.layoutXProperty().bind(place13.layoutXProperty());
                        circle.layoutYProperty().bind(place13.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 14) {
                        circle.layoutXProperty().bind(place14.layoutXProperty());
                        circle.layoutYProperty().bind(place14.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 15) {
                        circle.layoutXProperty().bind(place15.layoutXProperty());
                        circle.layoutYProperty().bind(place15.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 16) {
                        circle.layoutXProperty().bind(place16.layoutXProperty());
                        circle.layoutYProperty().bind(place16.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 17) {
                        circle.layoutXProperty().bind(place17.layoutXProperty());
                        circle.layoutYProperty().bind(place17.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 18) {
                        circle.layoutXProperty().bind(place18.layoutXProperty());
                        circle.layoutYProperty().bind(place18.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 19) {
                        circle.layoutXProperty().bind(place19.layoutXProperty());
                        circle.layoutYProperty().bind(place19.layoutYProperty());
                    } else if (vehicle1.getSelectedPlaceId() == 20) {
                        circle.layoutXProperty().bind(place20.layoutXProperty());
                        circle.layoutYProperty().bind(place20.layoutYProperty());
                    };
                });

                if (vehicle1.getSelectedPlaceId() == 20) {
                    for (int i = 0; i < 20; i++) {
                        Vehicle vehicle2 = vehicles.pop();
                        vehicle2.getVehiclePublisher().publishMessage(new Message(vehicle2, null));
                    }
                }
            }
            case SALIENDO -> {
                Vehicle vehicle1 = message.vehicle();
                Circle circle = vehicle1.getVehicleImage();

                Platform.runLater(() -> {
                    circle.layoutXProperty().bind(entrance.layoutXProperty());
                    circle.layoutYProperty().bind(entrance.layoutYProperty());
                });

                vehicle1.getVehiclePublisher().publishMessage(new Message(vehicle1, null));
            }
            case FINALIZADO -> {
                Vehicle vehicle1 = message.vehicle();
                Circle circle = vehicle1.getVehicleImage();

                Platform.runLater(() -> {
                    cinema.getChildren().remove(circle);
                });

            }
        }
    }

    private void start() {
        while (true) {

            Circle circle = new Circle(10);
            circle.layoutXProperty().bind(entranceZone.layoutXProperty());
            circle.layoutYProperty().bind(entranceZone.layoutYProperty());

            Platform.runLater(() -> cinema.getChildren().add(circle));

            VehicleThread vehicleThread = new VehicleThread(mainPublisher, circle, monitor);
            vehicleThread.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}