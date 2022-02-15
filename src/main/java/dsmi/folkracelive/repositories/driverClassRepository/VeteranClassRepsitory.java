package dsmi.folkracelive.repositories.driverClassRepository;

import dsmi.folkracelive.entities.driverClasses.VeteranClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeteranClassRepsitory extends JpaRepository<VeteranClass, Long> {
}
