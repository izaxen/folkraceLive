package dsmi.folkracelive.repositories;

import dsmi.folkracelive.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByClubName(String clubName);
}
