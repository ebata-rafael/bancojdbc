package org.example;


import org.example.BD.Conexao;
import org.example.entity.Operadora;
import org.example.entity.OperadoraDAO;
import org.example.queries.Queries;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        try {
            Connection connection = Conexao.getConexao();
            System.out.println("Conexão com o banco de dados estabelecida.");

//          CRIANDO TABELA
            Queries.createTableContabeis(connection, 2023);
//          IMPORTANDO DEMONSTRAÇÕES CONTABEIS 2023
            Queries.importTableContabeis(connection, 2023);

//          CRIANDO TABELA
            Queries.createTableContabeis(connection, 2024);
//          IMPORTANDO DEMONSTRAÇÕES CONTABEIS 2023
            Queries.importTableContabeis(connection, 2024);

//          CRIANDO TABELA OPERADORAS ATIVAS
            Queries.createTableOperadoras(connection);
//          IMPORTANDO OPERADORAS ATIVAS
            Queries.importTableOperadoras(connection);

//          ULTIMO TRIMESTRE
            String dataInicio = "2024-06-01";
            String dataFim = "2024-10-01";

            mostrarTop10OperadorasDefict(connection, dataInicio, dataFim, "demonstracoes_contabeis_2024");
            System.out.println();

//          ULTIMO ANO
            dataInicio = "2023-12-31";
            dataFim = "2025-01-01";
            mostrarTop10OperadorasDefict(connection, dataInicio, dataFim, "demonstracoes_contabeis_2024");

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public static void mostrarTop10OperadorasDefict(Connection connection, String dataInicio, String dataFim, String tableName) throws SQLException {

        // Carregar operadoras do banco
        Map<Integer, Operadora> operadoras = OperadoraDAO.carregarOperadoras(connection, dataInicio, dataFim, tableName);

        // Ordenar por maior déficit e pegar as top 10
        List<Operadora> topOperadoras = operadoras.values().stream()
                .sorted((o1, o2) -> Float.compare(o1.getTotalDespesas(), o2.getTotalDespesas()))
                .limit(10)
                .collect(Collectors.toList());


        // Exibir os resultados
        System.out.println("Top 10 Operadoras em Déficit:");
        for (Operadora op : topOperadoras) {
            System.out.println(op.getNomeFantasia() + " - Déficit total: " + op.getTotalDespesas());
        }

    }



}

