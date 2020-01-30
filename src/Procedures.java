import java.io.IOException;
import java.sql.*;
import java.util.concurrent.Callable;

public class Procedures {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        call_Int();

    }

    /* private static void executeQuery() throws SQLException {
         try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/shop?serverTimezone=UTC", "root", "Asassin2305");
              Statement statement = connection.createStatement();
              ResultSet resultSet = statement.executeQuery("select * from user")) {

             System.out.println("\nUsers:");
             while (resultSet.next()) {
                 int id = resultSet.getInt("id");
                 String name = resultSet.getString("name");
                 String email = resultSet.getString("email");

                 System.out.println("\n=================\n");
                 System.out.println("id: " + id);
                 System.out.println("name: " + name);
                 System.out.println("email: " + email);
             }
         }
     }

     private static void executeUpdate() {
         try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/eshop?serverTimezone=UTC", "root", "Asassin2305");
              Statement statement = connection.createStatement()) {
             int update = statement.executeUpdate("update user set email = 'makar@gmail.com'");
             System.out.println("rows changed:" + update);
         } catch (SQLException e) {
             e.printStackTrace();
         }

     }

     private static void preparedStatement() {
         try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/eshop?serverTimezone=UTC", "root", "Asassin2305")) {
             PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id=?");
             preparedStatement.setInt(1, 1);
             preparedStatement.execute();
             ResultSet resultSet = preparedStatement.getResultSet();
             while (resultSet.next()) {
                 int id = resultSet.getInt("id");
                 String name = resultSet.getString("name");
                 String email = resultSet.getString("email");

                 System.out.println("\n=================\n");
                 System.out.println("id: " + id);
                 System.out.println("name: " + name);
                 System.out.println("email: " + email);
             }

         } catch (SQLException e) {
             e.printStackTrace();
         }
     }*/
    private static void call_Out() {
        try {
            String query = "call proc2(?)";

            try (Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost/test?serverTimezone=UTC", "root", "Asassin2305");
                 CallableStatement callableStatement = connection.prepareCall(query)) {
                /*callableStatement.setInt(1, 3);*/
                callableStatement.registerOutParameter(1, Types.INTEGER);
                callableStatement.execute();
                int i = callableStatement.getInt(1);
                System.out.println(i);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void call_Int() {
        try {
            String query = "call proc(?)";

            try (Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost/test?serverTimezone=UTC", "root", "Asassin2305");
                 CallableStatement callableStatement = connection.prepareCall(query)) {
                callableStatement.setInt(1, 3);
                ResultSet resultSet=callableStatement.executeQuery();
//                callableStatement.getInt(1);
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");

                    System.out.println("\n=================\n");
                    System.out.println("id: " + id);
                    System.out.println("name: " + name);
                    System.out.println("email: " + email);
                }


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


