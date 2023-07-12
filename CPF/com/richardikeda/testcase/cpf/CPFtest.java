package com.richardikeda.testcase.cpf;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.richardikeda.cpf.CPF;

class CPFTest {

    @Test
    void testValidCPF() {
        // valid CPF 
        String cpfString = "359.072.950-31";
        CPF cpf = new CPF(cpfString);
        assertTrue(cpf.isValid());
    }

    @Test
    void testInvalidCPF() {
        // Invalid CPF 
        String cpfString = "11111111111";
        CPF cpf = new CPF(cpfString);
        assertFalse(cpf.isValid());
    }

    @Test
    void testFormattedCPF() {
        // mask CPF 
        String cpfString = "12345678900";
        CPF cpf = new CPF(cpfString);
        assertEquals("123.456.789-00", cpf.getFormattedCPF());
    }

    @Test
    void testDigitsOnly() {
        // unmask CPF
        String cpfString = "123.456.789-00";
        CPF cpf = new CPF(cpfString);
        assertEquals("12345678900", cpf.getDigitsOnly());
    }
    
    @Test
    void testNullCPF() {
        // null CPF 
        assertThrows(IllegalArgumentException.class, () -> new CPF(null));
    }

    @Test
    void testEmptyCPF() {
        // CPF vazio
        assertThrows(IllegalArgumentException.class, () -> new CPF(""));
    }

    @Test
    void testInvalidFormatCPF() {
        // CPF com formato inválido
        String cpfString = "12A.456.789-00";
        assertThrows(IllegalArgumentException.class, () -> new CPF(cpfString));
    }

    @Test
    void testInvalidDigitsCPF() {
        // CPF com dígitos inválidos
        String cpfString = "1234567890a";
        assertThrows(IllegalArgumentException.class, () -> new CPF(cpfString));
    }

  

}
