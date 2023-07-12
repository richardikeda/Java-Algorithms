package com.richardikeda.cpf;
/*
 * MIT License
 * 
 * Copyright (c) 2023 Richard Ikeda
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * CPF - Classe de controle para validação de CPF.
 *
 * Esta classe permite validar um CPF e fornecer metodos para obter o CPF formatado
 * e obter apenas os digitos do CPF.
 *
 * @author Richard Ikeda
 * @git https://github.com/richardikeda
 * @since 2023
 */

public class CPF {


	private String cpf;

	/**
	 * Cria uma instancia de CPF com o valor fornecido.
	 *
	 * @param cpf o CPF a ser validado e associado a instancia
	 * @throws IllegalArgumentException se o CPF fornecido for invalido
	 */
	public CPF(String cpf) {
		if (cpf == null) {
		    throw new IllegalArgumentException("CPF não pode ser nulo");
		}
		this.cpf = sanitize(cpf);
	}

	/**
	 * Remove caracteres nao numericos do CPF e valida seu formato.
	 *
	 * @param cpf o CPF a ser sanitizado
	 * @return o CPF sanitizado
	 * @throws IllegalArgumentException se o CPF fornecido for invalido
	 */
	private String sanitize(String cpf) {
		cpf = cpf.replaceAll("\\D", ""); // Remove caracteres nao numericos
		if (cpf.length() != 11 || !cpf.matches("\\d+")) {
			throw new IllegalArgumentException("CPF invalido");
		}
		return cpf;
	}

	/**
	 * Verifica se o CPF e valido.
	 *
	 * @return boolean valido, invalido
	 */
	public boolean isValid() {
		String cpf = this.cpf;

		// Verifica se todos os digitos sao iguais
		if (cpf.chars().distinct().count() == 1) {
			return false;
		}

		// Calcula o primeiro digito verificador
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (10 - i);
		}

		int primeiroDigito = (soma * 10) % 11;
		if (primeiroDigito == 10) {
			primeiroDigito = 0;
		}

		// Calcula o segundo digito verificador
		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (11 - i);
		}
		int segundoDigito = (soma * 10) % 11;
		if (segundoDigito == 10) {
			segundoDigito = 0;
		}

		// Verifica se os digitos verificadores estao corretos
		return primeiroDigito == Integer.parseInt(String.valueOf(cpf.charAt(9)))
				&& segundoDigito == Integer.parseInt(String.valueOf(cpf.charAt(10)));
	}
	
	/**
	 * CPF formatado
	 * @return string
	 */
	public String getFormattedCPF() {
		String cpf = this.cpf;
		return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
	}
	/**
	 * Digitos CPF
	 * @return string
	 */
	public String getDigitsOnly() {
		return this.cpf;
	}
}
