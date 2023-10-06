package OPG3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class OPG3 {

    /**
     * @param args
     */
    static Connection minConnection;
    static Statement stmt;
    static BufferedReader inLine;

    public static void selectmedparm() {
        /*
         du indtaste navnet på en given eksamen og en given termin.
         Programmet skal som resultat vise en liste af de
        studerende, der har deltaget i denne afvikling af denne eksamen i denne termin. Udover
        den studerendes navn og id, skal karakteren også være med i resultatet
         */
        try {
            // Indlæser søgestreng
            System.out.println("Indtast eksamens id");
            String eksamensid = inLine.readLine();
            // Laver sql-sætning og får den udført
            String sql = """
                    select studerende.navn, studerende.studieID, eksamensForsøg.karakter from eksamensForsøg
                    join studerende on eksamensForsøg.FK_studieID = studerende.studieID
                    where eksamensForsøg.FK_eksamenID =""" + eksamensid;
            System.out.println("SQL-streng er " + sql);
            ResultSet res = stmt.executeQuery(sql);
            //gennemløber svaret
            while (res.next()) {
                System.out.println(res.getString(1) + "    " + res.getString(2));
            }
            // pæn lukning
            if (!minConnection.isClosed()) minConnection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

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
            selectmedparm();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void OPG_1(){

    }
}
