package com.example.autocinema.monitors;

import com.example.autocinema.models.Vehicle;

import java.util.concurrent.atomic.AtomicInteger;

public class VehicleMonitor {

    private final boolean[] isSomeOneOnTheReception = {false, false};
    private boolean isSomeOneSelectingPlaceOnReception = false;
    private boolean isSomeOneOnEntrance = false;
    private boolean isFull = false;
    private final int capacity = 20;
    private final AtomicInteger currentCapacity = new AtomicInteger(0);
    private boolean isUsingVariable = false;

    public synchronized void enterReception(Vehicle vehicle) throws InterruptedException {
        while (isSomeOneSelectingPlaceOnReception || isFull) {
            wait();
        }
        isSomeOneSelectingPlaceOnReception = true;

        int index = isSomeOneOnTheReception[0] ? 1 : 0;
        isSomeOneOnTheReception[index] = true;


        currentCapacity.set(currentCapacity.get() + 1);
        System.out.println("currentCapacity.get() = entrada" + currentCapacity.get());
        if (currentCapacity.get() == capacity) {
            isFull = true;
        }

        vehicle.setSelectedPlaceId(currentCapacity.get());
        vehicle.setReceptionIndex(index);
        switch (vehicle.getVehicleState()) {
            case SALIENDO -> vehicle.gone();
            case PIDIENDO -> vehicle.enter();
        }
        release(vehicle.getReceptionIndex());
        release();
    }

    public synchronized void enter(Vehicle vehicle) throws InterruptedException {
        isSomeOneOnEntrance = true;
        vehicle.park();
        isSomeOneOnEntrance = false;
    }

    public synchronized void exit(Vehicle vehicle) throws InterruptedException {
        while (isSomeOneOnEntrance) {
            wait();
        }
        isSomeOneOnEntrance = true;



        System.out.println("currentCapacity.get() = salida" + currentCapacity.get());
        currentCapacity.set(currentCapacity.get() - 1);

        if (currentCapacity.get() < 1) {
            isFull = false;
        }


        vehicle.exit();
        isSomeOneOnEntrance = false;
        notifyAll();
    }

    public synchronized void release(int index) {
        isSomeOneOnTheReception[index] = false;
        notifyAll();
    }

    public synchronized void release() {
        isSomeOneSelectingPlaceOnReception = false;
        notifyAll();
    }
}
