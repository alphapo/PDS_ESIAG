package connection_pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class ConnectionPool{
	String driverName;
	String databaseUrl;
	String userName;
	String password;
	
	ArrayList<Connection> connectionsList = new ArrayList<Connection>();

	public ConnectionPool(String driverName,String databaseUrl,String userName,String password){
		this.driverName = driverName;
		this.databaseUrl = databaseUrl;
		this.userName = userName;
		this.password = password;
		initialize();
	}

	private void initialize()
	{
		//Here we can initialize all the information that we need
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
		final int MAX_POOL_SIZE = 10;

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

		try
		{
			Class.forName(driverName);
			connection = DriverManager.getConnection(databaseUrl, userName, password);
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
