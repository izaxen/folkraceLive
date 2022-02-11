package dsmi.folkracelive.entities.driverClasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "senior_class")
@Data
@AllArgsConstructor
@Builder
public class SeniorClass extends DriverClasses{
}
