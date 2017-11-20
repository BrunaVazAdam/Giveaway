package pojo;

public class Instituicao {
	
	private long id;
	private String nome;
	private String email;
	private String senha;
	private String telefone;
	private String descricao;
	private Endereco endereco;
	private Evento[] evento;
	
	
	public Instituicao() {
		
	}
	
	public Instituicao(String nome, String email, String senha, String telefone, String descricao, Endereco endereco) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.descricao = descricao;
		this.endereco = endereco;
	}

	//MÉTODOS
	public long getId() {
		return this.id;
	}
	
	public Evento[] getEvento() {
		return evento;
	}

	public void setEvento(Evento[] evento) {
		this.evento = evento;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
		
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
}




