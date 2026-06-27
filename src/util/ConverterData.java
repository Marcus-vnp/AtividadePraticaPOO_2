package util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Matcher matcher = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$").matcher(data);		
		
		if (!matcher.find()) return false;
		
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
