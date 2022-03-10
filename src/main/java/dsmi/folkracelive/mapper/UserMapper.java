package dsmi.folkracelive.mapper;

import dsmi.folkracelive.dto.models.UserNoPwDTO;
import dsmi.folkracelive.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserNoPwDTO UserNoPwDTO(User user){
        if(user==null)return null;

    return UserNoPwDTO.builder()
            .Id(user.getId())
            .clubName(user.getClubName())
            .clubImage(user.getClubImage())
            .email(user.getEmail())
            .build();

    }
}
