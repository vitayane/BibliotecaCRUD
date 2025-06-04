package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	// URL de conexão com o banco de dados MySQL. Inclui o nome do banco (sistema)
		private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
		
		//Informar o usuário e a senha do banco de dados
		private static final String USER = "root";
		private static final String PASSWORD = "";
		
		//Método estático que retorna uma conexão com o banco de dados
		public static Connection conectar() throws SQLException {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		}
}
