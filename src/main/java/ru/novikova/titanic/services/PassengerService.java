package ru.novikova.titanic.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;
import ru.novikova.titanic.dto.PassengerDto;
import ru.novikova.titanic.dto.ResponseDto;
import ru.novikova.titanic.entity.Passenger;
import ru.novikova.titanic.repositories.PassengerRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassengerService implements BasicService<Passenger, Long> {

    private final PassengerRepository passengerRepository;
    private final ModelMapper modelMapper;

    /**
     * Метод для получения списка всех пассажиров
     * @return Список всех пассажиров в виде List<Passenger>
     */
    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    /**
     * Метод для поиска  по id
     * @param id - id пассажира
     * @return объект Optional, который содержит пассажира, если такой присутствует в базе данных
     */
    @Override
    public Optional<Passenger> findById(Long id) {
        return passengerRepository.findById(id);
    }

    /**
     * Метод для удаления записи (объекта Passenger) по id из базы данных
     * @param id - id пассажира, которого нужно удалить
     */
    @Override
    public void deleteById(Long id) {
        passengerRepository.deleteById(id);
    }

    /**
     * Метод для сохранения объекта Passenger в базу данных
     * @param passenger - Пассажир
     */
    @Override
    public void save(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    /**
     * Метод для полсчета количества записей в таблицу Passenger
     * @return - количество записей в таблицу Passenger
     */
    @Override
    public long count() {
        return passengerRepository.count();
    }

    /**
     * Метод для сохранения списка объектов типа Passenger в базу данных
     * @param passengers - список объектов типа Passenger
     */
    @Override
    public void saveAll(List<Passenger> passengers) {
        passengerRepository.saveAll(passengers);
    }

    /**
     * Метод для поиска объектов типа Passenger, у которых имя совпадает с входным параметром
     * @param name - имя, по которому нужно найти пассажиров
     * @return Список объектов типа Passenger, у которых имя совпадает с параметром name
     */
    @Cacheable("passengers")
    public List<PassengerDto> findByName(String name) {
      return passengerRepository.findByName(name).stream()
              .map(p->(modelMapper.map(p, PassengerDto.class)))
              .collect(Collectors.toList());
    }

    /**
     * Метод для формирования объекта ResponseDto, на основании спецификации, номера страницы,
     * количества объектов импа PassengerDto на странице, вида сортировки
     * @param specification - спецификация (фильтр) для поиска
     * @param page - номер страницы
     * @param pageSize - размер страницы (количество записей на странице)
     * @param sort - объект типа Sort
     * @return  объект типа ResponseDto
     */
    @Cacheable("responseDto")
    public ResponseDto findAll(Specification<Passenger> specification, int page, int pageSize, Sort sort) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setPassengerDtoPage(passengerRepository.findAll(specification, PageRequest.of(page - 1, pageSize, sort))
                .map(o->modelMapper.map(o, PassengerDto.class)));
        List<Passenger> passengers = passengerRepository.findAll(specification);
        responseDto.setSumFair(passengers.stream().mapToDouble(Passenger::getFare).sum());
        responseDto.setSumSurvivalPassengers((int) passengers.stream().filter(p->p.getSurvived().equals(true)).count());
        responseDto.setSumPassengersWithRelativesOnBoard((int)passengers.stream()
                .filter(p-> (p.getParentsAboard()>0 || p.getSiblingsAboard()>0))
                .count());
        return responseDto;
    }
}
