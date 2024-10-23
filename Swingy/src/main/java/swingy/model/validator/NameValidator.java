package swingy.model.validator;

import swingy.model.validator.ValidName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NameValidator implements ConstraintValidator<ValidName, String> {

	@Override
	public void initialize(ValidName constraintAnnotation) {
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context)
	{
		if (name == null || name.length() == 0)
		{
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Error: Name must not be empty").addConstraintViolation();
			return (false);
		}
		if (name.trim().length() == 0)
		{
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Error: Name cannot be whitespace only").addConstraintViolation();
			return (false);
		}

		String forbiddenCharacters = "\"'\\/;:\t";
		for (char c : forbiddenCharacters.toCharArray())
		{
			if (name.indexOf(c) != -1)
			{
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Error: Name contains a forbidden character (\" ' \\ / ; : tab)").addConstraintViolation();
				return (false);
			}
		}

		if (name.length() > 20)
		{
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Error: Name length cannot exceed 20").addConstraintViolation();
			return (false);
		}

		return (true);
	}
}
