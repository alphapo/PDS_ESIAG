package connection_pool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import greg_pds.ServerMetier;


public class ConnectionPool{
	ArrayList<Connection> connectionsList = new ArrayList<Connection>();

	public ConnectionPool(){
		initializeConnectionPool();
	}

	private void initializeConnectionPool()
	{
		while(!checkIfConnectionPoolIsFull())
		{
			System.out.println("Connection Pool is NOT full. Proceeding with adding new connections");
			//Adding new connection instance until the pool is full
			connectionsList.add(createNewConnectionForPool());
		}
		System.out.println("Connection Pool is full.");
	}

	private synchronized boolean checkIfConnectionPoolIsFull()
	{
		final int MAX_POOL_SIZE = 15;

		//Check if the pool size
		if(connectionsList.size() < MAX_POOL_SIZE)
		{
			return false;
		}

		return true;
	}

	//Creating a connection
	private Connection createNewConnectionForPool()
	{
		Connection connection = null;
		Properties prop = new Properties();
		InputStream input = null;
		
		String DriverName;
		String database;
		String dbuser;
		String dbpassword;

		String filename = "config.properties";
		input = ServerMetier.class.getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			System.out.println("Sorry, unable to find " + filename);
		}
		// load a properties file
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DriverName = prop.getProperty("DriverName");
		database = prop.getProperty("database");
		dbuser = prop.getProperty("dbuser");
		dbpassword = prop.getProperty("dbpassword");
		try
		{
			Class.forName(DriverName);
			connection = DriverManager.getConnection(database, dbuser, dbpassword);
			System.out.println("Connection: "+connection);
		}
		catch(SQLException e)
		{
			System.err.println("SQLException: "+e);
			return null;
		}
		catch(ClassNotFoundException e)
		{
			System.err.println("ClassNotFoundException: "+e);
			return null;
		}
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public synchronized Connection getConnectionFromPool()
	{
		Connection connection = null;

		//Check if there is a connection available. There are times when all the connections in the pool may be used up
		if(connectionsList.size() > 0)
		{
			connection = connectionsList.get(0);
			connectionsList.remove(0);
		}
		//Giving away the connection from the connection pool
		return connection;
	}

	public synchronized void returnConnectionToPool(Connection connection)
	{
		//Adding the connection from the client back to the connection pool
		connectionsList.add(connection);
	}
	public synchronized int sizeConnectionPool(){
		return connectionsList.size();
	}
}
