package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pojo.Endereco;
import pojo.Instituicao;


public class InstituicaoDAO{


	private ConexaoMysql conexao;
	
	public InstituicaoDAO(){
		this.conexao = new ConexaoMysql ("localhost" , "giveaway", "root", "");
	}
	
	public Instituicao login(String email, String senha){
		int idEndereco = 0;
		String sqlSelect = "SELECT * from instituicao where instituicao.email='"+email+"'";
		String sqlSelectEndereco = "SELECT * FROM endereco WHERE endereco.id_endereco = ?";
		Instituicao instituicao = new Instituicao();
		Endereco endereco = new Endereco();
		this.conexao.abrirConexao();
		try {
			Statement statement = this.conexao.getConexao().createStatement();
			ResultSet rs = statement.executeQuery(sqlSelect);
			rs = statement.getResultSet();
			if(rs != null && rs.next()){
				if(rs.getString("senha").equals(senha)){
					instituicao.setDescricao(rs.getString("descricao"));
					instituicao.setId(Integer.parseInt(rs.getString("id_instituicao")));
					instituicao.setNome(rs.getString("nome"));
					instituicao.setSenha(rs.getString("senha"));
					instituicao.setTelefone(rs.getString("telefone"));
					instituicao.setEmail(email);
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
							endereco.setCep(rsEnd.getString("cep"));
							instituicao.setEndereco(endereco);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					System.out.println("Senha errada!");
				}
				
			}else{
				System.out.println("Usuario não existe!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.conexao.fecharConexao();
		}
		return instituicao;
	}

	public Instituicao salvar(Instituicao instituicao){
		String sqlInsert = "INSERT INTO instituicao VALUES(null, ?, ?, ?, ?, ?, ?)";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, instituicao.getNome());
			statement.setString(2, instituicao.getEmail());
			statement.setString(3, instituicao.getSenha());
			statement.setString(4, instituicao.getTelefone());
			statement.setString(5, instituicao.getDescricao());
			statement.setLong(6, instituicao.getEndereco().getIdEndereco());
			statement.executeUpdate();
		    ResultSet rs = statement.getGeneratedKeys();
		    if(rs.next()){
		    	 instituicao.setId(rs.getLong(1));
		    	 
		    }
		    
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.conexao.fecharConexao();
		}
		return instituicao;
	}
       

	public Instituicao editarDescricao(Instituicao instituicao){
		String sqlUpdate = "UPDATE instituicao SET descricao = ? WHERE id_instituicao = ?";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, instituicao.getDescricao());
			statement.setLong(2, instituicao.getId());
			statement.executeUpdate();
			 ResultSet rs = statement.getGeneratedKeys();
			    if(rs.next()){
			    	 instituicao.setId(rs.getLong(2));
			    	 
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.conexao.fecharConexao();
		}
		return instituicao;
	}
	public Instituicao editar(Instituicao instituicao){
		String sqlUpdate = "UPDATE instituicao SET nome = ?, senha = ?, descricao = ? WHERE id_instituicao = ?";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, instituicao.getNome());
			statement.setString(2, instituicao.getSenha());
			statement.setString(3, instituicao.getDescricao());
			statement.setLong(4, instituicao.getId());
			statement.executeUpdate();
			 ResultSet rs = statement.getGeneratedKeys();
			    if(rs.next()){
			    	 instituicao.setId(rs.getLong(4));
			    	 
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.conexao.fecharConexao();
		}
		return instituicao;
}
}
	//public Instituicao deletar(Instituicao instituicao){
	//String sqlDeletarInstituicao = "DELETE FROM instituicao WHERE id_instituicao = ? ";
	//this.conexao.abrirConexao();
	//try {
		//PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDeletarInstituicao, Statement.RETURN_GENERATED_KEYS);
		//statement.setLong(1, instituicao.getId());
				//statement.executeUpdate();
		// ResultSet rs = statement.getGeneratedKeys();
		   // if(rs.next()){
		    	// instituicao.setId(rs.getLong(1));
		    	 
		   // }
	//} catch (SQLException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
	//}finally{
		//this.conexao.fecharConexao();
	//}
	//return instituicao;

//}


