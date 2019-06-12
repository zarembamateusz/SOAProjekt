package jms.service;

import java.util.HashSet;
import java.util.Set;

public abstract class Observable {

    Set<Observer> observers = new HashSet<>();
    public void subscirbe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }
}
