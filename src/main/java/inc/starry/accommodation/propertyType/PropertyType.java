package inc.starry.accommodation.propertyType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PropertyType {
    @Id
    @SequenceGenerator(
            name = "propertyType_sequence",
            sequenceName = "propertyType_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "propertyType_sequence"
    )
    private long id;
    private String name;
    private String description;
}
