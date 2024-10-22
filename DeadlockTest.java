///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.duckdb:duckdb_jdbc:1.1.1

import java.sql.*;

public class DeadlockTest
{
    public static void main(String... args) throws SQLException
    {
        for (int i = 0; i < 100; i++)
        {
            executeQuery();
        }
    }

    private static void executeQuery() throws SQLException
    {
        System.out.println("getting connection");

        try (Connection connection = DriverManager.getConnection("jdbc:duckdb::memory:test"))
        {
            Statement statement = connection.createStatement();

            System.out.println("Executing query");

            ResultSet resultSet = statement.executeQuery("select 42");

            resultSet.next();

            if (!"42".equals(resultSet.getString(1)))
            {
                throw new AssertionError("Expected 42");
            }

            resultSet.close();

//            statement.close();
        }

        System.out.println("done executing query");
    }
}
