package com.example.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


@Repository
public class usuarioDAO {


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
        String resultado = "";

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
        }

        // fechar

        rs.close();
        stmt.close();
        conn.close();

        return resultado;
    }

    public void DeleteUser(String EMAIL) throws SQLException {
        // conexão
        Connection conn = dataSource.getConnection();

        // SQL
        String sql = "DELETE FROM pessoas WHERE \"EMAIL\" = ?";

        // preparar
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, EMAIL);

        //Realizar Querys
        int linhasAfetadas = stmt.executeUpdate();

        if(linhasAfetadas > 0){
            System.out.println("Usuário deletado");
        } else {
            System.out.println("Nenhum usuário encontrado com o email: " + EMAIL);
        }


        // fechar
        stmt.close();
        conn.close();
    }

    public ArrayList<String> QueryUserData(String EMAIL) throws SQLException {
        // conexão
        Connection conn = dataSource.getConnection();

        // SQL
        String sql = "SELECT \"NOME\", \"SOBRENOME\", \"EMAIL\", \"DT_REGISTER\" FROM pessoas WHERE \"EMAIL\" = ?";

        // preparar
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, EMAIL);

        //Realizar Querys
        ResultSet rs = stmt.executeQuery();

        ArrayList<String> Arrayresultado = new ArrayList<>();

        if (rs.next()) {
            Arrayresultado.add(rs.getString("NOME"));
            Arrayresultado.add(rs.getString("SOBRENOME"));
            Arrayresultado.add(rs.getString("EMAIL"));
            Arrayresultado.add(rs.getString("DT_REGISTER"));
        }

        // fechar
        rs.close();
        stmt.close();
        conn.close();

        return Arrayresultado;
    }

    public void UpdateSenhaUsuario(String SENHA, String EMAIL) throws SQLException {
        Connection conn = dataSource.getConnection();

        String sql = "UPDATE pessoas SET \"SENHA\" = ? WHERE \"EMAIL\" = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, SENHA);
        stmt.setString(2, EMAIL);

        System.out.println(stmt);
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

            return usuario;
        }

        rs.close();
        stmt.close();
        conn.close();
        return null;
    }
}