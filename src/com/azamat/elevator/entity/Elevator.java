package com.azamat.elevator.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Elevator {

    private List<Passenger> passengers = new ArrayList<>();
    private int floors = 0;
    private int currentFloor = 0;
    private int destinationFloor = -1;
    private boolean movingUp = true;

    public Elevator(int floors) {
        this.floors = floors;
    }

    public void up() {
        if (currentFloor < (floors - 1))
            currentFloor++;
    }

    public void down() {
        if (currentFloor > 0) {
            currentFloor--;
        }
    }

    public int letPassengersIn(List<Passenger> floorPassengers) {
        int passengersLetIn = 0;
        if (!floorPassengers.isEmpty()) {
            for (int i = 0; i < floorPassengers.size(); i++) {
                if (hasFreeSpace()) {
                    if (isMovingUp()) {
                        if (floorPassengers.get(i).getDestinationFloor() > currentFloor) {
                            passengers.add(floorPassengers.get(i));
                            floorPassengers.remove(i);
                            passengersLetIn++;
                            i--;
                        } else {
                            if (floorPassengers.get(i).getDestinationFloor() < currentFloor) {
                                passengers.add(floorPassengers.get(i));
                                floorPassengers.remove(i);
                                passengersLetIn++;
                                i--;
                            }
                        }
                    } else {
                        break;
                    }

                }
            }
        }
            if (passengersLetIn > 0) {
                defineDestinationFloor();
            }


        return passengersLetIn;
    }

    public int letPassengersOut() {

        int numberOfPassengersLetOut = 0;

        for (int i = 0; i < passengers.size(); i++) {
            if (passengers.get(i).getDestinationFloor() == currentFloor) {
                passengers.remove(i);
                numberOfPassengersLetOut++;
                i--;
            }
        }
        return numberOfPassengersLetOut;

    }

    public void defineDirection(List<Passenger> floorPassengers) {

        int passengersGoingUp = 0;

        int passengersGoingDown = 0;

        if (!floorPassengers.isEmpty()) {
            for (Passenger p : floorPassengers) {
                if (p.getDestinationFloor() > currentFloor) {
                    passengersGoingUp++;
                } else {
                    passengersGoingDown++;
                }
            }
            movingUp = passengersGoingUp >= passengersGoingDown;
        }
    }

    public void defineDestinationFloor() {

        for (Passenger p : passengers) {
            if (movingUp) {
                if (p.getDestinationFloor() > destinationFloor) {
                    destinationFloor = p.getDestinationFloor();
                }
            } else {
                if (p.getDestinationFloor() < destinationFloor) {
                    destinationFloor = p.getDestinationFloor();
                }
            }
        }
    }

    public boolean isEmpty() {
        return passengers.isEmpty();
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    private boolean hasFreeSpace() {
        return passengers.size() < 5;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public List<Passenger> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }

}

