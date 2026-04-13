package DAO;

import java.sql.*;
import com.example.dto.LoginDTO;

public class usuarioDAO {

    String resultado;

    String url = "jdbc:mysql://localhost:3306/cadastro";
    String user = "root";
    String password = "72B4688B321DBD81718EC88A9BDCBCFD";

    public void InsertCadastroIntoBD(String NOME, String SOBRENOME, String EMAIL, String SENHA) throws SQLException {
        // conexão
        try (Connection conn = DriverManager.getConnection(url, user, password)) { // Modifiquei

        // SQL
        String sql = "INSERT INTO pessoas (NOME, SOBRENOME, EMAIL, SENHA) VALUES (?, ?, ?, ?)";

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

    public String QueryLoginUsuario(String EMAIL) throws SQLException {
        // conexão
        Connection conn = DriverManager.getConnection(url, user, password);

        // SQL
        String sql = "Select SENHA FROM pessoas where EMAIL = ?";

        // preparar
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, EMAIL);

                    // Se encontrou usuario e senha correta
                    if (rs.next()) {

        if (rs.next()) {
            resultado = rs.getString("SENHA");
            System.out.println("Senha: " + resultado);
        }

        return "0"; // login invalido
    }

    public LoginDTO buscarPorEmail(String email) throws SQLException {

        String sql = "SELECT * FROM pessoas WHERE email = ?";

        Connection conn = DriverManager.getConnection(url, user, password);

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            LoginDTO usuario = new LoginDTO();

            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));

            // se tiver mais campos:
            // usuario.setNome(rs.getString("nome"));

            return usuario;
        }

        return null;
    }

}