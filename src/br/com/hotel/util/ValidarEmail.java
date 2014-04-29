package br.com.hotel.util;


import java.util.regex.*;  

public class ValidarEmail {
	 
	   public static boolean validarEmail(String email) {  
	      //Input the string for validation 
		   boolean emailvalido = false;
		   
		   if (email != null && email.length() > 0) {
			   String expression = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,4}$";
			   Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			   Matcher matcher = pattern.matcher(email);
			   
			   if (matcher.matches()) {
				emailvalido = true;
			}
			   
		   }
		   
		   return emailvalido;
//	      String email = "xyz@hotmail.com";  
//	  
//	      //Set the email pattern string  
//	      Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,3}$");  
//	      
//	      //Match the given string with the pattern  
//	      Matcher m = p.matcher(email);  
//	  
//	      //check whether match is found   
//	      boolean matchFound = m.matches();  
//	  
//	      if (matchFound)  
//	        System.out.println("Valid Email Id.");  
//	      else  
//	        System.out.println("Invalid Email Id.");  
	   }  
	
}
