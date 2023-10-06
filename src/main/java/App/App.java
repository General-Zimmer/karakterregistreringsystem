package App;

import opgaver.OPG1;
import opgaver.OPG2;
import opgaver.OPG3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class App {
    static Connection minConnection;
    static Statement stmt;
    static BufferedReader inLine;
    public static void main(String[] args) {
        try {
            inLine = new BufferedReader(new InputStreamReader(System.in));
            //generel opsætning
            //via native driver
            String server = "80.209.108.69"; //virker måske hos dig
            //virker det ikke - prøv kun med localhost
            String dbnavn = "Karakterregistreringssystem";            //virker måske hos dig
            String login = "sa";                     //skal ikke ændres
            String password = "Hejmeddig1234";            //skal ændres
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            minConnection = DriverManager.getConnection("jdbc:sqlserver://" + server + ";databaseName=" + dbnavn +
                    ";user=" + login + ";password=" + password + ";");
            //minConnection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=eksempeldb;user=sa;password=torben07;");
            stmt = minConnection.createStatement();
            //Indlæsning og kald af den rigtige metode
            System.out.println("Indtast  ");
            System.out.println("1 for OPG 1 ");
            System.out.println("2 for OPG 2");
            System.out.println("3 for OPG 3");
            String in = inLine.readLine();
            switch (in) {
                case "1" -> {
                    new OPG1(minConnection, stmt, inLine).OPG_1();
                }
                case "2" -> {
                    new OPG2(minConnection, stmt, inLine).OPG_2();
                }
                case "3" -> {
                    new OPG3(minConnection, stmt, inLine).OPG_3();
                }
                default -> System.out.println("ukendt indtastning");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
