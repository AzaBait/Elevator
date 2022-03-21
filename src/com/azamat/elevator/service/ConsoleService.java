package com.azamat.elevator.service;

import com.azamat.elevator.entity.Passenger;

import java.util.List;

public class ConsoleService {

    public static void endMessage() {
        System.out.println("--------ELEVATOR IS EMPTY---------\n" +
                "----NO PASSENGERS ON THE FLOOR----\n" +
                "--------------THE END-------------");
    }

    public static void reachedDestinationFloor() {

        System.out.println("---DESTINATION FLOOR IS REACHED!---");
    }

    public static void floorDescription(int currentFloor, int destinationFloor, boolean movingUp, int passengersCameIn,
                                        int passengersWentOut, List<Passenger> passengers, int upGoingPassengers,
                                        int downGoingPassengers) {
        currentFloor++;
        destinationFloor++;
        String arrow = (movingUp) ? "\u21E7" : "\u21E9";
        String fs = (currentFloor >= 10) ? " " : " ";
        int passengersCurrentFloor = 0;
        int passengersDestinationFloor = 0;
        String spaceBeforePassCurrFloor = "";
        String spaceAfterPassDestFloor = "";
        if (movingUp) {
            printArrows(arrow);
        }
        System.out.println(
                        "        |                 |  \n" +
                        "        |                 |   " + "\u21E7 " + upGoingPassengers + " \n" +
  " CF: " + currentFloor + fs + "|                 |   " + "\u21E9 " + downGoingPassengers
        );

        for (Passenger p : passengers) {
            passengersCurrentFloor = (p.getCurrentFloor() + 1);
            passengersDestinationFloor = (p.getDestinationFloor() + 1);

            spaceBeforePassCurrFloor = (passengersCurrentFloor >= 10) ? "     " : "      ";
            spaceAfterPassDestFloor = (passengersDestinationFloor >= 10) ? "     " : "      ";

            System.out.println(
                    "        |" + spaceBeforePassCurrFloor + passengersCurrentFloor + " : " +
                            passengersDestinationFloor +
                            spaceAfterPassDestFloor + "|       ");
        }
        fs = (destinationFloor >= 10) ? " " : " ";

        System.out.println(
                " DF: " + destinationFloor + fs + "|                 |   " + "\u21E6 " + passengersCameIn + " \n" +
                        "        |                 |   " + "\u21E8 " + passengersWentOut + " \n" +
                        "        |                 | \n"
        );

        if (!movingUp) {
            printArrows(arrow);
        }

        System.out.println("\n");
        System.out.println("-------------------------");
        System.out.println("\n\n");
    }

    private static void printArrows(String arrow) {
        System.out.print("        ");

        for (int i = 0; i < 19; i++) {
            System.out.print(arrow);
        }

        System.out.println("\n");
    }
}
