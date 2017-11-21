package menu;


import java.util.ArrayList;
import java.util.Scanner;

import persistencia.DoacoesDAO;
import persistencia.DoadorDAO;
import persistencia.EnderecoDAO;
import persistencia.EventoDAO;
import persistencia.InstituicaoDAO;
import pojo.Doacoes;
import pojo.Doador;
import pojo.Endereco;
import pojo.Evento;
import pojo.Instituicao;


public class Main {
	private static Scanner t;

	public static void main(String[] args) {
		t = new Scanner(System.in);
		String nome, senha, email, telefone, descricao, cidade, bairro, rua, num, cep;
		boolean sair, logado;
		Doador doador;
		Doacoes doacoes;
		Evento evento;
		Instituicao instituicao;
		Endereco endereco;
		EventoDAO eventoDAO = new EventoDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		InstituicaoDAO instituicaoDAO = new InstituicaoDAO();
		DoadorDAO doadorDAO = new DoadorDAO();
		DoacoesDAO doacoesDAO = new DoacoesDAO();
		ArrayList<Instituicao> listaInstituicoes = new ArrayList<Instituicao>();
		ArrayList<Doacoes> listaDoacoes = new ArrayList<Doacoes>();
		ArrayList<Evento> listaEventos = new ArrayList<Evento>();
		do {
			int x;
			sair = false;
			logado = false;
			System.out.println("1 - Login\n2 - Cadastro\n3 - Fechar Programa");
			x = t.nextInt();
			// login
			if (x == 1) {
				System.out.println("1 - Instituição\n2 - Doador");
				int y = t.nextInt();
				// instituição
				if (y == 1) {
					System.out.printf("Digite seu email:");
					t.nextLine();
					email = t.nextLine();
					System.out.printf("Digite sua senha:");
					senha = t.nextLine();
					instituicao = instituicaoDAO.login(email, senha);
					if(instituicao.getDescricao() != null){
						do{
							logado = true;
							System.out.println(" \n1 - Gerenciar Eventos\n2 - Alterar Descrição\n3 - Alterar Cadastro\n4 - Excluir Cadastro\n5 - Listar Doações\n6 - Deslogar");
							int i = t.nextInt();
							if(i == 1){
								System.out.println("1 - Criar Evento\n2 - Alterar Evento\n3 - Excluir Evento");
								int e = t.nextInt();
								if(e == 1){
									t.nextLine();
									System.out.print("Nome:");
									String nomeEv = t.nextLine();
									System.out.print("Data - DD/MM/AAAA:");
									String data = t.nextLine();
									System.out.print("Horário: - 00:00");
									String hora = t.nextLine();
									System.out.print("Organizadores:");
									String organizacao = t.nextLine();
									System.out.print("Descricao:");
									String desc = t.nextLine();
									System.out.print("Cidade: ");
									cidade = t.nextLine();
									System.out.print("Bairro: ");
									bairro = t.nextLine();
									System.out.print("Rua: ");
									rua = t.nextLine();
									System.out.print("Número: ");
									num = t.nextLine();
									System.out.print("Cep: ");
									cep = t.nextLine();
		
									endereco = new Endereco(cidade, bairro, rua, num, cep);
									enderecoDAO.salvar(endereco);
									evento = new Evento(nomeEv, organizacao, data, desc, endereco, hora);
									eventoDAO.salvar(evento, instituicao.getId());
								} else if(e == 2){
									
									} else if(e == 3){
									
								}
							} else if(i == 2){
								
								t.nextLine();
								System.out.print("Digite a nova descrição: ");
								descricao = t.nextLine();
								instituicao.setDescricao(descricao);
								instituicaoDAO.editarDescricao(instituicao);
								
							} else if(i == 3){
								t.hasNextLine();
								System.out.println("Altere o cadastro: ");
								nome = t.nextLine();
								senha = t.nextLine();
								descricao = t.nextLine();
								instituicao.setNome(nome);
								instituicao.setSenha(senha);
								instituicao.setDescricao(descricao);
								instituicaoDAO.editar(instituicao);
								
							} else if(i == 4){
								
							} else if(i == 5){
								listaDoacoes = doacoesDAO.listarDoacoesPorInst(instituicao.getId());
								int contDoacoes = 0;
								for(Doacoes doa: listaDoacoes){
									contDoacoes ++;
									System.out.println(contDoacoes + " - Valor: R$ " + doa.getValor());
								}
								System.out.println("\n");
							} else if(i == 6){
								logado = false;
							}
						}while(logado);
					}
				}
				// doador
				if (y == 2) {
					System.out.printf("Digite seu email:");
					t.nextLine();
					email = t.nextLine();
					System.out.printf("Digite sua senha:");
					senha = t.nextLine();
					doador = doadorDAO.login(email, senha);
					if(doador.getNome() != null){
						do{
							logado = true;
							System.out.println("1 - Pesquisar Instituições por cidade\n2 - Pesquisar Instituições por nome "
									+ "\n3 - Pesquisar Eventos por cidade\n4 - Pesquisar Eventos por nome\n5 - Deslogar");
							int d = t.nextInt();
							if(d == 1){
								
								t.nextLine();
								System.out.print("Digite a Cidade: ");
								cidade = t.nextLine();
								listaInstituicoes = doadorDAO.buscarTodasPorCidade(cidade);
								int contInst = 0;
								for (Instituicao inst : listaInstituicoes){
									contInst ++;
									System.out.println(contInst + " - INSTITUIÇÃO: " + inst.getNome()+ "\nCidade: " + inst.getEndereco().getCidade()+"\n");
								}
								if(!listaInstituicoes.isEmpty()){
									System.out.println("Qual instituição voce quer vizualizar?");
									int ls = t.nextInt();
									ls --;
									System.out.println("Nome: " + listaInstituicoes.get(ls).getNome() +
														"\nDescrição: " + listaInstituicoes.get(ls).getDescricao() + 
														"\nEmail: " + listaInstituicoes.get(ls).getEmail() +
														"\nTelefone: "   + listaInstituicoes.get(ls).getTelefone()+
														"\n\nEndereço:\nCidade: " + listaInstituicoes.get(ls).getEndereco().getCidade()+
														"\nBairro:" +  listaInstituicoes.get(ls).getEndereco().getBairro()+
														"\nRua:"  + listaInstituicoes.get(ls).getEndereco().getRua()+
														"\nNúmero:"  + listaInstituicoes.get(ls).getEndereco().getNum()+
														"\nCEP:"  + listaInstituicoes.get(ls).getEndereco().getCep());
									System.out.println("\nDeseja doar para essa instituição? \n1 - Sim \n2 - Não");
									int ds = t.nextInt();
									if(ds == 1){
										System.out.print("Digite o valor:");
										double valor = t.nextDouble();
										doacoes = new Doacoes(valor, listaInstituicoes.get(ls).getId(), doador.getId());
										doacoesDAO.salvar(doacoes);
										System.out.println("Parabéns, você acaba de realizar uma doaçâo para a Instituição" + listaInstituicoes.get(ls).getNome() + " no valor de R$" + valor );
									}
								} else {
									System.out.println("Nenhuma Instituição Encontrada !");
								}
							} else if(d == 2){
								t.nextLine();
								System.out.print("Digite o nome:");
								nome = t.nextLine();
								listaInstituicoes = doadorDAO.buscarTodasPorNome(nome);
								int contInst = 0;
								for (Instituicao inst : listaInstituicoes){
									contInst ++;
									System.out.println(contInst + " - INSTITUIÇÃO: " + inst.getNome()+ "\nBairro: " + inst.getEndereco().getBairro()+"\n");
								}
								if(!listaInstituicoes.isEmpty()){
									System.out.println("Qual instituição voce quer vizualizar?");
									int ls = t.nextInt();
									ls --;
									System.out.println("Nome: " + listaInstituicoes.get(ls).getNome() +
														"\nDescrição: " + listaInstituicoes.get(ls).getDescricao() + 
														"\nEmail: " + listaInstituicoes.get(ls).getEmail() +
														"\nTelefone: "   + listaInstituicoes.get(ls).getTelefone()+
														"\n\nEndereço:\nCidade: " + listaInstituicoes.get(ls).getEndereco().getCidade()+
														"\nBairro:" +  listaInstituicoes.get(ls).getEndereco().getBairro()+
														"\nRua:"  + listaInstituicoes.get(ls).getEndereco().getRua()+
														"\nNúmero:"  + listaInstituicoes.get(ls).getEndereco().getNum()+
														"\nCEP:"  + listaInstituicoes.get(ls).getEndereco().getCep());
									System.out.println("\nDeseja doar para essa instituição? \n1 - Sim \n2 - Não");
									int ds = t.nextInt();
									if(ds == 1){
										System.out.print("Digite o valor:");
										double valor = t.nextDouble();
										doacoes = new Doacoes(valor, listaInstituicoes.get(ls).getId(), doador.getId());
										doacoesDAO.salvar(doacoes);
										System.out.printf("Parabéns, você acaba de realizar uma doaçâo para a Instituiçâo " + listaInstituicoes.get(ls).getNome() + " no valor de R$ %.2f%n", valor );
									}
								} else {
									System.out.println("Nenhuma Instituição Encontrada !");
								}
							} else if(d == 3){
								t.nextLine();
								System.out.print("Digite a Cidade: ");
								cidade = t.nextLine();
								listaEventos = doadorDAO.buscarEventosPorCidade(cidade);
								int contInst = 0;
								for (Evento eventoo : listaEventos){
									contInst ++;
									System.out.println(contInst + " - Evento: " + eventoo.getNomeEv()+ "\nCidade: " + eventoo.getEndereco().getCidade()+"\n");
								}
								if(!listaEventos.isEmpty()){
									System.out.println("Qual Evento você quer vizualizar?");
									int ls = t.nextInt();
									ls --;
									System.out.println("Nome: " + listaEventos.get(ls).getNomeEv() +
														"\nDescrição: " + listaEventos.get(ls).getDescricao() + 
														"\nDia: " + listaEventos.get(ls).getData() +
														"\nHora: "   + listaEventos.get(ls).getHora()+
														"\nOrganização: \n" + listaEventos.get(ls).getOrganizacao()+"\n");	
								} else {
									System.out.println("Nenhum evento encontrado !");
								}
							} else if(d == 4){
								t.nextLine();
								System.out.print("Digite o nome: ");
								nome = t.nextLine();
								listaEventos = doadorDAO.buscarEventosPorNome(nome);
								int contInst = 0;
								for (Evento eventoo : listaEventos){
									contInst ++;
									System.out.println(contInst + " - Evento: " + eventoo.getNomeEv()+ "\nCidade: " + eventoo.getEndereco().getCidade()+"\n");
								}
								if(!listaEventos.isEmpty()){
									System.out.println("Qual Evento você quer vizualizar?");
									int ls = t.nextInt();
									ls --;
									System.out.println("Nome: " + listaEventos.get(ls).getNomeEv() +
														"\nDescrição: " + listaEventos.get(ls).getDescricao() + 
														"\nDia: " + listaEventos.get(ls).getData() +
														"\nHora: "   + listaEventos.get(ls).getHora()+
														"\nOrganização: \n" + listaEventos.get(ls).getOrganizacao()+"\n");	
								} else {
									System.out.println("Nenhum evento encontrado !");
								}
							} else if(d == 5){
								logado = false;
							}	
						}while(logado);
					}
				}
			}
			// cadastro
			if (x == 2) {
				System.out.println("1 - Instituição\n2 - Doador");
				int z = t.nextInt();
				if (z == 1) {
					//instituicao
					t.nextLine();
					System.out.print("Nome:");
					nome = t.nextLine();
					System.out.print("Email:");
					email = t.nextLine();
					System.out.print("Senha:");
					senha = t.nextLine();
					System.out.print("Telefone:");
					telefone = t.nextLine();
					System.out.print("Descricao:");
					descricao = t.nextLine();
					System.out.print("Cidade: ");
					cidade = t.nextLine();
					System.out.print("Bairro: ");
					bairro = t.nextLine();
					System.out.print("Rua: ");
					rua = t.nextLine();
					System.out.print("Número: ");
					num = t.nextLine();
					System.out.print("cep: ");
					cep = t.nextLine();

					endereco = new Endereco(cidade, bairro, rua, num, cep);
					enderecoDAO.salvar(endereco);
					instituicao = new Instituicao(nome, email, senha, telefone, descricao, endereco);
					instituicaoDAO.salvar(instituicao);
				}
				if (z == 2) {
					// doador
					t.nextLine();
					System.out.print("Nome: ");
					nome = t.nextLine();
					System.out.print("Email: ");
					email = t.nextLine();
					System.out.print("Senha: ");
					senha = t.nextLine();
					System.out.print("Telefone: ");
					telefone = t.nextLine();
					System.out.print("Cidade: ");
					cidade = t.nextLine();
					System.out.print("Bairro: ");
					bairro = t.nextLine();
					System.out.print("Rua: ");
					rua = t.nextLine();
					System.out.print("Número: ");
					num = t.nextLine();
					System.out.print("cep: ");
					cep = t.nextLine();

					endereco = new Endereco(cidade, bairro, rua, num, cep);
					enderecoDAO.salvar(endereco);
					doador = new Doador(nome, email, senha, telefone, endereco);
					doadorDAO.salvar(doador);
				}
			}
			if(x == 3){
				sair = true;
			}
		} while (!sair);
		System.out.println("Programa finalizado!");
				}
			
		}
	





