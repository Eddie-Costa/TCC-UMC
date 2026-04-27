package DAO;

import java.sql.*;
import com.example.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class usuarioDAO {

    String resultado;

    private String url = "jdbc:mysql://localhost:3306/cadastro";
    String user = System.getenv("DB_USUARIO");
    String password = System.getenv("DB_SENHA");

    public void InsertCadastroIntoBD(String NOME, String SOBRENOME, String EMAIL, String SENHA) throws SQLException {
        // conexão
        Connection conn = DriverManager.getConnection(url, user, password);

        // SQL
        String sql = "INSERT INTO pessoas (NOME, SOBRENOME, EMAIL, SENHA) VALUES (?, ?, ?, ?)";

        // preparar
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, NOME);
        stmt.setString(2, SOBRENOME);
        stmt.setString(3, EMAIL);
        stmt.setString(4, SENHA);

        // executar
        stmt.executeUpdate();

        System.out.println("Inserido com sucesso!");

        // fechar
        stmt.close();
        conn.close();
        }

    public String QueryLoginUsuario(String EMAIL) throws SQLException {
        // conexão
        Connection conn = DriverManager.getConnection(url, user, password);

        // SQL
        String sql = "Select SENHA FROM pessoas where EMAIL = ?";

        // preparar
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, EMAIL);

        //Realizar Querys
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            resultado = rs.getString("SENHA");
            System.out.println("Senha: " + resultado);
        }

        // fechar
        rs.close();
        stmt.close();
        conn.close();

        return resultado;
    }

    public void UpdateSenhaUsuario(String SENHA, String EMAIL) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);

        String sql = "UPDATE pessoas SET SENHA = ? WHERE EMAIL = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, SENHA);
        stmt.setString(2, EMAIL);

        System.out.println(stmt);
        stmt.executeUpdate();
        int linhas = stmt.executeUpdate();
        System.out.println("Linhas afetadas: " + linhas);

        stmt.close();
        conn.close();
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
