package connection_pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;
public class ConnectionPoolManager_WithDataSources {
		String driverName;
		String databaseUrl;
		String userName;
		String password;
		
		ArrayList connectionPool = new ArrayList();

		public ConnectionPoolManager_WithDataSources(String driverName,String databaseUrl,String userName,String password){
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
				connectionPool.add(createNewConnectionForPool());
			}
			System.out.println("Connection Pool is full.");
		}

		private synchronized boolean checkIfConnectionPoolIsFull()
		{
			final int MAX_POOL_SIZE = 5;

			//Check if the pool size
			if(connectionPool.size() < 5)
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
				BasicDataSource dataSource = new BasicDataSource();
				
				dataSource.setDriverClassName(driverName);
				dataSource.setUrl(databaseUrl);
				dataSource.setUsername(userName);
				dataSource.setPassword(password);
				dataSource.setInitialSize(5);
				
				//dataSource.
				Connection conn = dataSource.getConnection();
				
				System.out.println("Connection: "+connection);
			}
			catch(SQLException cnfe)
			{
				System.err.println("ClassNotFoundException: "+cnfe);
				return null;
			}

			return connection;
		}

		public synchronized Connection getConnectionFromPool()
		{
			Connection connection = null;

			//Check if there is a connection available. There are times when all the connections in the pool may be used up
			if(connectionPool.size() > 0)
			{
				connection = (Connection) connectionPool.get(0);
				connectionPool.remove(0);
			}
			//Giving away the connection from the connection pool
			return connection;
		}

		public synchronized void returnConnectionToPool(Connection connection)
		{
			//Adding the connection from the client back to the connection pool
			connectionPool.add(connection);
		}

}
