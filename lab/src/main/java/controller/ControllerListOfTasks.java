package controller;

import business_logic_layer.ListOfTaskLogic;
import classes.ListOfTasks;

import java.util.ArrayList;

public class ControllerListOfTasks {

    ListOfTaskLogic listOfTaskLogic = new ListOfTaskLogic();


    public ArrayList<ListOfTasks> getListsOfTasks(int id) {
        return listOfTaskLogic.getListsOfTasks(id);
    }


}
