package ru.novikova.titanic.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.novikova.titanic.entity.Passenger;

import java.util.Arrays;
import java.util.List;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>, JpaSpecificationExecutor<Passenger> {
    Page<Passenger> findAllBy(Pageable pageable);
    List<Passenger> findByNameLike(String name);

    List<Passenger> findByName(String name);

}
