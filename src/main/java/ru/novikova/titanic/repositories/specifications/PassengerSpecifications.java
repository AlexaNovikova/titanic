package ru.novikova.titanic.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.novikova.titanic.entity.Passenger;


public class PassengerSpecifications {

    /**
     * Метод для формирования фильтра (спецификации) Пассажиров (Passenger.class)
     * по параметру - survived (выжил ли)
     * @param survived - логическое значение, определяет выжил ли пассажир
     * @return Specification<Passenger> c фильтром по критерию выжил ли
     */
    private static Specification<Passenger> survived(boolean survived) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("survived"), survived);
    }

    /**
     *  Метод для формирования фильтра (спецификации) Пассажиров (Passenger.class)
     *  по критерию совершеннолетия, если параметр - isAdult = false, то
     *  выбраны будут все пассажиры
     * @param isAdult - логическое значения, определяет является ли пассажир совершеннолетним
     * @return Specification<Passenger> c фильтром по критерию совершеннолетия
     */
    private static Specification<Passenger> isAdultOtherwiseAll(boolean isAdult) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("age"), isAdult ? 16 : 0);
    }

    /**
     * Метод для формирования фильтра (спецификации) Пассажиров (Passenger.class)
     * по параметру - gender (пол)
     * @param gender - пол пассажира ("male", "female")
     * @return Specification<Passenger> c фильтром по критерию пол
     */
    private static Specification<Passenger> genderEquals(String gender) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("sex"), gender);
    }

    /**
     *  Метод для формирования фильтра (спецификации) Пассажиров (Passenger.class)
     *  по критерию пассажир не имеет сублингов на борту
     * @return pecification<Passenger> c фильтром по критерию -
     * пассажир не имеет сублингов на борту
     */
    private static Specification<Passenger> hasNoSiblingsOnBoard() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("siblingsAboard"), 0);
    }

    /**
     *  Метод для формирования фильтра (спецификации) Пассажиров (Passenger.class)
     *  по критерию пассажир не имеет родителей на борту
     * @return pecification<Passenger> c фильтром по критерию -
     * пассажир не имеет родителей на борту
     */
    private static Specification<Passenger> hasNoParentsOnBoard() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("parentsAboard"), 0);
    }


    /**
     * Метод для построения фильтра (спецификации) по  по возрасту, по полу, выжил ли,
     * имеет ли родственников на борту
     * @param params - map, где ключ-значение вида:
     *              "survived": "true"/"false",
     *               "is_male": "true"/"false",
     *               "is_adult": "true"/"false",
     *               "no_relatives": "true"/"false".
     * @return Specification<Passenger>( фильтр по возрасту, по полу, выжил ли,
     * имеет ли родственников на борту)
     */
    public static Specification<Passenger> build(MultiValueMap<String, String> params) {
        Specification<Passenger> spec = Specification.where(null);
        if (params.containsKey("survived") && !params.getFirst("survived").isBlank()) {
            if (Boolean.parseBoolean(params.getFirst("survived"))) {
                spec = spec.and(PassengerSpecifications.survived
                        (Boolean.parseBoolean(params.getFirst("survived"))));
            }
        }

        if (params.containsKey("is_male") && !params.getFirst("is_male").isBlank()) {
            if (Boolean.parseBoolean(params.getFirst("is_male"))) {
                spec = spec.and(PassengerSpecifications.genderEquals("male"));
            }
        }
        if (params.containsKey("is_adult") && !params.getFirst("is_adult").isBlank()) {
            if (Boolean.parseBoolean(params.getFirst("is_adult"))) {
                spec = spec.and(PassengerSpecifications.isAdultOtherwiseAll(Boolean.parseBoolean(params.getFirst("is_adult"))));
            }
        }
        if (params.containsKey("no_relatives") && !params.getFirst("no_relatives").isBlank()) {
            if (Boolean.parseBoolean(params.getFirst("no_relatives"))) {
                spec = spec.and(PassengerSpecifications
                                .hasNoParentsOnBoard())
                        .and(PassengerSpecifications
                                .hasNoSiblingsOnBoard());
            }
        }
        return spec;
    }
}
