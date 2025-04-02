package org.example.queries;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {

    public static void createTableContabeis(Connection connection, int ano) throws SQLException {
        Statement statement = connection.createStatement();

        String sql = "CREATE TABLE demonstracoes_contabeis_"+ano+" (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY," +
                "data DATE," +
                "reg_ans INT(8)," +
                "cd_conta_contabil INT(8)," +
                "descricao VARCHAR(150)," +
                "vl_saldo_inicial FLOAT(10,2)," +
                "vl_saldo_final FLOAT(10,2)" +
                ")";
        statement.execute(sql);
        System.out.println("Tabela demonstracoes_contabeis_"+ano+" criada com sucesso!");
    }

    public static void createTableOperadoras(Connection connection) throws SQLException{
        Statement statement = connection.createStatement();

        String sql = "CREATE TABLE operadoras_ativas (" +
                    "registro_operadora VARCHAR(6) PRIMARY KEY," +
                    "cnpj VARCHAR(14)," +
                    "razao_social VARCHAR(140)," +
                    "nome_fantasia VARCHAR(140)," +
                    "modalidade VARCHAR(40)," +
                    "logradouro VARCHAR(40)," +
                    "numero VARCHAR(20)," +
                    "complemento VARCHAR(40)," +
                    "bairro VARCHAR(30)," +
                    "cidade VARCHAR(30)," +
                    "uf VARCHAR(2)," +
                    "cep VARCHAR(8)," +
                    "ddd VARCHAR(4)," +
                    "telefone VARCHAR(20)," +
                    "fax VARCHAR(20)," +
                    "endereco_eletronico VARCHAR(255)," +
                    "representante VARCHAR(50)," +
                    "cargo_representante VARCHAR(40)," +
                    "regiao_de_comercializacao int(1)," +
                    "data_registro_ans DATE" +
                    ")";

            statement.execute(sql);
            System.out.println("Tabela operadoras_ativas criada com sucesso!");
    }

    public static void importTableContabeis(Connection connection, int ano) throws SQLException {
        String sql = "INSERT INTO demonstracoes_contabeis_"+ano+" (data,reg_ans,cd_conta_contabil,descricao,vl_saldo_inicial,vl_saldo_final) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        importarCsvContabeis(preparedStatement, ano);
    }

    public static void importarCsvContabeis(PreparedStatement statement, int ano){

        int count = 1;
        do {
            String csvFilePath = "src\\main\\resources\\" + ano + "\\" + count + "T" + ano + ".csv";
            try {
                System.out.println(csvFilePath);
                BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
                String lineText;

                lineReader.readLine(); // Ignorar o cabeçalho
                while ((lineText = lineReader.readLine()) != null) {
                    String[] split = lineText.split(";");
                    String data = split[0].replace("\"", "");
                    String reg_ans = split[1].replace("\"", "");
                    String cd_conta_contabil = split[2].replace("\"", "");
                    String descricao = split[3].replace("\"", "");
                    String vl_saldo_inicial = split[4].replace(",", ".");
                    String vl_saldo_final = split[5].replace(",", ".");

                    statement.setString(1, data);
                    statement.setString(2, reg_ans);
                    statement.setString(3, cd_conta_contabil);
                    statement.setString(4, descricao);
                    statement.setString(5, vl_saldo_inicial);
                    statement.setString(6, vl_saldo_final);

                    statement.addBatch();
                }

                statement.executeBatch();
                lineReader.close();
                System.out.println("Dados importados com sucesso!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            count++;
        }while(count < 5);
    }

    public static void importTableOperadoras(Connection connection) throws SQLException {

        String sql = "INSERT INTO operadoras_ativas (registro_operadora,cnpj,razao_social,nome_fantasia,modalidade,logradouro,numero,complemento,bairro,cidade,uf,cep,ddd,telefone,fax,endereco_eletronico,representante,cargo_representante,regiao_de_comercializacao,data_registro_ans) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            importarCsvOperadoras(preparedStatement, "src\\main\\resources\\operadoras\\Relatorio_cadop.csv");
    }

    public static void importarCsvOperadoras(PreparedStatement statement, String csvFilePath){

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText;

            lineReader.readLine(); // Ignorar o cabeçalho
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(";");
                String registro_operadora = data[0].replace("\"", "");
                String cnpj = data[1].replace("\"", "");
                String razao_social = data[2].replace("\"", "");
                String nome_fantasia = data[3].replace("\"", "");
                String modalidade = data[4].replace("\"", "");
                String logradouro = data[5].replace("\"", "");
                String numero = data[6].replace("\"", "");
                String complemento = data[7].replace("\"", "");
                String bairro = data[8].replace("\"", "");
                String cidade = data[9].replace("\"", "");
                String uf = data[10].replace("\"", "");
                String cep = data[11].replace("\"", "");
                String ddd = data[12].replace("\"", "");
                String telefone = data[13].replace("\"", "");
                String fax = data[14].replace("\"", "");
                String endereco_eletronico = data[15].replace("\"", "");
                String representante = data[16].replace("\"", "");
                String cargo_representante = data[17].replace("\"", "");
                String regiao_de_comercializacao = data[18].replace("", "0");
                String data_registro_ans = data[19].replace("\"", "");

                statement.setString(1, registro_operadora);
                statement.setString(2, cnpj);
                statement.setString(3, razao_social);
                statement.setString(4, nome_fantasia);
                statement.setString(5, modalidade);
                statement.setString(6, logradouro);
                statement.setString(7, numero);
                statement.setString(8, complemento);
                statement.setString(9, bairro);
                statement.setString(10, cidade);
                statement.setString(11, uf);
                statement.setString(12, cep);
                statement.setString(13, ddd);
                statement.setString(14, telefone);
                statement.setString(15, fax);
                statement.setString(16, endereco_eletronico);
                statement.setString(17, representante);
                statement.setString(18, cargo_representante);
                statement.setString(19, regiao_de_comercializacao);
                statement.setString(20, data_registro_ans);

                statement.addBatch();
            }

            statement.executeBatch();
            lineReader.close();
            System.out.println("Dados importados com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
