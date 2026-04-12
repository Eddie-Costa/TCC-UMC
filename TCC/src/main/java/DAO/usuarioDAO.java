package DAO;

import java.sql.*;

public class usuarioDAO {

    String resultado;

    String url = "jdbc:mysql://localhost:3306/cadastro";
    String user = "root";
    String password = "72B4688B321DBD81718EC88A9BDCBCFD";

    public void InsertCadastroIntoBD(String NOME, String SOBRENOME, String EMAIL, String SENHA) throws SQLException {
        // conexão
        try (Connection conn = DriverManager.getConnection(url, user, password)) { // Modifiquei

            // SQL
            String sql = "INSERT INTO pessoas (NOME, SOBRENOME, EMAIL, SENHA) VALUES (?, ?, ?, SHA2(?,256))";

            // preparar
            try (PreparedStatement stmt = conn.prepareStatement(sql)) { // Modifiquei
                stmt.setString(1, NOME);
                stmt.setString(2, SOBRENOME);
                stmt.setString(3, EMAIL);
                stmt.setString(4, SENHA);

                // executar
                stmt.executeUpdate();
            } // Modifiquei

            System.out.println("Inserido com sucesso!");
        } // Modifiquei
    }


    //Apartir daqui.
    public String QueryLoginUsuario(String EMAIL, String SENHA) throws SQLException {

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            // Verifica se email + senha estao corretos
            String sql = "SELECT id FROM pessoas WHERE email = ? AND senha = SHA2(?,256)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, EMAIL);
                stmt.setString(2, SENHA);

                try (ResultSet rs = stmt.executeQuery()) {

                    // Se encontrou usuario e senha correta
                    if (rs.next()) {

                        // GERA CoDIGO 2FA (6 dígitos)
                        String codigo = String.valueOf(100000 + new java.util.Random().nextInt(900000));

                        // Salva codigo e tempo de expiração (5 minutos)
                        String update = "UPDATE pessoas SET codigo_2fa = ?, expiracao_2fa = NOW() + INTERVAL 5 MINUTE WHERE email = ?";

                        try (PreparedStatement stmtUpdate = conn.prepareStatement(update)) {
                            stmtUpdate.setString(1, codigo);
                            stmtUpdate.setString(2, EMAIL);
                            stmtUpdate.executeUpdate();
                        }

                        // Simula envio (depois vira email)
                        System.out.println("Código 2FA: " + codigo);

                        return "2FA";
                    }
                }
            }
        }

        return "0"; // login invalido
    }

    // Validacao com o BD
    public String validar2FA(String EMAIL, String CODIGO) throws SQLException {

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String sql = "SELECT count(*) FROM pessoas WHERE email = ? AND codigo_2fa = ? AND expiracao_2fa > NOW()";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, EMAIL);
                stmt.setString(2, CODIGO);

                try (ResultSet rs = stmt.executeQuery()) {

                    if (rs.next() && rs.getInt(1) > 0) {

                        // Limpa o código apos uso // Modifiquei
                        String limpar = "UPDATE pessoas SET codigo_2fa = NULL, expiracao_2fa = NULL WHERE email = ?";
                        try (PreparedStatement stmtLimpar = conn.prepareStatement(limpar)) {
                            stmtLimpar.setString(1, EMAIL);
                            stmtLimpar.executeUpdate();
                        }

                        return "1"; // código valido
                    }
                }
            }
        }

        return "0";
    }

}