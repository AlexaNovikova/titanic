package ru.novikova.titanic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.novikova.titanic.enums.PassengerClass;

import java.util.Arrays;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "survived")
    private Boolean survived;

    @Enumerated(EnumType.STRING)
    @Column(name = "pclass")
    private PassengerClass pclass;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "age")
    private double age;

    @Column(name = "siblings_aboard")
    private int siblingsAboard;

    @Column(name = "parents_aboard")
    private int parentsAboard;

    @Column(name = "fare")
    private double fare;

    public Passenger(Boolean survived, PassengerClass pclass,
                     String name, String sex, double age,
                     int siblingsAboard, int parentsAboard, double fare) {
        this.survived = survived;
        this.pclass = pclass;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.siblingsAboard = siblingsAboard;
        this.parentsAboard = parentsAboard;
        this.fare = fare;
    }

    /**
     * Конструктор
     * @param passengerValues - массив строк,  в котором
     *                       содержаться значения для полей класса
     *                        Passenger в след. порядке : "survived", "pclass",
     *                        "name", "sex", "age",
     *                        "siblingsAboard", "parentsAboard", "fare".
     */
    public Passenger (String [] passengerValues) {
        this(
                Integer.parseInt(passengerValues[0]) > 0,
                PassengerClass.getPassengerClassByNumber(Integer.parseInt(passengerValues[1])),
                passengerValues[2],
                passengerValues[3],
                Double.parseDouble(passengerValues[4]),
                Integer.parseInt(passengerValues[5]),
                Integer.parseInt(passengerValues[6]),
                Double.parseDouble(passengerValues[7])
        );
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", survived=" + survived +
                ", pclass=" + pclass +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", siblingsAboard=" + siblingsAboard +
                ", parentsAboard=" + parentsAboard +
                ", fare=" + fare +
                '}';
    }
}
