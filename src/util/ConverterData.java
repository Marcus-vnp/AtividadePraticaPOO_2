package util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class ConverterData {
	
	public static String converter(String data) {
		String[] partes = data.split("/");
		return partes[2] + "-" + partes[1] + "-" + partes[0];
	}
	
	public static String retroceder(String data) {
	    String[] partes = data.split("-");

	    return partes[2] + "/" + partes[1] + "/" + partes[0];
	}
	
	public static boolean verificar(String data){
		if (data.length() != 10) return false;
	    
		String[] partes = data.split("/");
		
		if (partes.length != 3) return false;
		
		if (partes[0].length() != 2 || partes[1].length() != 2 || partes[2].length() != 4) return false;
		
		return true;
	}
	
	public static boolean verificarAntecedencia(String dataInicio, String dataFinal) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate dtInicio = LocalDate.parse(dataInicio, formato);
		LocalDate dtFinal = LocalDate.parse(dataFinal, formato);
		
		if (dtInicio.isAfter(dtFinal)) return false;
		return true;
	}
}
