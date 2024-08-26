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

    private final String URL_PATH = "https://web.stanford.edu/class/archive/cs/cs109/cs109.1166/stuff/titanic.csv";
    private final PassengerService passengerService;
    private final String COMMA_DELIMITER = ",";


    /**
     * Метод для первоначальной загрузки данных в базу
     * Данные заггружаются из файла с расширением csv, который скачивается из сети интернет
     * адрес записан в URL_PATH
     * Если данные в базе уже есть - ничего не делает
     */
    @Override
    @PostConstruct
    public void initialDataLoad()  {
        if (passengerService.count() == 0) {
            // открываем буферизированный входной поток, читаем его построчно
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new URL(URL_PATH).openConnection().getInputStream()))) {
                List<Passenger> passengers = new ArrayList<>();
                reader.readLine();
                String line;
                while ((line = reader.readLine()) != null) {
                    //из каждой строки формируем объект Passenger и добавляем его в список
                    passengers.add(new Passenger(line.split(COMMA_DELIMITER)));
                }
                // сохраняем всех пассажиров
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
