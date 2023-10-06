package opgaver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OPG1 {

    /**
     * @param args
     */
    static Connection minConnection;
    static Statement stmt;
    static BufferedReader inLine;

    public OPG1(Connection minConnection, Statement stmt, BufferedReader inLine) {
        this.minConnection = minConnection;
        this.stmt = stmt;
        this.inLine = inLine;
    }

    public void OPG_1() {
        ArrayList<String> acceptablesGrades = new ArrayList<String>(List.of(new String[]{"-3", "00", "02", "4", "7", "10", "12", "SY", "IM", "IA"}));
        try {
            String sqlString = "insert into eksamensforsøg values(";
            System.out.println("Indsæt eksamensforsøg ind i databasen");
            System.out.println("Indtast start dato dato for eksamforsøget [YYYY-MM-DD]");
            String startDato = inLine.readLine();
            try {
                LocalDate.parse(startDato);
            } catch (IllegalArgumentException e){
                System.err.println("Start Dato invalid");
                return;
            }
            System.out.println("Indtast slut dato for eksamforsøget [YYYY-MM-DD]");
            String slutDato = inLine.readLine();
            try {
                LocalDate.parse(slutDato);
            } catch (IllegalArgumentException e){
                System.err.println("Slut Dato invalid");
                return;
            }
            System.out.println("Indtast elevens studieID");
            String studieID = inLine.readLine();
            System.out.println("Indtast eksamenID");
            String eksamenID = inLine.readLine();
            System.out.println("Indtast karakter eller grund for udeblivelse");
            String karakter = inLine.readLine();
            if (!acceptablesGrades.contains(karakter)){
                throw new IllegalArgumentException("Karakter er ikke en gyldig");
            }
            System.out.println("Indtast 27 eller over for eksamensforsøgID");
            String efID = inLine.readLine();
            sqlString += "'" + startDato + "', '" + slutDato + "', '" + karakter + "', " +
                    efID + ", " + studieID + ", " + eksamenID + ")";
            System.out.println("Dint SQL streng er: " + sqlString);
            stmt.execute(sqlString);
            System.out.println("Eksamensforsøg er inserted");
        } catch (SQLException e) {
            System.err.println(e.getStackTrace());
            System.err.println("Tjek om StudieID, EksamensID og eksamensforsøgID er Gyldige");
        } catch (Exception e) {

        }
    }


}
