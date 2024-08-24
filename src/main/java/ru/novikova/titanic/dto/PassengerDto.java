package ru.novikova.titanic.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.novikova.titanic.enums.PassengerClass;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    private Long id;
    private boolean survived;
    private PassengerClass pclass;
    private String name;
    private String sex;
    private double age;
    private int siblingsAboard;
    private int parentsAboard;
    private double fare;
}
