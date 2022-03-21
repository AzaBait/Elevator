package com.azamat.elevator;

import com.azamat.elevator.service.LogicService;

public class Main {

    public static void main(String[] args) {
        LogicService logicService = new LogicService();
        logicService.initData();
        logicService.run();

    }
}

