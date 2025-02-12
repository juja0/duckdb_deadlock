/// usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.duckdb:duckdb_jdbc:1.2.0

import java.sql.*;

public class ExtensionIssueDemo
{
	public static void main(String... args) throws SQLException
	{
		System.out.println("getting connection");

		try (Connection connection = DriverManager.getConnection("jdbc:duckdb::memory:test"))
		{
			Statement statement = connection.createStatement();

			System.out.println("Executing query");

			ResultSet resultSet = statement.executeQuery("pragma platform");

			resultSet.next();

			System.out.println(resultSet.getString(1));

			resultSet.close();

			statement.execute("install httpfs; load httpfs;");
			
			statement.close();
		}

		System.out.println("done executing query");
	}
}
