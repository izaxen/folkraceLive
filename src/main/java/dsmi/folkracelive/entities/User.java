package dsmi.folkracelive.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long Id;

    @Column(name = "club_name")
    private String clubName;

    private String role;
    @Column(name = "club_image")
    private String clubImage;

    private String password;

}
