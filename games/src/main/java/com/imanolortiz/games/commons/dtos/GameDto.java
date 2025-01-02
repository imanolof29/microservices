package com.imanolortiz.games.commons.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GameDto {

    private Long id;

    private String name;

    private String userId;

}
