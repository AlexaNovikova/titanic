package ru.novikova.titanic.enums;

public enum PassengerClass {
    FIRST (1),
    SECOND (2),
    THIRD (3);
    private int classNumber;

    PassengerClass(int classNumber) {
        this.classNumber = classNumber;
    }

    /**
     *
     * @param classNumber - номер класса 1, 2 или 3
     * @return значение PassengerClass, соответсвующее номеру
     */
    public static PassengerClass getPassengerClassByNumber(int classNumber) {
        return switch (classNumber) {
            case 1 -> PassengerClass.FIRST;
            case 2 -> PassengerClass.SECOND;
            case 3 -> PassengerClass.THIRD;
            default -> throw new IllegalArgumentException("Invalid passenger class number");
        };
    }

    public int getClassNumber() {
        return classNumber;
    }
}
