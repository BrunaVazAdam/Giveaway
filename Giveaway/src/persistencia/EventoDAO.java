package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pojo.Endereco;
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



	public Evento editar(Evento evento) {
		String sqlEditar = "UPDATE eventos SET nome=?, organizacao=?, dia=?, descricao_evento=?, hora=? WHERE id_evento=?";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, evento.getNomeEv());
			statement.setString(2, evento.getOrganizacao());
			statement.setString(3, evento.getData());
			statement.setString(4, evento.getDescricao());
			statement.setString(5, evento.getHora());
			statement.setLong(6, evento.getIdEvento());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				evento.setIdEvento(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return evento;
	}
	
	public ArrayList<Evento> listarEventosPorInstituicao(long id_instituicao){
		int idEndereco = 0;
		ArrayList<Evento> listaEventos = new ArrayList<Evento>();
		String sqlSelectPorInstituicao = "SELECT * FROM  eventos WHERE cod_id_instituicao ="+id_instituicao;
		String sqlSelectEndereco = "SELECT * FROM endereco WHERE endereco.id_endereco = ?";
		this.conexao.abrirConexao();
		try {
			Statement statement = this.conexao.getConexao().createStatement();
			ResultSet rs = statement.executeQuery(sqlSelectPorInstituicao);
			while(rs.next()){
				Evento evento = new Evento();
				Endereco endereco = new Endereco();
				evento.setIdEvento(Integer.parseInt(rs.getString("id_evento")));
				evento.setNomeEv(rs.getString("nome"));
				evento.setDescricao(rs.getString("descricao_evento"));
				evento.setHora(rs.getString("hora"));
				evento.setData(rs.getString("dia"));
				evento.setOrganizacao(rs.getString("organizacao"));
				idEndereco = Integer.parseInt(rs.getString("cod_id_endereco"));
				try { 
					PreparedStatement statementEnd = this.conexao.getConexao().prepareStatement(sqlSelectEndereco);
					statementEnd.setString(1, Integer.toString(idEndereco));
					ResultSet rsEnd = statementEnd.executeQuery();
					rsEnd = statementEnd.getResultSet();
					if(rsEnd != null && rsEnd.next()) {
						endereco.setIdEndereco(Integer.parseInt(rsEnd.getString("id_endereco")));
						endereco.setBairro(rsEnd.getString("bairro"));
						endereco.setCidade(rsEnd.getString("cidade"));
						endereco.setRua(rsEnd.getString("rua"));
						endereco.setNum(rsEnd.getString("num"));
						endereco.setCep(rsEnd.getString("CEP"));
						evento.setEndereco(endereco);
						listaEventos.add(evento);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaEventos;
	}

	public void deletarPorId(long id) {
		String sqlDeletarPorId = "DELETE FROM eventos WHERE id_evento=?";
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
