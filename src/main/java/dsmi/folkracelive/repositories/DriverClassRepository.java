package dsmi.folkracelive.repositories;

import dsmi.folkracelive.entities.driverClasses.DriverClasses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  DriverClassRepository extends JpaRepository<DriverClasses, Long> {
}
