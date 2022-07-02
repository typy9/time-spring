package com.parpiiev.time.annotations;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;


public class PasswordConstraintValidator
        implements ConstraintValidator<ValidPassword, String> {

    private final PasswordValidator validator = new PasswordValidator(Arrays.asList(

            new LengthRule(1, 8),

//                new CharacterRule(EnglishCharacterData.UpperCase, 1),

            new CharacterRule(EnglishCharacterData.LowerCase, 1),

            new CharacterRule(EnglishCharacterData.Digit, 1),

//                new CharacterRule(EnglishCharacterData.Special, 1),

            new WhitespaceRule()

    ));

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }

        List<String> messages = validator.getMessages(result);

        String messageTemplate = String.join(",", messages);

        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}