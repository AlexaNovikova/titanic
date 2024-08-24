package ru.novikova.titanic.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.novikova.titanic.entity.Passenger;
import ru.novikova.titanic.enums.PassengerClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataLoader {

    private final String URL = "https://web.stanford.edu/class/archive/cs/cs109/cs109.1166/stuff/titanic.csv";
    private final PassengerService passengerService;


    @PostConstruct
    public void loadTitanicData() throws RuntimeException {
        if (passengerService.count() == 0) {
            List<Passenger> passengers = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new URL(URL).openStream()))) {
                reader.readLine();
                String line;
                while ((line = reader.readLine()) != null) {
                    Passenger passenger = createPassenger(line);
                    passengers.add(passenger);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
//                log.error("URL is incorrect or resource is unavailable", e);
//                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
//                log.error("Unavailable to read file", e);
//                throw new RuntimeException(e);
            }
            passengerService.saveAll(passengers);
        }
    }

    private Passenger createPassenger(String line) {
        String[] values = line.split(",");
        return new Passenger(
                Integer.parseInt(values[0]) > 0,
                getPassengerClass(Integer.parseInt(values[1])),
                values[2],
                values[3],
                Double.parseDouble(values[4]),
                Integer.parseInt(values[5]),
                Integer.parseInt(values[6]),
                Double.parseDouble(values[7])
        );
    }

    private PassengerClass getPassengerClass(int classNumber) {
        return switch (classNumber) {
            case 1 -> PassengerClass.FIRST;
            case 2 -> PassengerClass.SECOND;
            case 3 -> PassengerClass.THIRD;
            default -> throw new IllegalArgumentException("Invalid passenger class number");
        };
    }

}
