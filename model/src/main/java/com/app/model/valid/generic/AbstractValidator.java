package com.app.model.valid.generic;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractValidator<T> implements Validator<T> {

    protected Map<String, String> errors = new HashMap<>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
