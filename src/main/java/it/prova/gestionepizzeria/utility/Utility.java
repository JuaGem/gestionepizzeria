package it.prova.gestionepizzeria.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class Utility {
	
	public static Date parseDateFromString(String dataDiNascitaStringParam) {
		if (StringUtils.isBlank(dataDiNascitaStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataDiNascitaStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static boolean checkIntegerPositive(String intero) {
		if(!checkInteger(intero) || Integer.parseInt(intero) < 0)
			return false;
		
	  return true;
	}
	
	public static boolean checkInteger(String intero) {
		if(!NumberUtils.isCreatable(intero) || NumberUtils.toInt(intero, -1) == -1)
			return false;
		
	 return true;
	}
	
	public static Date parseDateWithTimeFromString(String dataDiNascitaStringParam) {
		if (StringUtils.isBlank(dataDiNascitaStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataDiNascitaStringParam);
		} catch (ParseException e) {
			return null;
		}
	}

}
