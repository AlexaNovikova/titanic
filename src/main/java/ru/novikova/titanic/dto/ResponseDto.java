package ru.novikova.titanic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    // количество выживших пассажиров всего
    private Integer sumSurvivalPassengers;
    // количество пассажиров, у которых были родственникики на борту
    private Integer sumPassengersWithRelativesOnBoard;
    // общая стоимость билетов
    private Double sumFair;
    // Страница с объектами типа PassengerDto
    private Page<PassengerDto> passengerDtoPage;
}
