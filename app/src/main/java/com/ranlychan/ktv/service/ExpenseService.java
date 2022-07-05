package com.ranlychan.ktv.service;

import java.util.HashMap;

public class ExpenseService {
    //private HashMap<String,Object> param;

    public static float getExpense(HashMap<String,Object> param){
        float expense = Float.MAX_VALUE;
        int roomNumber = (int)param.get("roomNumber");
        int adultGuestNumber = (int)param.get("adultGuestNumber");
        int childGuestNumber = (int)param.get("childGuestNumber");
        float roomTypePrice = (float)param.get("roomTypePrice");
        int orderDay = (int)param.get("orderDay");
        expense = roomNumber*roomTypePrice*orderDay;
        return expense;
    }

}
