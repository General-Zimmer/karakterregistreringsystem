package opgaver;
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

    public OPG3(Connection minConnection, Statement stmt, BufferedReader inLine){
        this.minConnection = minConnection;
        this.stmt = stmt;
        this.inLine = inLine;
    }
    public void OPG_3() {
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
            int id;
            try {
                id = Integer.parseInt(eksamensid);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Eksamens id skal være et tal");
            }
            if (id < 1) throw new IllegalArgumentException("Eksamens id skal være større end 1");
            // Laver sql-sætning og får den udført
            String sql = """
                    select studerende.navn, studerende.studieID, eksamensForsøg.karakter from eksamensForsøg
                    join studerende on eksamensForsøg.FK_studieID = studerende.studieID
                    where eksamensForsøg.FK_eksamenID =""" + id;
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
}
