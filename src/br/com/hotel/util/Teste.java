package br.com.hotel.util;

/**
 * Apenas um teste
 * @author Tomaz Lavieri
 */
public class Teste {
    
	public static void main(String[] args) {
		//Faz a busca para o cep 58043-280
		WebServiceCep webServiceCep = WebServiceCep.searchCep("54330-130");
		//A ferramenta de busca ignora qualquer caracter que não seja número.
		
		//caso a busca ocorra bem, imprime os resultados.
		if (webServiceCep.wasSuccessful()) {
			System.out.println("Cep: "+webServiceCep.getCep());
			System.out.println("Logradouro: "+webServiceCep.getLogradouroFull());
			System.out.println("Bairro: "+webServiceCep.getBairro());
			System.out.println("Cidade: "+
					webServiceCep.getCidade()+"/"+ webServiceCep.getUf());
		//caso haja problemas imprime as exceções.
		} else {
			System.out.println("Erro número: " + webServiceCep.getResulCode());
			System.out.println("Descrição do erro: " + webServiceCep.getResultText());
		}
		
		System.out.println(ValidaCNPJ.isCNPJ("14.572.457/0001-85"));
			
	}   
}
