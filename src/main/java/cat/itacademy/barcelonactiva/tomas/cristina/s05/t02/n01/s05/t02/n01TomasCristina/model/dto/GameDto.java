package cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private int id;
    private int dice1;
    private int dice2;
    private boolean won;
    private int playerId;
}
