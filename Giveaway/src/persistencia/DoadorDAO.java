package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pojo.Doador;
import pojo.Endereco;
import pojo.Evento;
import pojo.Instituicao;

public class DoadorDAO {
	private ConexaoMysql conexao;

	public DoadorDAO() {
		this.conexao = new ConexaoMysql("localhost", "giveaway", "root", "");
	}

	public Doador login(String email, String senha) {
		String sqlSelect = "SELECT * from doador where doador.email='" + email + "'";
		Doador doador = new Doador();
		
		this.conexao.abrirConexao();
		try {
			Statement statement = this.conexao.getConexao().createStatement();
			ResultSet rs = statement.executeQuery(sqlSelect);
			rs = statement.getResultSet();
			if (rs != null && rs.next()) {
				if (rs.getString("senha").equals(senha)) {
					doador.setNome(rs.getString("nome"));
					doador.setSenha(rs.getString("senha"));
					doador.setId(Integer.parseInt(rs.getString("id_doador")));
					doador.setEmail(email);
					
						
					
				} else {
					System.out.println("Senha errada!\n");
				}

			} else {
				System.out.println("Usuario não existe!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return doador;
	}

	public Doador salvar(Doador doador) {
		String sqlInsert = "INSERT INTO doador VALUES(null, ?, ?, ?)";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, doador.getNome());
			statement.setString(2, doador.getEmail());
			statement.setString(3, doador.getSenha());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				doador.setId(rs.getLong(1));

			}

		} catch (SQLException e) {
			System.out.println("Email já registrado!\n");
			//e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return doador;

	}
	public ArrayList<Instituicao> buscarTodasPorCidade(String cidade){
		
		int idEndereco = 0;
		ArrayList<Instituicao> listaInstituicao = new ArrayList<Instituicao>();
		String sqlSelectPorCidade = "SELECT * FROM instituicao LEFT JOIN endereco ON instituicao.cod_id_endereco=endereco.id_endereco WHERE endereco.cidade LIKE '%"+cidade+"%'";
		String sqlSelectEndereco = "SELECT * FROM endereco WHERE endereco.id_endereco = ?";
		this.conexao.abrirConexao();
		try {
			Statement statement = this.conexao.getConexao().createStatement();
			ResultSet rs = statement.executeQuery(sqlSelectPorCidade);
			while(rs.next()){
				Instituicao instituicao = new Instituicao();
				Endereco endereco = new Endereco();
				instituicao.setId(Integer.parseInt(rs.getString("id_instituicao")));
				instituicao.setNome(rs.getString("nome"));
				instituicao.setSenha(rs.getString("senha"));
				instituicao.setTelefone(rs.getString("telefone"));
				instituicao.setEmail(rs.getString("email"));
				instituicao.setDescricao(rs.getString("descricao"));
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
						instituicao.setEndereco(endereco);
						listaInstituicao.add(instituicao);
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
		return listaInstituicao;
	}
	
public ArrayList<Evento> buscarEventosPorCidade(String cidade){
		
		int idEndereco = 0;
		ArrayList<Evento> listaEventos = new ArrayList<Evento>();
		String sqlSelectPorCidade = "SELECT * FROM eventos LEFT JOIN endereco ON eventos.cod_id_endereco=endereco.id_endereco WHERE endereco.cidade LIKE '%"+cidade+"%'";
		String sqlSelectEndereco = "SELECT * FROM endereco WHERE endereco.id_endereco = ?";
		this.conexao.abrirConexao();
		try {
			Statement statement = this.conexao.getConexao().createStatement();
			ResultSet rs = statement.executeQuery(sqlSelectPorCidade);
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

public ArrayList<Instituicao> buscarTodasPorNome(String nome){
		
		int idEndereco = 0;
		ArrayList<Instituicao> listaInstituicao = new ArrayList<Instituicao>();
		String sqlSelectPorCidade = "SELECT * FROM instituicao WHERE instituicao.nome LIKE '%"+nome+"%'";
		String sqlSelectEndereco = "SELECT * FROM endereco WHERE endereco.id_endereco = ?";
		this.conexao.abrirConexao();
		try {
			Statement statement = this.conexao.getConexao().createStatement();
			ResultSet rs = statement.executeQuery(sqlSelectPorCidade);
			while(rs.next()){
				Instituicao instituicao = new Instituicao();
				Endereco endereco = new Endereco();
				instituicao.setId(Integer.parseInt(rs.getString("id_instituicao")));
				instituicao.setNome(rs.getString("nome"));
				instituicao.setSenha(rs.getString("senha"));
				instituicao.setTelefone(rs.getString("telefone"));
				instituicao.setEmail(rs.getString("email"));
				instituicao.setDescricao(rs.getString("descricao"));
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
						instituicao.setEndereco(endereco);
						listaInstituicao.add(instituicao);
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
		return listaInstituicao;
	}
	public ArrayList<Evento> buscarEventosPorNome(String nome){
		
		int idEndereco = 0;
		ArrayList<Evento> listaEventos = new ArrayList<Evento>();
		String sqlSelectPorCidade = "SELECT * FROM eventos WHERE eventos.nome LIKE '%"+nome+"%'";
		String sqlSelectEndereco = "SELECT * FROM endereco WHERE endereco.id_endereco = ?";
		this.conexao.abrirConexao();
		try {
			Statement statement = this.conexao.getConexao().createStatement();
			ResultSet rs = statement.executeQuery(sqlSelectPorCidade);
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
	public Doador editar(Doador doador){
		String sqlUpdate = "UPDATE doador SET nome = ?, senha = ? WHERE id_doador = ?";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, doador.getNome());
			statement.setString(2, doador.getSenha());
			statement.setLong(3, doador.getId());
			statement.executeUpdate();
			 ResultSet rs = statement.getGeneratedKeys();
			    if(rs.next()){
			    	 doador.setId(rs.getLong(1));
			    	 
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.conexao.fecharConexao();
		}
		return doador;
}
}
