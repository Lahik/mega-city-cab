package com.megacitycab.database;

import static org.junit.Assert.*;
import java.sql.Connection;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBConnectionTest {
	
	private static Connection connection;
	
	@BeforeClass
	public static void setUp() {
		connection = DBConnectionFactory.getConnection();
	}
	
	@Test
	public void testDatabaseConnection() {
		assertNotNull("Database connection should not be null", connection);
	}
	
	@Test
	public void testConnectionIsValid() throws Exception {
		assertTrue("Connection should be valid", connection.isValid(2));
	}
}
