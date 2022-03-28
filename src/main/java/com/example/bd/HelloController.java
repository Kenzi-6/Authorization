package com.example.bd;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class HelloController {
    protected String dbHost = "localhost";
    protected String dbPort = "3306";
    protected String dbUser = "root";
    protected String dbName = "a1";
    protected String dbPass = "Qwerty123456";
    Connection dbConnection;
    public HelloController() {
        // TODO Auto-generated constructor stub
    }
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionStr = "jdbc:mysql://" + dbHost + ":" +
                dbPort + "/" + dbName + "?" + "autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift" +
                "=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionStr, dbUser, dbPass);
        return dbConnection;
    }
    public Button bt;
    public TextField tf;
    public TextField ft;
    @FXML
    // чтение данных с полей
    public void initialize() {
        bt.setOnAction(event -> {
            String logTxt = tf.getText();
            String passw = ft.getText();
            try {
                blb(logTxt, passw);
            } catch (Exception e) {
                e.printStackTrace();}
        });
    }
    // запрос в БД на чтение
    public ResultSet getUs(String logTxt, String passw) {
        ResultSet rs = null;
        String select = "select * from userdata where Email =? AND Password =?";
        try{
            PreparedStatement ps = getDbConnection().prepareStatement(select);
            ps.setString(1, logTxt);
            ps.setString(2, passw);
            rs = ps.executeQuery();}
        catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();}
        return rs;
    }
    // проверка на правильность
    public void blb(String logTxt, String passw){
        ResultSet res = getUs(logTxt, passw);
        int count = 0;
        try {
            while (res.next()) {
                count++;}}
        catch (SQLException e) {
            e.printStackTrace();}

        if (count >= 1){
            System.out.println("Авторизация прошла успешно");
        }
        else{System.out.println("Введены некорректные данные");}
    }
}
