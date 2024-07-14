package cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.dto.login;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LoginResponse {
    private String token;

    private long expiresIn;

}
