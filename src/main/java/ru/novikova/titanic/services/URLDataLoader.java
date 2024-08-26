package ru.novikova.titanic.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.novikova.titanic.entity.Passenger;
import ru.novikova.titanic.enums.PassengerClass;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class URLDataLoader implements DataLoader {

    private final String URL = "https://web.stanford.edu/class/archive/cs/cs109/cs109.1166/stuff/titanic.csv";
    private final PassengerService passengerService;
    private final String COMMA_DELIMITER = ",";

    @Override
    @PostConstruct
    public void initialDataLoad()  {
        if (passengerService.count() == 0) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new URL(URL).openConnection().getInputStream()))) {
                List<Passenger> passengers = new ArrayList<>();
                reader.readLine();
                String line;
                while ((line = reader.readLine()) != null) {
                    passengers.add(new Passenger(line.split(COMMA_DELIMITER)));
                }
                System.out.println(passengers);
                passengerService.saveAll(passengers);
            }
        catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
