package cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.dto.login;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
public class LoginResponse {
    private String token;

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

}
