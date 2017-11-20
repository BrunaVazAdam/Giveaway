package pojo;

public class Endereco {
	
	private long idEndereco;
	private String cidade;
	private String bairro;
	private String rua;
	private String num;
	private String cep;
 
	public Endereco(){
		
	}
	public Endereco(String cidade, String bairro, String rua, String num, String cep){
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.num = num;
		this.cep = cep;
	}
	public long getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(long idEndereco) {
		this.idEndereco = idEndereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
}
