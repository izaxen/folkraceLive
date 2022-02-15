package dsmi.folkracelive.repositories.driverClassRepository;

import dsmi.folkracelive.entities.driverClasses.SeniorClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeniorClassRepository extends JpaRepository<SeniorClass, Long> {
}
