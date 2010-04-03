package com.crepezzi.pdf;

import java.io.IOException;

import junit.framework.TestCase;

public class TestBruteForce extends TestCase {

	private static final String PASSWORD = PdfCrackerTest.PASSWORD;
	
	public void testBruteForce1() throws IOException {
		
		//Try a successful brute force attack with an alphabet,
		//trying all lengths
		
		assertEquals(PdfCrackerTest.cracker.crackViaBruteForce(PASSWORD), PASSWORD);
		
	}

	public void testBruteForce2() throws IOException {
		
		//Try an unsuccessful brute force attack with an alphabet,
		//trying all lengths
		
		assertEquals(PdfCrackerTest.cracker.crackViaBruteForce("def"), null);
		
	}
	
	public void testBruteForce3() throws IOException {
	
		//Try a successful brute force attack with an alphabet,
		//trying only the exact length of the password
		
		assertEquals(PdfCrackerTest.cracker.crackViaBruteForce(PASSWORD, PASSWORD.length()), PASSWORD);
		
	}	
	
	public void testBruteForce4() throws IOException {
	
		//Try an unsuccessful brute force attack with an alphabet,
		//trying only the exact length of the password
		
		assertEquals(PdfCrackerTest.cracker.crackViaBruteForce("def", 3), null);
		
	}

	public void testBruteForce5() throws IOException {
		
		//Try a successful brute force attack with an alphabet,
		//trying only the exact 2 lengths
		
		int pwLength = PASSWORD.length();
		assertEquals(PdfCrackerTest.cracker.crackViaBruteForce(PASSWORD, pwLength - 1, pwLength), PASSWORD);
		
	}	
	
	public void testBruteForce6() throws IOException {
		
		//Try an unsuccessful brute force attack with an alphabet,
		//trying only the exact 2 lengths
		
		assertEquals(PdfCrackerTest.cracker.crackViaBruteForce(PASSWORD, 1, 2), null);
		
	}
		
}
