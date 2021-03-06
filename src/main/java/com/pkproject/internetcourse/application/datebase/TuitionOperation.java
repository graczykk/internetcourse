package com.pkproject.internetcourse.application.datebase;

import com.pkproject.internetcourse.application.account.Account;
import com.pkproject.internetcourse.application.tuition.Course;
import com.pkproject.internetcourse.application.tuition.Question;
import com.pkproject.internetcourse.application.tuition.Test;

import javax.transaction.*;
import java.sql.*;
import java.util.ArrayList;

public class TuitionOperation {
    private Connection mysqlConnection;
    private Connection postgressConnection;

    private PreparedStatement preparedStatement;
    private String query;

    public TuitionOperation() {
        try {
            mysqlConnection = DBConnectMysql.getConnection();
            postgressConnection = DBConnectPostgres.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void changeData(int idCourse, String newSubject, String newText) throws SQLException {
        query = "update `internetcourse`.`Kurs` SET tematKursu=? where idKurs= ?";
        preparedStatement = mysqlConnection.prepareStatement(query);
        preparedStatement.setString(1, newSubject);
        preparedStatement.setInt(2, idCourse);
        preparedStatement.executeUpdate();

        query = "update `internetcourse`.`Kurs` SET trescKursu= ? where idKurs=?";
        preparedStatement = mysqlConnection.prepareStatement(query);
        preparedStatement.setString(1, newText);
        preparedStatement.setInt(2, idCourse);
        preparedStatement.executeUpdate();
    }

    public ArrayList<Test> testAllList() throws SQLException {
        ArrayList<Test> tests = new ArrayList<>();

        query = "SELECT * FROM `internetcourse`.`test`\n" +
                "join `internetcourse`.kurs on kurs.idKurs = test.idKurs\n";

        preparedStatement = mysqlConnection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Test test = new Test();
            test.setIdTest(resultSet.getInt("idTest"));
            test.setName(resultSet.getString("nazwaKursu"));
            test.setLevel(resultSet.getString("poziomTestu"));
            test.setAuthor(resultSet.getString("autorTestu"));
            tests.add(test);
        }

        return tests;
    }


    public ArrayList<Course> courseAllList(String level) throws SQLException {
        ArrayList<Course> courses = new ArrayList<>();

        query = "SELECT * FROM `internetcourse`.`kurs`\n";
        preparedStatement = mysqlConnection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Course course = new Course();
            courses.add(course);
        }

        return courses;
    }


    public ArrayList<Test> tuitionList(String level) throws SQLException {
        ArrayList<Test> tests = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();

        query = "SELECT * FROM `internetcourse`.`test`\n" +
                "join `internetcourse`.kurs on kurs.idKurs = test.idKurs\n" +
                " where poziomTestu =?";

        preparedStatement = mysqlConnection.prepareStatement(query);
        preparedStatement.setString(1, level);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Test test = new Test();
            test.setIdTest(resultSet.getInt("idTest"));
            test.setName(resultSet.getString("nazwaKursu"));
            test.setLevel(resultSet.getString("poziomTestu"));
            tests.add(test);
        }

        return tests;
    }

    public ArrayList<Course> courseList(String level) throws SQLException {
        ArrayList<Course> courses = new ArrayList<>();

        query = "SELECT * FROM `internetcourse`.`kurs`\n" +
                " where poziomKursu =?";

        preparedStatement = mysqlConnection.prepareStatement(query);
        preparedStatement.setString(1, level);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Course course = new Course();
            courses.add(course);
        }

        return courses;
    }

    public ArrayList<Question> addQuestion(int idTest) throws SQLException {
        ArrayList<Test> tests = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();

        query = "SELECT * FROM `internetcourse`.`test`\n" +
                "join `internetcourse`.kurs on kurs.idKurs = test.idKurs\n" +
                "join `internetcourse`.pytanie on pytanie.idTest = test.idTest \n" +
                " where test.idTest = ?";

        preparedStatement = mysqlConnection.prepareStatement(query);
        preparedStatement.setInt(1, idTest);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Question question = new Question();

            question.addAnswer(Question.AnswerLetter.A, resultSet.getString("odpowiedza"));
            question.addAnswer(Question.AnswerLetter.B, resultSet.getString("odpowiedzb"));
            question.addAnswer(Question.AnswerLetter.C, resultSet.getString("odpowiedzc"));
            question.addAnswer(Question.AnswerLetter.D, resultSet.getString("odpowiedzd"));

            question.setQuestionName(resultSet.getString("tresc"));
            question.setCorrectAnswer(Question.AnswerLetter.valueOf(resultSet.getString("prawidlowaOdpowiedz")));
            questions.add(question);
        }
        return questions;
    }

    private int insertCourse(Course course) throws SQLException {
        query = "INSERT INTO `internetcourse`.`Kurs` (`nazwaKursu`, `poziomKursu`, `tematKursu`, `trescKursu`)\n" +
                "VALUES (?, ?, ?, ?)";
        System.out.println(course.getLevel());
        preparedStatement = mysqlConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, course.getName());
        preparedStatement.setString(2, course.getLevel());
        preparedStatement.setString(3, course.getSubject());
        preparedStatement.setString(4, course.getContent());
        preparedStatement.executeUpdate();
        return generatedKeys();
    }

    private int generatedKeys() throws SQLException {
        try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
            rs.next();
            return rs.getInt(1);
        }
    }

    private int insertTest(Test test, int idCourse) throws SQLException {
        query = "INSERT INTO `internetcourse`.`Test` (`idKurs`, `nazwaTestu`, `autorTestu`, `iloscPytan`, `poziomTestu`)\n" +
                "VALUES (?, ?, ?, ?, ?);\n";
        preparedStatement = mysqlConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, idCourse);
        preparedStatement.setString(2, test.getName());
        preparedStatement.setString(3, "niewiem");
        preparedStatement.setInt(4, test.getNumberOfQuestion());
        preparedStatement.setString(5, test.getLevel());
        preparedStatement.executeUpdate();
        return generatedKeys();
    }

    private int insertWynik(Account account, Test test) throws SQLException {
        query = "INSERT INTO `internetcourse`.`Wynik` (`nazwaTestu`, `nazwaUzytkownika`, `iloscPunktow`, `data`)\n" +
                "VALUES (?, ?, ?, '2017-01-04 00:00:00');";
        preparedStatement = mysqlConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, test.getName());
        preparedStatement.setString(2, account.getFullname());
        preparedStatement.setInt(3, test.getResult());
        preparedStatement.executeUpdate();
        return generatedKeys();
    }

    public void insertTuition(Account account, Test test, Course course) throws SQLException {

		TransactionManager tm = CustomTransactionManager.getTransactionManager();
        try {
            tm.begin();
            mysqlConnection.setAutoCommit(false);
            postgressConnection.setAutoCommit(false);

            int idCourse = insertCourse(course);
            int idTest = insertTest(test, idCourse);
            test.getQuestions()
                .forEach(q -> storeQuestion(q, idTest));

            int idResult = insertWynik(account, test);

            query = "INSERT INTO `internetcourse`.`RozwiazanyTest` (`idKonto`, `idTest`, `idWynik`)\n" +
                    "VALUES (?, ?, ?);";
            preparedStatement = mysqlConnection.prepareStatement(query);
            preparedStatement.setInt(1, account.getIdAccount());
            preparedStatement.setInt(2, idTest);
            preparedStatement.setInt(3, idResult);
            preparedStatement.executeUpdate();

            tm.commit();
        } catch (Exception e) {
            try {
                tm.rollback();
            } catch (SystemException e1) {
                e1.printStackTrace();
                throw new RuntimeException(e1);
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            mysqlConnection.setAutoCommit(true);
            postgressConnection.setAutoCommit(true);
        }

    }

    private void storeQuestion(Question question, int idTest) {

        try {
            query = "SELECT nextval('questions.\"pytanie_sq\"')";
            preparedStatement = postgressConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int pytanieId = resultSet.getInt(1);

            query = "INSERT INTO questions.\"Pytanie\"(\n" +
                    "\t\"idPytanie\", \"idTest\", tresc, \"odpowiedzA\", \"odpowiedzB\", \"odpowiedzC\", \"odpowiedzD\", \"prawidlowaOdpowiedz\", \"liczbaPunktow\")\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            preparedStatement = postgressConnection.prepareStatement(query);
            preparedStatement.setInt(1, pytanieId);
            preparedStatement.setInt(2, idTest);
            preparedStatement.setString(3, question.getQuestionName());
            preparedStatement.setString(4, question.getAnswerText(Question.AnswerLetter.A));
            preparedStatement.setString(5, question.getAnswerText(Question.AnswerLetter.B));
            preparedStatement.setString(6, question.getAnswerText(Question.AnswerLetter.C));
            preparedStatement.setString(7, question.getAnswerText(Question.AnswerLetter.D));
            preparedStatement.setString(8, question.getCorrectAnswer().toString());
            preparedStatement.setString(9, "1");
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Test> completeResultTest(Account account) throws SQLException {
        ArrayList<Test> tests = new ArrayList<>();

        query = "SELECT wynik.* FROM `internetcourse`.konto\n" +
                "join `internetcourse`.rozwiazanytest on  konto.idKonto = rozwiazanytest.idKonto\n" +
                "join `internetcourse`.wynik on wynik.idWynik = rozwiazanytest.idWynik\n" +
                "where konto.idKonto=" + account.getIdAccount();

        preparedStatement = mysqlConnection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Test test = new Test();
            test.setName(resultSet.getString("nazwaTestu"));
            test.setResult(resultSet.getInt("iloscPunktow"));
            tests.add(test);
        }

        return tests;
    }

    public void insertResult(Account account, Test test) throws SQLException {
        query = "INSERT INTO `internetcourse`.`wynik` ( `nazwaTestu`, `nazwaUzytkownika`, `iloscPunktow`, `data`) " +
                "VALUES (?, ?, ?, '2017-01-04 00:00:00');";
        preparedStatement = mysqlConnection.prepareStatement(query);
        preparedStatement.setString(1, test.getName());
        preparedStatement.setString(2, account.getFullname());
        preparedStatement.setInt(3, test.getResult());
        preparedStatement.executeUpdate();


        query = "SELECT wynik.idWynik FROM `internetcourse`.wynik where nazwaTestu= ? AND nazwaUzytkownika=?";
        preparedStatement = mysqlConnection.prepareStatement(query);
        preparedStatement.setString(1, test.getName());
        preparedStatement.setString(2, account.getFullname());
        ResultSet resultSet = preparedStatement.executeQuery();
        int idWynik = 0;

        while (resultSet.next()) {
            idWynik = resultSet.getInt("idWynik");
        }

        query = "INSERT INTO `internetcourse`.`rozwiazanytest` (`idKonto`, `idTest`, `idWynik`) VALUES (?, ?, ?);";
        preparedStatement = mysqlConnection.prepareStatement(query);
        preparedStatement.setInt(1, account.getIdAccount());
        preparedStatement.setInt(2, test.getIdTest());
        preparedStatement.setInt(3, idWynik);
        preparedStatement.executeUpdate();

    }

    public Course getCourse(Course course) throws SQLException {
        query = "select kurs.tematKursu, kurs.trescKursu, kurs.idKurs from `internetcourse`.kurs " +
                "where poziomKursu=? and nazwaKursu=?";
        preparedStatement = mysqlConnection.prepareStatement(query);
        preparedStatement.setString(1, course.getLevel());
        preparedStatement.setString(2, course.getName());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            course.setSubject(resultSet.getString("tematKursu"));
            course.setContent(resultSet.getString("trescKursu"));
            course.setIdCourse(resultSet.getInt("idKurs"));
        }

        return course;
    }

    public Course getCourse(Course course, Account account) throws SQLException {
        query = "select kurs.tematKursu, kurs.trescKursu, test.autorTestu from `internetcourse`.kurs \n" +
                "join `internetcourse`.test on test.idKurs = kurs.idKurs\n" +
                "where poziomKursu=? and nazwaKursu=? and test.autorTestu=?";
        preparedStatement = mysqlConnection.prepareStatement(query);
        preparedStatement.setString(1, course.getLevel());
        preparedStatement.setString(2, course.getName());
        preparedStatement.setString(3, account.getFullname());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            course.setSubject(resultSet.getString("tematKursu"));
            course.setContent(resultSet.getString("trescKursu"));
        }

        return course;
    }
}
