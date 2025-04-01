package org.example.entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperadoraDAO {


    public static Map<Integer, Operadora> carregarOperadoras(Connection conn, String dataInicio, String dataFim, String tableName) throws SQLException {
        Map<Integer, Operadora> operadoras = new HashMap<>();

        String query = "SELECT oa.registro_operadora, oa.nome_fantasia, dc.vl_saldo_inicial, dc.vl_saldo_final " +
                "FROM "+tableName+" dc " +
                "JOIN operadoras_ativas oa ON dc.reg_ans = oa.registro_operadora " +
                "WHERE dc.data BETWEEN ? AND ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, dataInicio);
            stmt.setString(2, dataFim);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int registro = rs.getInt("registro_operadora");
                String nome = rs.getString("nome_fantasia");
                float vlSaldoInicial = rs.getFloat("vl_saldo_inicial");
                float vlSaldoFinal = rs.getFloat("vl_saldo_final");

                float despesas = calcularDespesas(vlSaldoInicial, vlSaldoFinal);

                // Se a operadora já estiver no mapa, somamos suas despesas
                operadoras.putIfAbsent(registro, new Operadora(registro, nome));
                operadoras.get(registro).somarDespesas(despesas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operadoras;
    }

    // Método para calcular despesas baseado na lógica original
    private static float calcularDespesas(float vlSaldoInicial, float vlSaldoFinal) {
        float despesas;

        if (vlSaldoInicial != 0) {
            if (vlSaldoFinal > 0 && vlSaldoFinal >= vlSaldoInicial) {
                despesas = vlSaldoFinal - vlSaldoInicial;
            } else if (vlSaldoFinal > 0 && vlSaldoFinal < vlSaldoInicial) {
                despesas = vlSaldoInicial - vlSaldoFinal;
            } else if (vlSaldoFinal < 0 && vlSaldoInicial < 0 && vlSaldoFinal < vlSaldoInicial) {
                despesas = vlSaldoFinal - vlSaldoInicial;
            } else if (vlSaldoFinal < 0 && vlSaldoInicial < 0 && vlSaldoFinal > vlSaldoInicial) {
                despesas = vlSaldoInicial - vlSaldoFinal;
            } else {
                despesas = vlSaldoInicial;
            }
        } else {
            despesas = vlSaldoFinal;
        }

        // Garantir que despesas negativas continuem negativas
        return despesas > 0 ? -despesas : despesas;
    }

}
