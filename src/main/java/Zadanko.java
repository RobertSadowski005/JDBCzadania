import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class Zadanko {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/firma?serverTimezone=UTC&useSSL=false";
        String databaseUsername = "root";
        String databasePassword = "Kvcsidto5";
        Properties properties = new Properties();
        properties.put("user", databaseUsername);
        properties.put("password", databasePassword);
//        Connection connection = DriverManager.getConnection(url, properties);
        try (Connection connection = DriverManager.getConnection(url, properties)) {
            createStatment(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    private static void createStatment(Connection connection) throws SQLException {
        Integer id = 6;
        String name = "Bagiń%";
        String query = "SELECT * FROM kontrah WHERE NR_KONTRAH = ? AND IMIE_NAZWISKO LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            boolean execute = statement.execute();
            if(execute){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int column1 = resultSet.getInt(1);
                    String column2 = resultSet.getString(2);
                    String column3 = resultSet.getString(3);
                    String column4 = resultSet.getString(4);
                    String column5 = resultSet.getString(5);
                    String row = new StringJoiner(", ")
                            .add(String.valueOf(column1)).add(column2)
                            .add(column3).add(column4).add(column5)
                            .toString();
                    System.out.println(row);
                }

                resultSet.close();
            }
        }

    }
    }
