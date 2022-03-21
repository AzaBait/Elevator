package com.azamat.elevator.entity;

import java.util.ArrayList;
import java.util.List;

public class Building extends ArrayList<List<Passenger>> {

    public Elevator getElevator() {
        return new Elevator(this.size());
    }
}
