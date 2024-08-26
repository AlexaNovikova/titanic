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
    private Integer sumSurvivalPassengers;
    private Integer sumPassengersWithRelativesOnBoard;
    private Double sumFair;
    private Page<PassengerDto> passengerDtoPage;
}
