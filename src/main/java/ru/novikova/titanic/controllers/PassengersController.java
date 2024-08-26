package ru.novikova.titanic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.novikova.titanic.dto.PassengerDto;
import ru.novikova.titanic.dto.ResponseDto;
import ru.novikova.titanic.repositories.specifications.PassengerSpecifications;
import ru.novikova.titanic.services.PassengerService;

import java.util.Dictionary;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/passengers")
public class PassengersController {
    private final PassengerService passengerService;

    @GetMapping
    public ResponseDto getAllPassengers(@RequestParam MultiValueMap<String, String> params,
                                              @RequestParam(name = "p", defaultValue = "1") int page,
                                              @RequestParam(name = "size", defaultValue = "50") int pageSize,
                                              @RequestParam(name = "sort", defaultValue = "id") String sort,
                                              @RequestParam(name = "stype", defaultValue = "ASC") String sortType
    ) {
        if (page < 1) {
            page = 1;
        }
        return passengerService.findAll(PassengerSpecifications.build(params)
                ,page, pageSize, Sort.by(Sort.Direction.fromString(sortType), sort));
    }

    @GetMapping("/{name}")
    public List<PassengerDto> findByName(@PathVariable String name) {
        return passengerService.findByName(name);
    }

}
