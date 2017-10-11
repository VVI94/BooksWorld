package models;

import exceptions.ValidationException;

public class Validators {

	public static void vlidateInputForNull(String type, Object object) throws ValidationException{
		
			if(object == null){
				throw new ValidationException("Invalid " + type + "!");
			}		
	}
		
}
