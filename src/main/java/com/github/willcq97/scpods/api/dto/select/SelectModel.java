package com.github.willcq97.scpods.api.dto.select;

public interface SelectModel<T> {

    T getValue();

    void setValue( T value );

    String getDescription();

    void setDescription( String description );

}
