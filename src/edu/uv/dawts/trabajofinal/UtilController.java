package edu.uv.dawts.trabajofinal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.reflect.FieldUtils;


/**
 * @author jnicolau
 *
 */
public final class UtilController {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.forLanguageTag("es-ES"));
	/**
	 * 
	 */
	private UtilController() {
		super();
	}

	public static <T> T obtenerBeanDeParametros(HttpServletRequest request, Class<T> clazz){
		if (clazz != null) {
			try {
				final Constructor<T> contructor = clazz.getConstructor();
				contructor.setAccessible(true);
				final T t = contructor.newInstance();
				final List<Field> allFields = FieldUtils.getAllFieldsList(clazz);
				for (final Field field : allFields) {
					field.setAccessible(true);
					setField(t, request, field);
				}
				return t;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	private static <T> void setField(T t,HttpServletRequest request, Field field) throws NumberFormatException, IllegalArgumentException, IllegalAccessException, ParseException {
		String value = request.getParameter(field.getName());
		if (value != null) {
			if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
				field.set(t, Integer.valueOf(value));
			}else if (field.getType().equals(Long.class) || field.getType().equals(long.class)){
				field.set(t, Long.valueOf(value));
			}else if (field.getType().equals(Double.class) || field.getType().equals(double.class)){
				field.set(t, Double.valueOf(value));
			}else if (field.getType().equals(Float.class) || field.getType().equals(float.class)){
				field.set(t, Float.valueOf(value));
			}else if (field.getType().equals(String.class)){
				field.set(t, value);
			}else if (field.getType().equals(char.class) || field.getType().equals(Character.class)){
				field.set(t, value.charAt(0));
			}else if(field.getType().equals(Date.class)) {				
				field.set(t, DATE_FORMAT.parse(value));
			}else {
				field.set(t, field.getType().cast(obtenerBeanDeParametros(request, field.getType())));
			}
		}
	}
	
	public static String getFechaYYYY_MM_DD(Date fecha) {
		return DATE_FORMAT.format(fecha);
	}

	public static String semicolonFormat(String... tareasIdList) {
		final StringBuilder idJoiner = new StringBuilder(";");
		for (final String idTarea : tareasIdList) {
			idJoiner.append(idTarea).append(';');
		}
		return idJoiner.toString();
	}
}