package com.ti2cc;

import java.util.List;
import java.util.Scanner;

import dao.DAO;
import dao.VeiculoDAO;
import model.Veiculo;

public class Principal {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in, "UTF-8");
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		
		String options = "";
		
		
		String menu = "DIGITE: 1 PARA LISTAR \n 2 PARA INSERIR \n 3 PARA EXCLUIR \n 0 PARA SAIR";
		System.out.println(menu);
		options = sc.nextLine();
		
		switch(options) {
			case "1":
				listaVeiculos(veiculoDAO);
				break;
			case "2":
				inseriVeiculos(veiculoDAO, sc);
				break;
			case "3":
				excluiVeiculo(veiculoDAO, sc);
				break;
			default:
				break;
		}
	}
	
	static void inseriVeiculos(VeiculoDAO veiculoDAO, Scanner sc) {
		System.out.println("\n\n==== Inserir veiculo === ");
		System.out.println("Insira a placa:");
		String placa = sc.nextLine();
		System.out.println("Insira a marca:");
		String marca = sc.nextLine();
		System.out.println("Insira o motor:");
		String motor = sc.nextLine();
		Veiculo veiculo = new Veiculo(3, placa, marca, motor);
		if(veiculoDAO.insert(veiculo) == true) {
			System.out.println("Inserção com sucesso -> " + veiculo.toString());
		}
	}
	static void listaVeiculos(VeiculoDAO veiculoDAO) {
		System.out.println("\n\n==== Lista veiculos === ");
		List<Veiculo> veiculos = veiculoDAO.get();
		for (Veiculo u: veiculos) {
			System.out.println(u.toString());
		}
	}
	static void excluiVeiculo(VeiculoDAO veiculoDAO, Scanner sc) {
		System.out.println("\n\n==== Excluir Veiculo ==== ");
		System.out.println("Insira o codigo do veiculo a ser excluido:");
		String codigo = sc.nextLine();
		veiculoDAO.delete(Integer.parseInt(codigo));
	}
}