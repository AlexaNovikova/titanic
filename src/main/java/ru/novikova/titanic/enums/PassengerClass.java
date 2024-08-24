package ru.novikova.titanic.enums;

public enum PassengerClass {
    FIRST (1),
    SECOND (2),
    THIRD (3);
    private int classNumber;

    PassengerClass(int classNumber) {
        this.classNumber = classNumber;
    }
}
