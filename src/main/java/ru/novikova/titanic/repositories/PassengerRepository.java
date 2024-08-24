package ru.novikova.titanic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.novikova.titanic.entity.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
