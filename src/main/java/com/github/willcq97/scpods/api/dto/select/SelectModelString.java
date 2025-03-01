package com.github.willcq97.scpods.api.dto.select;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SelectModelString implements SelectModel<String> {

    private String value;
    private String description;

}
