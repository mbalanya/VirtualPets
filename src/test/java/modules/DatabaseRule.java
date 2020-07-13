package modules;

import org.junit.rules.ExternalResource;

import org.sql2o.Connection;
import org.sql2o.Sql2o;


public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/virtual_pets_test", "chalookoba", "1234"); // Those with linux or windows use two strings for username and password
    }

    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deletePersonsQuery = "DELETE FROM persons *;";
            String deleteMonstersQuery = "DELETE FROM monsters *;";
            con.createQuery(deletePersonsQuery).executeUpdate();
            con.createQuery(deleteMonstersQuery).executeUpdate();
        }
    }
}
