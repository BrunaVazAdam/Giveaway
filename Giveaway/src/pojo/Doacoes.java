package pojo;

public class Doacoes {
	
	private long idDoacoes;
	private double valor;
	private long idInst;
	private long idDoador;
	public Doacoes(){
	}
	 
	 
	public Doacoes(double valor, long idInst, long idDoador) {
		super();
		this.valor = valor;
		this.idInst = idInst;
		this.idDoador = idDoador;
	}


	public long getIdDoacoes() {
		return idDoacoes;
	}
	public void setIdDoacoes(long idDoacoes) {
		this.idDoacoes = idDoacoes;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public long getIdInst() {
		return idInst;
	}
	public void setIdInst(long idInst) {
		this.idInst = idInst;
	}
	public long getIdDoador() {
		return idDoador;
	}
	public void setIdDoador(long idDoador) {
		this.idDoador = idDoador;
	}
	
	
		
	}
	 

