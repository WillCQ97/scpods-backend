package com.github.willcq97.scpods.api.dto.select;

// todo: escrever em português
// A model representing a selectable option with a value and description.
// Used in dropdowns, radio buttons, etc.
public interface SelectModel<T> {

    T getValue();

    void setValue( T value );

    String getDescription();

    void setDescription( String description );

}
