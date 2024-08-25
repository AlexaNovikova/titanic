package ru.novikova.titanic.services;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface BasicService<E, K> {
    List<E> findAll();

    Optional<E> findById(K id);

    void deleteById(K id);

    void save(E o);

    long count();

    void saveAll(List<E> lists);
}

