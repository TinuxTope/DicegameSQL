package cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.dto;

import lombok.*;
import org.springframework.stereotype.Service;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private int id;
    private String email;
    private String name;
    private String password;
    private int totalRolls;
    private int wonRolls;
    private double successRate;

    public double getSuccessRate() {
        if (totalRolls == 0) {
            return 0.0;
        }
        return (double) wonRolls / totalRolls * 100;
    }
}
