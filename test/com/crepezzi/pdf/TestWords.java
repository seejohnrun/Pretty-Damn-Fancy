package com.crepezzi.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

public class TestWords extends TestCase {
	
	private static final String PASSWORD = PdfCrackerTest.PASSWORD;
	
	public void testList1() throws IOException {
		
		//Try to open a PDF using a list of strings that contain
		//the password, make sure we can open the file
		
		Collection<String> words = new ArrayList<String>();
		words.add("other");
		words.add(PdfCrackerTest.PASSWORD);
		
		assertEquals(PdfCrackerTest.cracker.crackViaWordlist(words), PASSWORD);
		
	}

	public void testList2() throws IOException {
		
		//Try to open a PDF using a list of strings that don't contain
		//the password, make sure we can't open the file
		
		Collection<String> words = new ArrayList<String>();
		words.add("other");
		words.add("other2");
		
		assertEquals(PdfCrackerTest.cracker.crackViaWordlist(words), null);
		
	}

}
