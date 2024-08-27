# Тестовое задание на должность Java-разработчик (Итерация)

## Используемые технологии
- Java 17
- Stream API 
- Maven 
- Spring Boot  3
- Spring JPA 
- PostgreSQL 12
- Liquibase 
- REStful 
- Angular.js
- Bootstrap 

## Задача
### Список пассажиров Титаника
Описание
При первом запуске скачать данные с сайта https://web.stanford.edu/class/archive/cs/cs109/cs109.1166/problem12.html, ссылка на файл с данными (https://web.stanford.edu/class/archive/cs/cs109/cs109.1166/stuff/titanic.csv). Данные записать в базу данных, при записи часть данных модифицировать: данные о классе пассажира (Pclass) заменить на Enum, идентификатор выжившего (Survived) заменить на тип данных Boolean.
Пользователь попадает на экран со списком пассажиров Титаника. На странице должно отображаться 50 пассажиров, должна быть возможность изменять количество отображаемых пассажиров на странице. Также должна быть пагинация.
Должна быть возможность сортировать (по возрастанию, по убыванию) список по полям: Имя, Возраст, Оплата.
На странице должно быть поле поиска пассажира по имени.
В нижней части списка должна быть статистика по следующим полям:
- Общая сумма оплаты проезда
- Количество людей имеющих родственников на борту.
- Количество выживших на борту.
Данная статистика должна меняться в зависимости от фильтрованных данных, но по всем пассажирам, а не только тех что на экране.
На экране должны быть кнопки которые фильтруют данные:
- показать всех выживших пассажиров.
- показать всех совершеннолетих пассажирова (страше 16 лет)
- показать всех пассажиров мужского пола
- показать всех пассажиров кто не имеет родственников.
Должна быть возможность комбинировать фильтры.
Должно быть реализовано кэширование данных, выбор технологии кэширования на своё усмотрение.

### Требования к результату
- Код приложения необходимо снабдить комментариями.
- Приложение должно собираться при помощи maven без установки или настройки каких-либо дополнительных компонент;
- Должен быть заполнен текстовый файл readme.md с инструкцией по сборке, настройке, конфигурированию и развертыванию приложения (если необходимо);
- Результаты должны быть загружены на GitHub/GitLab (желательно вести последовательную разработку: один коммит - одна фича).

## Для сборки/запуска приложения:
- Открыть проект в IntellIJ IDEA 
- Сервер разворачивается на localhost, порт 8189. Для изменения порта - измените порт в файле application.yaml
- Для подключения к базе данных Postgres измените логин и пароль (на рабочие) в файле application.yaml
- Для сбоки проекта выполнить команду maven -package
- Для старта приложения запустить сформированный jar файл (папка target)
