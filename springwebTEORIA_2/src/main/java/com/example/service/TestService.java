package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

	public String salutoUser(int orario) {
		String saluto;
		
		if(orario>=7 && orario <13) {
			saluto="Buongiorno";
		}else if(orario>=13 && orario<=19) {
			saluto="Buon pomeriggio";
		}else if(orario>=20 && orario<=22) {
			saluto="Buonasera";
		}else {
			saluto="Buonanotte";
		}
	
		return saluto;
	}
	
	
}
