package dsmi.folkracelive.entities.driverClasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "junior_class")
@Data
@AllArgsConstructor
@Builder
public class JuniorClass extends DriverClasses{

}
