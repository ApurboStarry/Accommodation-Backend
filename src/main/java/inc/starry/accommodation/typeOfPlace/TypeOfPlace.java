package inc.starry.accommodation.typeOfPlace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TypeOfPlace {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
}
