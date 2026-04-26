package com.example.DAO;

import java.sql.*;
import com.example.dto.LoginDTO;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;

@Repository
public class usuarioDAO {

    // 🔴 REMOVIDO: variável global desnecessária
    // String resultado;

    @Autowired
    private DataSource dataSource;

    public void InsertCadastroIntoBD(String nome, String sobrenome, String email, String senha) throws SQLException {

        Connection conn = dataSource.getConnection();

        // 🔴 ALTERADO: nomes das colunas em minúsculo (PostgreSQL)
        String sql = "INSERT INTO pessoas (nome, sobrenome, email, senha) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, nome);
        stmt.setString(2, sobrenome);
        stmt.setString(3, email);
        stmt.setString(4, senha);

        stmt.executeUpdate();

        System.out.println("Inserido com sucesso!");

        stmt.close();
        conn.close();
    }

    public String QueryLoginUsuario(String email) throws SQLException {

        Connection conn = dataSource.getConnection();

        // 🔴 ALTERADO: minúsculo
        String sql = "SELECT senha FROM pessoas WHERE email = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();

        String resultado = null; // 🔴 CORRIGIDO: variável local

        if (rs.next()) {
            resultado = rs.getString("senha");
        }

        rs.close();
        stmt.close();
        conn.close();

        return resultado;
    }

    public void UpdateSenhaUsuario(String senha, String email) throws SQLException {

        Connection conn = dataSource.getConnection();

        // 🔴 ALTERADO: minúsculo
        String sql = "UPDATE pessoas SET senha = ? WHERE email = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, senha);
        stmt.setString(2, email);

        int linhas = stmt.executeUpdate();

        System.out.println("Linhas afetadas: " + linhas);

        stmt.close();
        conn.close();
    }

    public LoginDTO buscarPorEmail(String email) throws SQLException {

        String sql = "SELECT * FROM pessoas WHERE email = ?";

        Connection conn = dataSource.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            LoginDTO usuario = new LoginDTO();

            // 🔴 ALTERADO: nomes corretos
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));

            return usuario;
        }

        rs.close();
        stmt.close();
        conn.close();

        return null;
    }

    // 🔥 NOVO: salvar código 2FA
    public void salvarCodigo2FA(String email, String codigo, Timestamp expiracao) throws SQLException {

        Connection conn = dataSource.getConnection();

        String sql = "UPDATE pessoas SET codigo_2fa = ?, expiracao_2fa = ? WHERE email = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, codigo);
        stmt.setTimestamp(2, expiracao);
        stmt.setString(3, email);

        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    // 🔥 NOVO: validar código 2FA
    public boolean validarCodigo2FA(String email, String codigo) throws SQLException {

        Connection conn = dataSource.getConnection();

        String sql = "SELECT * FROM pessoas WHERE email = ? AND codigo_2fa = ? AND expiracao_2fa > NOW()";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, codigo);

        ResultSet rs = stmt.executeQuery();

        boolean valido = rs.next();

        rs.close();
        stmt.close();
        conn.close();

        return valido;
    }
