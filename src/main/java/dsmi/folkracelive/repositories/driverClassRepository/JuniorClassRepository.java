package dsmi.folkracelive.repositories.driverClassRepository;

import dsmi.folkracelive.entities.driverClasses.JuniorClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuniorClassRepository extends JpaRepository<JuniorClass, Long> {
}
