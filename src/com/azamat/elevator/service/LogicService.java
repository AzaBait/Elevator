package com.azamat.elevator.service;

import com.azamat.elevator.entity.Building;
import com.azamat.elevator.entity.Elevator;
import com.azamat.elevator.entity.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LogicService {

    private Building building = null;
    private Elevator elevator = null;

    public void initData() {
        building = new Building();
        int floors = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        System.out.println("NUMBERS OF FLOORS: " + floors);

        for (int i = 0; i < floors; i++) {
            building.add(generatePassengers(i, floors));
        }
        elevator = building.getElevator();
    }

    public void run() {

        int currentFloor = 0;
        List<Passenger> passengers = null;
        int passengersCameIn = 0;
        int passengersWentOut = 0;

        int upGoingPassengers = 0;
        int downGoingPassengers = 0;

        do {
            currentFloor = elevator.getCurrentFloor();
            passengers = building.get(currentFloor);

            upGoingPassengers = countUpGoingPassengers(passengers, currentFloor);
            downGoingPassengers = countDownGoingPassengers(passengers, currentFloor);

            if (currentFloor == elevator.getDestinationFloor()) {

                passengersWentOut = letPassengersOut();
                elevator.defineDirection(passengers);
                passengersCameIn = elevator.letPassengersIn(passengers);
                ConsoleService.reachedDestinationFloor();

            } else {
                passengersWentOut = letPassengersOut();
                passengersCameIn = elevator.letPassengersIn(passengers);
            }
            ConsoleService.floorDescription(currentFloor, elevator.getDestinationFloor(), elevator.isMovingUp(),
                    passengersCameIn, passengersWentOut, elevator.getPassengers(), upGoingPassengers, downGoingPassengers);
            if (elevator.isMovingUp()) {
                elevator.up();
            } else {
                elevator.down();
            }
        } while (!elevator.isEmpty());
        ConsoleService.endMessage();
    }

    private int letPassengersOut() {
        int passengersLetOut = 0;
        if (!elevator.isEmpty()) {
            passengersLetOut = elevator.letPassengersOut();
            generateNewPassengers(passengersLetOut);
        }
        return passengersLetOut;
    }

    private void generateNewPassengers(int passengersLetOut) {
        for (int i = 0; i < passengersLetOut; i++) {
            int randomFloor = 0;
            do {
                randomFloor = ThreadLocalRandom.current().nextInt(0, building.size());
            } while (randomFloor == elevator.getCurrentFloor());
            Passenger passenger = new Passenger(randomFloor, building.size());
            building.get(randomFloor).add(passenger);
        }
    }

    private List<Passenger> generatePassengers(int currentFloor, int floors) {
        int numberOfGeneratedPassengers = 0;
        if (currentFloor == 1) {
            numberOfGeneratedPassengers = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        } else {
            numberOfGeneratedPassengers = ThreadLocalRandom.current().nextInt(0, 5 + 1);
        }
        List<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < numberOfGeneratedPassengers; i++) {
            passengers.add(new Passenger(currentFloor, floors));
        }
        return passengers;
    }

    private int countUpGoingPassengers(List<Passenger> passengers, int currentFloor) {
        int passengersGoingUp = 0;
        for (Passenger p : passengers) {
            if (p.getDestinationFloor() > currentFloor) {
                passengersGoingUp++;
            }
        }
        return passengersGoingUp;
    }
    private int countDownGoingPassengers(List<Passenger> passengers, int currentFloor){
        int passengersGoingDown = 0;
        for (Passenger p : passengers){
            if (p.getDestinationFloor() < currentFloor){
                passengersGoingDown++;
            }
        }
        return passengersGoingDown;
    }
}
