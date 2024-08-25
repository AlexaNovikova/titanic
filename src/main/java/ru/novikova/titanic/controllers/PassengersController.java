package ru.novikova.titanic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.novikova.titanic.dto.PassengerDto;
import ru.novikova.titanic.repositories.specifications.PassengerSpecifications;
import ru.novikova.titanic.services.PassengerService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/passengers")
public class PassengersController {
    private final PassengerService passengerService;


    @GetMapping
    public Page<PassengerDto> getAllPassengers(@RequestParam MultiValueMap<String, String> params,
                                               @RequestParam(name = "p", defaultValue = "1") int page
    ) {
        if (page < 1) {
            page = 1;
        }
    return passengerService.findAll(PassengerSpecifications.build(params), page, 10);
//        System.out.println(passengerService.findAll(page, 10));
//        return passengerService.findAll(page, 10);

    }

}
