package DAO;

import java.sql.*;

public class usuarioDAO {

    String resultado;

    String url = "jdbc:mysql://localhost:3306/cadastro";
    String user = "root";
    String password = "72B4688B321DBD81718EC88A9BDCBCFD";

    public void InsertCadastroIntoBD(String NOME, String SOBRENOME, String EMAIL, String SENHA) throws SQLException {
        // conexão
        Connection conn = DriverManager.getConnection(url, user, password);

        // SQL
        String sql = "INSERT INTO pessoas (NOME, SOBRENOME, EMAIL, SENHA) VALUES (?, ?, ?, SHA2(?,256))";

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

    public String QueryLoginUsuario(String EMAIL, String SENHA) throws SQLException {
        // conexão
        Connection conn = DriverManager.getConnection(url, user, password);

        // SQL
        String sql = "SELECT count(p.id) FROM pessoas p WHERE p.email = ? and p.senha = ?";

        // preparar
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, EMAIL);
        stmt.setString(2, SENHA);

        //Realizar Querys
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);

            if (count > 0) {
                resultado = "1";
            } else {
                resultado = "0";
            }
        }

        // fechar
        rs.close();
        stmt.close();
        conn.close();

        return resultado;
    }



}
