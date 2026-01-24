package com.github.willcq97.scpods.api.dto.select;

/**
 * Um modelo representando uma opção selecionável com um valor e descrição. Utilizado em dropdowns, botões de rádio, etc.
 * 
 * @param <T> o tipo do valor da opção selecionável
 */
public interface SelectModel<T> {

    T getValue();

    void setValue( T value );

    String getDescription();

    void setDescription( String description );

}
