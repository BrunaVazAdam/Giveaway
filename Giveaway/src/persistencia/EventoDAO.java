package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pojo.Evento;

public class EventoDAO {
	private ConexaoMysql conexao;

	public EventoDAO() {
		this.conexao = new ConexaoMysql("localhost", "Giveaway", "root", "");
	}

	public Evento salvar(Evento evento, long idInstituicao) {
		String sqlInsert = "INSERT INTO eventos VALUES(null, ?, ?, ?, ?, ?, ?, ?)";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, evento.getNomeEv());
			statement.setString(2, evento.getData());
			statement.setString(3, evento.getHora());
			statement.setString(4, evento.getOrganizacao());
			statement.setString(5, evento.getDescricao());
			statement.setLong(6, evento.getEndereco().getIdEndereco());
			statement.setLong(7, idInstituicao);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				evento.setIdEvento(rs.getLong(1));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return evento;

	}



	public void editar(Evento evento) {
		String sqlEditar = "UPDATE evento SET nomeEv=?, organizacao=?, data=?, descricao=?, hora=? WHERE id=?";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setString(1, evento.getNomeEv());
			statement.setString(2, evento.getOrganizacao());
			statement.setString(3, evento.getData());
			statement.setString(4, evento.getDescricao());
			statement.setString(5, evento.getHora());
			statement.setLong(6, evento.getIdEvento());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void deletarPorId(long id) {
		String sqlDeletarPorId = "DELETE FROM evento WHERE id=?";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDeletarPorId);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
}
