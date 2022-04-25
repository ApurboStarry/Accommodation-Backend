package inc.starry.accommodation.property;

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
public class Property {
    @Id
    @SequenceGenerator(
            name = "property_sequence",
            sequenceName = "property_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "property_sequence"
    )
    private long id;
    private String name;
    private long propertyTypeId;
    private long typeOfPlaceId;
    private long hostId;
    private int numberOfBeds;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private float pricePerNight;
    private boolean isReserved;
    private float latitude;
    private float longitude;
}
