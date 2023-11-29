package com.example.autocinema.models;

import com.example.autocinema.models.enums.VehicleState;
import com.example.autocinema.publ.MessagePublisher;
import javafx.scene.shape.Circle;

public class Vehicle {

    private final MessagePublisher vehiclePublisher;
    private final Circle vehicleImage;
    private int selectedPlaceId;
    private MessagePublisher publisher;
    private VehicleState vehicleState;

    public int getReceptionIndex() {
        return receptionIndex;
    }

    public void setReceptionIndex(int receptionIndex) {
        this.receptionIndex = receptionIndex;
    }

    private int receptionIndex;

    public Vehicle(MessagePublisher vehiclePublisher, MessagePublisher publisher, Circle vehicleImage) {
        this.vehiclePublisher = vehiclePublisher;
        this.publisher = publisher;
        this.vehicleImage = vehicleImage;
    }

    public void reception() {
        vehicleState = VehicleState.PIDIENDO;
        publisher.publishMessage(message());
    }

    public void enter() {
        vehicleState = VehicleState.ENTRANDO;
        publisher.publishMessage(message());
    }

    public void park() {
        vehicleState = VehicleState.ESTACIONANDO;
        publisher.publishMessage(message());
    }

    public void begin() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        vehicleState = VehicleState.INICIANDO_SALIDA;
        publisher.publishMessage(message());
    }

    public void exit() {
        vehicleState = VehicleState.SALIENDO;
        publisher.publishMessage(message());
    }

    public void gone() {
        vehicleState = VehicleState.SALIDO;
        publisher.publishMessage(message());
    }

    public void finish() {
        vehicleState = VehicleState.FINALIZADO;
        publisher.publishMessage(message());
    }

    public Message message() {
        return new Message(this, publisher);
    }

    public MessagePublisher getVehiclePublisher() {
        return vehiclePublisher;
    }

    public Circle getVehicleImage() {
        return vehicleImage;
    }

    public int getSelectedPlaceId() {
        return selectedPlaceId;
    }

    public void setSelectedPlaceId(int selectedPlaceId) {
        this.selectedPlaceId = selectedPlaceId;
    }

    public MessagePublisher getPublisher() {
        return publisher;
    }

    public void setPublisher(MessagePublisher publisher) {
        this.publisher = publisher;
    }

    public VehicleState getVehicleState() {
        return vehicleState;
    }

    public void setVehicleState(VehicleState vehicleState) {
        this.vehicleState = vehicleState;
    }
}
