package pl.coderslab.model;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DbUtil.getConn();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
