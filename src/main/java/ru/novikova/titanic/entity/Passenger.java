package ru.novikova.titanic.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.novikova.titanic.enums.PassengerClass;

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
    private boolean survived;

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

    public Passenger(boolean survived, PassengerClass pclass,
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
}
