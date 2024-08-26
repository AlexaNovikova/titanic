package ru.novikova.titanic.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.novikova.titanic.entity.Passenger;


public class PassengerSpecifications {
    private static Specification<Passenger> survived(boolean survived) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("survived"), survived);
    }

    private static Specification<Passenger> isAdult(boolean isAdult) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("age"), isAdult ? 16 : 0);
    }

    private static Specification<Passenger> genderEquals(String male) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("sex"), male);
    }

    private static Specification<Passenger> hasNoSiblingsOnBoard() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("siblingsAboard"), 0);
    }

    private static Specification<Passenger> hasNoParentsOnBoard() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("parentsAboard"), 0);
    }


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
                spec = spec.and(PassengerSpecifications.isAdult(Boolean.parseBoolean(params.getFirst("is_adult"))));
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
