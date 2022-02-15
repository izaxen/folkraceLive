package dsmi.folkracelive.repositories.driverClassRepository;

import dsmi.folkracelive.entities.driverClasses.LadyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LadyClassRepository extends JpaRepository<LadyClass, Long> {
}
