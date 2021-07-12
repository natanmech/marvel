package br.com.marvel.loja.cliente;

import java.time.LocalDate;


import org.springframework.stereotype.Service;

@Service
public class PromoService {

	
	public char checkIsbn(String isbn) {
		char fimIsbn = isbn.charAt(isbn.length() - 1);
		if(fimIsbn == 1) {
			System.out.println("FIm do isbn é 1");
		}
		return fimIsbn;
		
	}

	public Boolean checkDiaPromo(String isbn) {
		String data = LocalDate.now().getDayOfWeek().toString();
		char c = checkIsbn(isbn);
		data = "MONDAY";
		switch (data) {
		case "MONDAY":
			if(c == 0 || c==1) {
				return true;
			}
			return false;	
		case "TUESDAY":
			if(c == 2 || c == 3) {
				return true;
			}
			return false;	
		case "WEDNESDAY":
			if(c == 4 || c == 5) {
				return true;
			}
			return false;	
		case "THURSDAY":
			if(c == 6 || c == 7) {
				return true;
			}
			return false;	
		case "FRIDAY":
			if(c == 8 || c == 9) {
				return true;
			}
			return false;		
	}
		return false;
	}
	
}
