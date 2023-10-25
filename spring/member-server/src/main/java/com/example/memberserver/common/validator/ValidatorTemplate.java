package com.example.memberserver.common.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ValidatorTemplate<T> {
    public void execute(T dto){
        validateVariable(dto);

        validateIntegrity(dto);
    }

    public abstract void validateVariable(T dto);

    public abstract void validateIntegrity(T dto);

    protected void validateEmailFormat(String email) {
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new RuntimeException();
        }
    }

    protected void validatePasswordFormat(String password) {
        String passwordPattern = "^(.*[a-zA-Z0-9].*){2,}.*[^a-zA-Z0-9\\s].*$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            throw new RuntimeException();
        }
    }
}
