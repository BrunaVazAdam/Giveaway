package pojo;


public class Evento {

	private long idEvento;
	private String nomeEv;
	private String organizacao;
	private String data;
	private String descricao;
	private Endereco endereco;
	private String hora;
	
	public Evento(){
		
	}
	
	public Evento(String nomeEv, String organizacao, String data, String descricao, Endereco endereco, String hora){
		this.nomeEv = nomeEv;
		this.organizacao = organizacao;
		this.data = data;
		this.descricao = descricao;
		this.endereco = endereco;
		this.hora = hora;
	}
	
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNomeEv() {
		return nomeEv;
	}

	public void setNomeEv(String nomeEv) {
		this.nomeEv = nomeEv;
	}

	public String getOrganizacao() {
		return organizacao;
	}

	public void setOrganizacao(String organizacao) {
		this.organizacao = organizacao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
