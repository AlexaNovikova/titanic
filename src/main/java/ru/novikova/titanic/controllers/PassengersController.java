package ru.novikova.titanic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.novikova.titanic.dto.PassengerDto;
import ru.novikova.titanic.dto.ResponseDto;
import ru.novikova.titanic.repositories.specifications.PassengerSpecifications;
import ru.novikova.titanic.services.PassengerService;


import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/passengers")
public class PassengersController {
    private final PassengerService passengerService;


    /**
     * Метод обрабатывает Get Запрос по адресу .../api/v1/passengers,
     * @param params - - map, где ключ-значение вида:
     *                 "survived": "true"/"false",
     *                  "is_male": "true"/"false",
     *                  "is_adult": "true"/"false",
     *                  "no_relatives": "true"/"false".
     * @param page - номер страницы (по умолчанию = 1)
     * @param pageSize - размер страницы (по умолчанию = 50 )
     * @param sort - поле класса Passenger, по которому будет осуществляться сортировка,
     *             по умолчанию - по Id
     * @param sortType - тип сортировки ("ASC", "DESC"), по умолчанию - ASC
     * @return объект типа ResponseDto, где
     * PageResponse.sumSurvivalPassengers - кол-во выживших
     * пассажиров из списка с учетом фильтрации,
     * PageResponse.sumPassengersWithRelativesOnBoard- количество пассажиров,
     * у которых были родственникики на борту из списка с учетом фильтрации,
     * PageResponse.sumFair - общая стоимость билетов у пассажиров из списка с учетом фильтрации,
     * PageResponse.passengerDtoPage - объект типа Page со списком объектов PassengerDto
     */
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


    /**
     * Метод обрабатывает GET запрос по адресу .../api/v1/passengers/{name},
     * @param name - имя, по которому нужно найти пассажиров
     * @return список найденных объектов типа PassengerDto, лиюо пустой список
     */
    @GetMapping("/{name}")
    public List<PassengerDto> findByName(@PathVariable String name) {
        return passengerService.findByName(name);
    }

}
