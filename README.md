Инструкция по запуску приложения:
1. В файле application.properties указать:
    URL к базе данных postgresql (по умолчанию jdbc:postgresql://localhost:5432/postgres)
    Логин и пароль к учетной записи администратора базы данных (по умолчанию postgres 1234)
2. Прогнать скрипты создания таблиц в базе из файла com/converter/data/scripts.sql
3. Запустить цель mvn:package
4. Запустить приложение \converter\target\converter-1.0.jar из командной строки

Приложение развернуто по адресу http://localhost:8080/
