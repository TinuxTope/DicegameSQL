package cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerEntity player;

    private int dice1;
    private int dice2;
    private boolean won;

    @CreationTimestamp
    private Date playedAt;
}
