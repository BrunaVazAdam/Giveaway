package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pojo.Endereco;

public class EnderecoDAO {
	private ConexaoMysql conexao;
	
	public EnderecoDAO(){
		this.conexao = new ConexaoMysql ("localhost" , "Giveaway", "root", "");
	}
	public  Endereco salvar(Endereco endereco){
		String sqlInsert = "INSERT INTO endereco VALUES(null, ?, ?, ?, ?,?)";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, endereco.getCidade());
			statement.setString(2, endereco.getBairro());
			statement.setString(3, endereco.getRua());
			statement.setString(4, endereco.getNum());
			statement.setString(5, endereco.getCep());
			statement.executeUpdate();
		    ResultSet rs = statement.getGeneratedKeys();
		    if(rs.next()){
		    	 endereco.setIdEndereco(rs.getLong(1));
		    	 
		    }
		    
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.conexao.fecharConexao();
		}
		return endereco;
	
}
	public Endereco editar(Endereco endereco){
		String sqlUpdate = "UPDATE endereco SET cidade = ?, bairro = ?, rua = ?, num = ?, cep = ? WHERE id_endereco = ?";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, endereco.getCidade());
			statement.setString(2, endereco.getBairro());
			statement.setString(3, endereco.getRua());
			statement.setString(4, endereco.getNum());
			statement.setString(5, endereco.getCep());
			statement.setLong(6, endereco.getIdEndereco());
			statement.executeUpdate();
		    ResultSet rs = statement.getGeneratedKeys();
		    if(rs.next()){
		    	 endereco.setIdEndereco(rs.getLong(1));
		    	 
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.conexao.fecharConexao();
		}
		return endereco;
	}
}
