package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pojo.Doacoes;


public class DoacoesDAO {

	private ConexaoMysql conexao;
	
	public DoacoesDAO(){
		this.conexao = new ConexaoMysql ("localhost" , "Giveaway", "root", "");
	}
	public  Doacoes salvar(Doacoes doacoes){
		String sqlInsert = "INSERT INTO doacoes VALUES(null, ?, ?, ?)";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setDouble(1, doacoes.getValor());
			statement.setLong(2, doacoes.getIdInst());
			statement.setLong(3, doacoes.getIdDoador());
			statement.executeUpdate();
		    ResultSet rs = statement.getGeneratedKeys();
		    if(rs.next()){
		    	 doacoes.setIdDoacoes(rs.getLong(1));
		    	 
		    }
		    
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.conexao.fecharConexao();
		}
		return doacoes;
	}
	 public ArrayList<Doacoes> listarDoacoesPorInst(long idInst){
		 ArrayList<Doacoes> listaDoacoes = new ArrayList<Doacoes>();
		 String selectDoacoes = "SELECT * FROM doacoes WHERE doacoes.cod_id_instituicao = ?";
		 this.conexao.abrirConexao();
		 try {
			 PreparedStatement statement = this.conexao.getConexao().prepareStatement(selectDoacoes);
			 statement.setString(1, Long.toString(idInst));
			 ResultSet rs = statement.executeQuery();
			 while(rs.next()){
				 Doacoes doacoes = new Doacoes();
				 doacoes.setValor(rs.getDouble("valor"));
				 listaDoacoes.add(doacoes);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		 return listaDoacoes;
	 }
}
