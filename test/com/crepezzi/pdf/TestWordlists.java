package com.crepezzi.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

public class TestWordlists extends TestCase {
	
	private static final String PASSWORD = PdfCrackerTest.PASSWORD;
	private static final String SUCCESS_WORDLIST = "data/wordlists/success", 
								FAILURE_WORDLIST = "data/wordlists/failure";
	
	public void testFile1() throws IOException {
		
		//Try to open a PDF using a wordlist that contains
		//the password, make sure we can open the file
		
		assertEquals(PdfCrackerTest.cracker.crackViaWordlistFile(SUCCESS_WORDLIST), PASSWORD);
		
	}
	
	public void testFile2() throws IOException {
		
		//Try to open a PDF using a wordlist that doesn't contain
		//the password, make sure we can't open the file
		
		assertEquals(PdfCrackerTest.cracker.crackViaWordlistFile(FAILURE_WORDLIST), null);
		
	}

    public void testFile3() throws IOException {
		
		//Try to open a PDF using a set of wordlists that contain
		//the password, make sure we can open the file (via convenience method)
		
		Collection<String> lists = new ArrayList<String>();
		lists.add(FAILURE_WORDLIST); //put this one first
		lists.add(SUCCESS_WORDLIST);

		assertEquals(PdfCrackerTest.cracker.crackViaWordlistFiles(lists), PASSWORD);
		
	}

    public void testFile4() throws IOException {
		
		//Try to open a PDF using a set of wordlists that don't contain
		//the password, make sure we can't open the file (via convenience method)
		
		Collection<String> lists = new ArrayList<String>();
		lists.add(FAILURE_WORDLIST);
		
		assertEquals(PdfCrackerTest.cracker.crackViaWordlistFiles(lists), null);
		
	}
    
}
