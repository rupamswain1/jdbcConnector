import java.sql.*;

public class JdbcConnection {
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String conString = "jdbc:sqlite:dataBase.db";
        Connection connection = DriverManager.getConnection(conString);
        System.out.println("Connection to DB Eshtablished");
        String createSql = "CREATE TABLE IF NOT EXISTS employee (id INTEGER PRIMARY KEY, name TEXT,email TEXT)";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createSql);

        //Insert rows
        String insertSql = "INSERT into employee (name,email) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1,"Jhon Doe2");
        preparedStatement.setString(2, "jhon2@java.com");
        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println(rowsAffected+" Rows Affected");
        preparedStatement.close();

        String fetchSql = "SELECT * FROM employee";
        ResultSet results = statement.executeQuery(fetchSql);
        while (results.next()){
            int id = results.getInt("id");
            String name = results.getString("name");
            String email = results.getString("email");
            System.out.println(id+" "+name+" - "+email);
        }
        connection.close();
    }
}
