package com.example.w2022comp1011w2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
//implementing changelistener interface
public class SpinnerChangeListener implements ChangeListener {
    @Override
    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        System.out.printf("old value: %s   new Value: %s%d", oldValue, newValue);
    }
}
