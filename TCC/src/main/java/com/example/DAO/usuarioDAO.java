package com.example.DAO;

import java.sql.*;
import com.example.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


@Repository
public class usuarioDAO {

    String resultado;

    @Autowired
    private DataSource dataSource;

    public void InsertCadastroIntoBD(String NOME, String SOBRENOME, String EMAIL, String SENHA) throws SQLException {
// conexão
        Connection conn = dataSource.getConnection();

// SQL
        String sql = "INSERT INTO public.pessoas (\"NOME\", \"SOBRENOME\", \"EMAIL\", \"SENHA\") VALUES (?, ?, ?, ?)";

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
        Connection conn = dataSource.getConnection();

// SQL
        String sql = "SELECT \"SENHA\" FROM pessoas WHERE \"EMAIL\" = ?";

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
        Connection conn = dataSource.getConnection();

        String sql = "UPDATE pessoas SET \"SENHA\" = ? WHERE \"EMAIL\" = ?";

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

        String sql = "SELECT * FROM pessoas WHERE \"EMAIL\" = ?";
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            LoginDTO usuario = new LoginDTO();
            usuario.setEmail(rs.getString("EMAIL"));
            usuario.setSenha(rs.getString("SENHA"));

// se tiver mais campos:

// usuario.setNome(rs.getString("nome"));

            return usuario;
        }
        return null;
    }
}