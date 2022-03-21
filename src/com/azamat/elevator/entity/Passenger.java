package com.azamat.elevator.entity;

import java.util.concurrent.ThreadLocalRandom;

public class Passenger {

    private int currentFloor = 0;
    private int destinationFloor = 0;

    public Passenger(int currentFloor, int floor) {
        this.currentFloor = currentFloor;

        do {

            destinationFloor = ThreadLocalRandom.current().nextInt(0, floor);
        } while (destinationFloor == currentFloor);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }
}
