package com.ibrahimbdj.passwd_generation;

import java.security.SecureRandom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PasswdGenerationApplication {
	
	@RestController
	public class PasswdGeneratorService {
	
		private static final String MAJ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    private static final String MIN = "abcdefghijklmnopqrstuvwxyz";
	    private static final String NUM = "0123456789";
	    private static final String SYMB = "!@#$%^&*()_-+=";
	    private static final String TOT = MAJ + MIN + NUM + SYMB;
		
		@GetMapping("/")
		public String home() {
			return "GET /generate: génère le password sécurisé<br>";
		}
		
		@GetMapping("/generate")
		public String passwdGenerator() {
			StringBuilder pwd = new StringBuilder();
			SecureRandom r = new SecureRandom();
			for(int i=0;i<2;i++) {
				pwd.append(MAJ.charAt(r.nextInt(MAJ.length())));
		        pwd.append(MIN.charAt(r.nextInt(MIN.length())));
		        pwd.append(NUM.charAt(r.nextInt(NUM.length())));
		        pwd.append(SYMB.charAt(r.nextInt(SYMB.length())));
			}
			
	        for(int i=0; i<4; i++) {
	        	pwd.append(TOT.charAt(r.nextInt(TOT.length())));
	        }
	        
	        return melange(pwd.toString(), r);
		}
		
		private static String melange(String s, SecureRandom r) {
	        char[] chars = s.toCharArray();
	        for (int i = chars.length - 1; i > 0; i--) {
	            int index = r.nextInt(i + 1);
	            char temp = chars[i];
	            chars[i] = chars[index];
	            chars[index] = temp;
	        }
	        return new String(chars);
	    }
		
	}

	public static void main(String[] args) {
		SpringApplication.run(PasswdGenerationApplication.class, args);
	}

}
