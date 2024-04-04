package com.example.config;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteInitializer {

    private static final String URL = "jdbc:sqlite:src/main/resources/data/mydatabase.db";

    public static void createDatabase() {
        File databaseFile = new File("src/main/resources/data/mydatabase.db");
        if (databaseFile.exists()) {
            System.out.println("O banco de dados já existe.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            String sqlBeneficiario = "CREATE TABLE IF NOT EXISTS beneficiarios (\n"
                + "    id INTEGER PRIMARY KEY,\n"
                + "    nome TEXT NOT NULL,\n"
                + "    telefone TEXT NOT NULL,\n"
                + "    data_nascimento DATE NOT NULL,\n"
                + "    data_inclusao DATE NOT NULL,\n"
                + "    data_atualizacao DATE,\n"
                + "    documentos TEXT\n"  
                + ");";
            stmt.execute(sqlBeneficiario);

            String sqlDocumento = "CREATE TABLE IF NOT EXISTS documentos (\n"
                    + "    id INTEGER PRIMARY KEY,\n"
                    + "    tipo_documento TEXT NOT NULL,\n"
                    + "    descricao TEXT NOT NULL,\n"
                    + "    data_inclusao DATE NOT NULL,\n"
                    + "    data_atualizacao DATE,\n"
                    + "    beneficiario_id INTEGER,\n"
                    + "    FOREIGN KEY (beneficiario_id) REFERENCES beneficiarios(id)\n"
                    + ");";

            stmt.execute(sqlDocumento);

        } catch (SQLException e) {
            System.out.println("Erro ao criar o banco de dados: " + e.getMessage());
        }
    }
}
