package ru.novikova.titanic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.novikova.titanic.entity.Passenger;
import ru.novikova.titanic.repositories.PassengerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerService implements BasicService<Passenger, Long> {

    private final PassengerRepository passengerRepository;

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
}
