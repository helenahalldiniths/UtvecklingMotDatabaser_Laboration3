package se.iths.helena;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ArtistDaoImpl implements ArtistDao{
    private Connection connection;

    public ArtistDaoImpl() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/laboration3", "helena", "Password123");
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate(
                    "CREATE TABLE Artist (" +
                            "id int," +
                            "first_name varchar(50)," +
                            "last_name varchar(50)," +
                            "age smallint," +
                            "primary key(id)" +
                            " );");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Tabellen finns redan");
        }
    }

    @Override
    public void add(Artist artist) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Artist (id, first_name, last_name, age) VALUES (?,?,?,?)");
            statement.setInt(1, artist.getId());
            statement.setString(2, artist.getFirstName());
            statement.setString(3, artist.getLastName());
            statement.setInt(4, artist.getAge());
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("id " + artist.getId() +" är upptaget");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Artist artist) {

    }

    @Override
    public void update(Artist artist, int age) {

    }

    @Override
    public void update(Artist artist, String lastName) {

    }

    @Override
    public void showAll() {

    }

    @Override
    public Optional<Artist> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Artist> findByAge(int age) {
        return null;
    }

    @Override
    public List<Artist> findByName(String lastName) {
        return null;
    }
}
