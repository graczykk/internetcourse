package com.pkproject.internetcourse.application.datebase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Piotr Fudala
 */
public class DBCreateMysql {
    private PreparedStatement preparedStatement;
    private Connection connection;
    private String query;

    public DBCreateMysql(Connection connection) {
        this.connection = connection;
    }

    public void createDB() throws SQLException {
        connection = DBConnectMysql.getConnection();

        try {
            query = "CREATE DATABASE IF NOT EXISTS internetcourse DEFAULT CHARACTER SET utf8 " +
                    "DEFAULT COLLATE utf8_polish_ci";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

            query = "CREATE TABLE IF NOT EXISTS `internetcourse`.`Konto` \n" +
                    "(\t`idKonto` INT NOT NULL AUTO_INCREMENT ,\n" +
                    "\t`Login` VARCHAR(45) NOT NULL ,\n" +
                    "\t`Haslo` VARCHAR(45) NOT NULL , \n" +
                    "\t`RodzajKonta` ENUM('Admin','Instructor','Trainee'), \n" +
                    "\t`BlokadaKonta` BOOLEAN NOT NULL ,\n" +
                    " \tPRIMARY KEY (`idKonto`)\n" +
                    ");";


            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

            query = "CREATE TABLE IF NOT EXISTS `internetcourse`.`DaneOsobiste` \n" +
                    "(`idDaneOsobiste` INT NOT NULL AUTO_INCREMENT ,\n" +
                    "`idKonto` INT NOT NULL ,\n" +
                    "`NazwaUzytkownika` VARCHAR(45) NOT NULL,\n" +
                    "`Email` VARCHAR(45) NOT NULL , \n" +
                    "PRIMARY KEY (`idDaneOsobiste`),\n" +
                    "FOREIGN KEY (`idKonto`) REFERENCES Konto(`idKonto`));";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

            query = "CREATE TABLE IF NOT EXISTS `internetcourse`.`Wiadomosc` \n" +
                    "(\t`idWiadomosc` INT NOT NULL AUTO_INCREMENT ,\n" +
                    "\t`idKonto` INT NOT NULL ,\n" +
                    "\t`nadawca` VARCHAR(45) NOT NULL , \n" +
                    "\t`odbiorca` VARCHAR(45) NOT NULL , \n" +
                    "\t`tematWiadomosci` VARCHAR(45) NOT NULL , \n" +
                    "\t`trescWiadomosci` TEXT(45) NOT NULL , \n" +
                    "\tPRIMARY KEY (`idWiadomosc`),\n" +
                    "\tFOREIGN KEY (`idKonto`) REFERENCES Konto(`idKonto`)\n" +
                    ");";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

            query = "CREATE TABLE IF NOT EXISTS `internetcourse`.`Kurs` \n" +
                    "(\t`idKurs` INT NOT NULL AUTO_INCREMENT ,\n" +
                    "\t`nazwaKursu` VARCHAR(45) NOT NULL , \n" +
                    "\t`poziomKursu` ENUM('podstawowy','zaawansowany'), \n" +
                    "\t`tematKursu` VARCHAR(45) NOT NULL , \n" +
                    "\t`trescKursu` TEXT(999999) NOT NULL , \n" +
                    "\tPRIMARY KEY (`idKurs`)\n" +
                    ");";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

            query = "CREATE TABLE IF NOT EXISTS `internetcourse`.`Wynik` \n" +
                    "(\t`idWynik` INT NOT NULL AUTO_INCREMENT ,\n" +
                    "\t`nazwaTestu` VARCHAR(45) NOT NULL , \n" +
                    "\t`nazwaUzytkownika` VARCHAR(45) NOT NULL , \n" +
                    "\t`iloscPunktow` VARCHAR(45) NOT NULL ,\n" +
                    "\t`data` DATETIME NOT NULL,\n" +
                    "\tPRIMARY KEY (`idWynik`)\n" +
                    ");\n";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

            query = "CREATE TABLE IF NOT EXISTS `internetcourse`.`Test` \n" +
                    "(\t`idTest` INT NOT NULL AUTO_INCREMENT ,\n" +
                    "\t`idKurs` INT NOT NULL ,\n" +
                    "\t`nazwaTestu` VARCHAR(45) NOT NULL , \n" +
                    "\t`autorTestu` VARCHAR(45) NOT NULL ,\n" +
                    "\t`iloscPytan` VARCHAR(45) NOT NULL ,\n" +
                    "\t`poziomTestu` ENUM('podstawowy','zaawansowany'), \n" +
                    "\tPRIMARY KEY (`idTest`),\n" +
                    "\tFOREIGN KEY (`idKurs`) REFERENCES Kurs(`idKurs`)\n" +
                    ");\n";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

            query = "CREATE TABLE IF NOT EXISTS `internetcourse`.`RozwiazanyTest` \n" +
                    "(\t`idKonto` INT NOT NULL ,\n" +
                    "\t`idTest` INT NOT NULL ,\n" +
                    "\t`idWynik` INT NOT NULL ,\n" +
                    "FOREIGN KEY (`idKonto`) REFERENCES Konto(`idKonto`),\n" +
                    "FOREIGN KEY (`idTest`) REFERENCES Test(`idTest`),\n" +
                    "FOREIGN KEY (`idWynik`) REFERENCES Wynik(`idWynik`)\n" +
                    ");\n";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

            query = "CREATE TABLE IF NOT EXISTS `internetcourse`.`Log` \n" +
                    "(\t`idLog` INT NOT NULL AUTO_INCREMENT ,\n" +
                    "\t`idKonto` INT NOT NULL ,\n" +
                    "\t`DataLogowania` datetime NOT NULL DEFAULT NOW() ,\n" +
                    "\tPRIMARY KEY (`idLog`),\n" +
                    "FOREIGN KEY (`idKonto`) REFERENCES Konto(`idKonto`)\n" +
                    ");";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

            insertIntoDataBase();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void insertIntoDataBase() throws SQLException {
        query = "INSERT IGNORE INTO `internetcourse`.`konto` (`idKonto`, `Login`, `Haslo`, `RodzajKonta`, `BlokadaKonta`) " +
                "VALUES (1, 'admin', 'admin', 'Admin', '0')";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();

        query = "INSERT IGNORE INTO `internetcourse`.`log` (`idKonto`) VALUES ('1')";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();

        query = "INSERT IGNORE INTO `internetcourse`.`daneOsobiste` (`idDaneOsobiste`,`idKonto`, `NazwaUzytkownika`, `Email`) " +
                "VALUES (1, (select konto.idKonto from `internetcourse`.`konto` where Login LIKE 'admin'), 'Piotr', 'piotr@wp.pl')";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();


        query = "INSERT IGNORE  INTO `internetcourse`.`konto` (`idKonto`, `Login`, `Haslo`, `RodzajKonta`, `BlokadaKonta`) " +
                "VALUES (2, 'damian', 'damian', 'Instructor', '0')";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();

        query = "INSERT IGNORE INTO `internetcourse`.`log` (`idKonto`) VALUES ('2')";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();

        query = "INSERT IGNORE  INTO `internetcourse`.`daneOsobiste` (`idDaneOsobiste`,`idKonto`, `NazwaUzytkownika`, `Email`) " +
                "VALUES (2, (select konto.idKonto from `internetcourse`.`konto` where Login LIKE 'damian'), 'Damian', 'Damian')";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();

       // insertTuition();

    }

    public void insertTuition() throws SQLException {

        query ="INSERT IGNORE  INTO `internetcourse`.`Kurs` (`idKurs`, `nazwaKursu`, `poziomKursu`, `tematKursu`, `trescKursu`)\n" +
                "VALUES (null, 'Sieci Komputerowe', 'podstawowy', '', ?),\n" +
                "(null, 'Sieci Komputerowe', 'zaawansowany', 'Sieci', ?)\n";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, Text.networkBasic);
        preparedStatement.setString(2, Text.networkAdvanced);
        preparedStatement.executeUpdate();

        query ="INSERT IGNORE  INTO `internetcourse`.`Test` (`idTest`, `idKurs`, `nazwaTestu`, `autorTestu`, `iloscPytan`, `poziomTestu`)\n" +
                "VALUES (null, '1', 'Sieci Komputerowe', 'DamianSmigielski', '5', 'podstawowy'),\n" +
                "(null, '2', 'Sieci Komputerowe', 'DamianSmigielski', '5', 'zaawansowany')\n";

                preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();

        query = "INSERT IGNORE  INTO `internetcourse`.`Pytanie` (`idPytanie`, `idTest`, `tresc`, `odpowiedza`, `odpowiedzb`, `odpowiedzc`, `odpowiedzd`, `prawidlowaOdpowiedz` , `liczbaPunktow`)\n" +
                "VALUES (null, 1, 'Ethernet wykorzystuje topologie:', 'gwiazdy', 'magistrali', 'pierścienia', 'śiatki', 'magistrali', '1'),\n" +
                " (null, 1, 'Jednym z zadań warstwy IP jest:', 'znalezienie najlepszej drogi łączącej dwa hosty', 'znalezienie najlepszej drogi łączącej użytkowników', 'tworzenie specjalizowanych obiektów na podstawie bardziej ogólnych', 'żadna z powyższych', 'znalezienie najlepszej drogi łączącej dwa hosty', '1'),\n" +
                " (null, 1, 'W stosie protokołów TCP/IP adresowanie jest zdefiniowane w:', 'protokole UDP', 'protokole IP', 'protokole FTP', 'wszystkich', 'protokole IP', '1'),\n" +
                " (null, 1, 'Stansardowy adres IP składa się z:', '8-bitów', '16-bitów', '32-bitów', '64-bitów', '32-bitów', '1'),\n" +
                " (null, 1, 'W jakiej notacji został zapisany adres IP: 109314561?', 'szesnastkowej', 'dziesiętnej', 'binarnej', 'żadna z powyższych', 'dziesiętnej', '1');\n";


        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        query = "INSERT IGNORE  INTO `internetcourse`.`Pytanie` (`idPytanie`, `idTest`, `tresc`, `odpowiedza`, `odpowiedzb`, `odpowiedzc`, `odpowiedzd`, `prawidlowaOdpowiedz` , `liczbaPunktow`)\n" +
                " (null, 2, 'Który typ pakietu służy do żądania routera o udzielenie dodatkowych informacji o dowolnym wpisie z DBD?', 'Hello', 'DBD', 'LSR', 'LSU', 'LSR', '1'),\n" +
                " (null, 2, 'Co może powodować zalewanie LSA w wielodostępowych sieciach OSPF?', 'do przeciążenia sieci', 'do informacji o najkrótszej drodze ustalonej przez algorytm SPF', 'nawiązaniu sąsiedztwa router, który został wybrany jako master', 'żadne z powyższych', 'do przeciążenia sieci', '1'),\n" +
                " (null, 2, 'Pakiet LSA  musi zostać wysłany tylko:', 'Podczas każdego uruchomienia routera albo po włączeniu procesu protokołu routingu na tym routerze', 'Kiedy nie zmieni się topologia, co obejmuje także wyłączenie lub włączenie łącza, albo przyległość z sąsiadem zostanie ustanowiona albo zerwana', 'Kiedy zmieni się topologia, co obejmuje także wyłączenie lub włączenie łącza, albo przyległość z sąsiadem zostanie tylko zerwana', 'żadne z powyższych', 'żadne z powyższych', '1'),\n" +
                " (null, 2, 'Co to drzewo OSPF?', 'Algorytm który sumuje koszt ostatniej drogi od źródła do celu', 'Algorytm który sumuje koszt pierwszej drogi od źródła do celu', 'Algorytm który sumuje koszt żadnej drogi od źródła do celu', 'Algorytm który sumuje koszt każdej drogi od źródła do celu', 'Algorytm który sumuje koszt każdej drogi od źródła do celu', '1')\n";


        query ="INSERT IGNORE INTO `internetcourse`.`Wynik` (`idWynik`, `nazwaTestu`, `nazwaUzytkownika`, `iloscPunktow`, `data`)\n" +
                "VALUES (null, 'Sieci Komputerowe', 'damian', '5', '2017-01-15 15:33:33');";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();


        query = "INSERT IGNORE INTO `internetcourse`.`RozwiazanyTest` (`idKonto`, `idTest`, `idWynik`)\n" +
                "VALUES (2, '1', '1');";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();

    }
}
