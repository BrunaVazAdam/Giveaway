package pojo;

public class Doador {
	
	private long id;
	private String nome;
	private String email;
	private String senha;
	
	
	
	
	public Doador() {
		
	}
		
	public Doador(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		
	}

	//MÉTODOS
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}






