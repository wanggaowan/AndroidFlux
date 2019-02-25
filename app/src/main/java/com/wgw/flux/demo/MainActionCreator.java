package com.wgw.flux.demo;

import com.wgw.flux.Dispatcher;

import java.util.Random;

/**
 * @author Created by 汪高皖 on 2019/2/25 0025 09:28
 */
public class MainActionCreator {
    public void changeText(){
        Random random = new Random();
        String text = "random value is " + random.nextInt(100000);
        Dispatcher.postAction(ActionType.CHANGE_TEXT,text);
    }
}
