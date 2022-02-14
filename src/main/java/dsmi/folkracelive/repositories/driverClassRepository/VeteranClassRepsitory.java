package dsmi.folkracelive.repositories.driverClassRepository;

import dsmi.folkracelive.entities.driverClasses.VeteranClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Vector;

public interface VeteranClassRepsitory extends JpaRepository<VeteranClass, Long> {
}
