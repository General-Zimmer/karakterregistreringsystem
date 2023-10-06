package opgaver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class OPG2 {

    /**
     * @param args
     */
    static Connection minConnection;
    static Statement stmt;
    static BufferedReader inLine;

    public OPG2(Connection minConnection, Statement stmt, BufferedReader inLine) {
        this.minConnection = minConnection;
        this.stmt = stmt;
        this.inLine = inLine;
    }
    public static void OPG_2() {
        try {
            System.out.println("Skriv en startDato");
            String startDato = inLine.readLine();
            System.out.println("Skriv en slutDato");
            String slutDato = inLine.readLine();
            System.out.println("Skriv en karakter");
            String karakter = inLine.readLine();
            System.out.println("Skriv et eksamensForsøgID");
            String eksamensForsøgID = inLine.readLine();
            System.out.println("Skriv en studerendes ID");
            String studerendeID = inLine.readLine();
            System.out.println("Skriv et eksamensID");
            String eksamensID = inLine.readLine();

            String sql = "INSERT INTO eksamensForsøg VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = minConnection.prepareStatement(sql)) {
                preparedStatement.setString(1, startDato);
                preparedStatement.setString(2, slutDato);
                preparedStatement.setString(3, karakter);
                preparedStatement.setString(4, eksamensForsøgID);
                preparedStatement.setString(5, studerendeID);
                preparedStatement.setString(6, eksamensID);
                preparedStatement.executeUpdate();

                System.out.println("Eksamensafvikling er blevet oprettet.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

