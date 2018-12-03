package com.urise.webapp.model;

import java.util.Objects;

public class TextFieldSection extends Section {

    private final String textField;

    public TextFieldSection(String textField) {
        this.textField = textField;
    }

    public String getTextField() {
        return textField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextFieldSection that = (TextFieldSection) o;
        return Objects.equals(textField, that.textField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textField);
    }

}
