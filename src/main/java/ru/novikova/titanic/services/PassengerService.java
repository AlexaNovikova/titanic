package ru.novikova.titanic.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.novikova.titanic.dto.PassengerDto;
import ru.novikova.titanic.entity.Passenger;
import ru.novikova.titanic.repositories.PassengerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerService implements BasicService<Passenger, Long> {

    private final PassengerRepository passengerRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Optional<Passenger> findById(Long id) {
        return passengerRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        passengerRepository.deleteById(id);
    }

    @Override
    public void save(Passenger o) {
        passengerRepository.save(o);
    }

    @Override
    public long count() {
        return passengerRepository.count();
    }

    @Override
    public void saveAll(List<Passenger> passengers) {
        passengerRepository.saveAll(passengers);
    }

    public Page<PassengerDto> findAll(Specification<Passenger> specification, int page, int pageSize) {
        return passengerRepository.findAll(specification, PageRequest.of(page - 1, pageSize))
                .map(o->modelMapper.map(o, PassengerDto.class));
    }

    public Page<PassengerDto> findAll(int page, int pageSize) {
        return passengerRepository.findAll(PageRequest.of(page-1, pageSize))
                .map(p->modelMapper.map(p, PassengerDto.class));
    }
}
