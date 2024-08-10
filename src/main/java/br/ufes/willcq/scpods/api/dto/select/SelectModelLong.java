package br.ufes.willcq.scpods.api.dto.select;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SelectModelLong implements SelectModel<Long> {

    private Long value;
    private String description;

}
