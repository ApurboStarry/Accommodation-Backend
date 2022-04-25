package inc.starry.accommodation.property;

import inc.starry.accommodation.propertyType.PropertyType;
import inc.starry.accommodation.propertyType.PropertyTypeRepository;
import inc.starry.accommodation.typeOfPlace.TypeOfPlace;
import inc.starry.accommodation.typeOfPlace.TypeOfPlaceRepository;
import inc.starry.accommodation.user.User;
import inc.starry.accommodation.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final PropertyTypeRepository propertyTypeRepository;
    private final TypeOfPlaceRepository typeOfPlaceRepository;
    private final UserRepository userRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository,
                           PropertyTypeRepository propertyTypeRepository,
                           TypeOfPlaceRepository typeOfPlaceRepository,
                           UserRepository userRepository) {
        this.propertyRepository = propertyRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.typeOfPlaceRepository = typeOfPlaceRepository;
        this.userRepository = userRepository;
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public ResponseEntity<?> getPropertyById(Long id) {
        Optional<Property> property = propertyRepository.findById(id);

        if(!property.isPresent()) {
            return ResponseEntity.status(404).body("No property with the given ID was found");
        }
        return ResponseEntity.status(200).body(property.get());
    }

    private boolean isValidPropertyTypeId(Long propertyTypeId) {
        Optional<PropertyType> propertyTypeOptional = propertyTypeRepository.findById(propertyTypeId);
        return propertyTypeOptional.isPresent();
    }

    private boolean isValidTypeOfPlaceId(Long typeOfPlaceId) {
        Optional<TypeOfPlace> typeOfPlace = typeOfPlaceRepository.findById(typeOfPlaceId);
        return typeOfPlace.isPresent();
    }

    private boolean isValidHostId(Long hostId) {
        Optional<User> user = userRepository.findById(hostId);
        return user.isPresent();
    }

    private boolean isValidProperty(Property property) {
        if(!isValidPropertyTypeId(property.getPropertyTypeId()) ||
            !isValidTypeOfPlaceId(property.getTypeOfPlaceId()) ||
            !isValidHostId(property.getHostId())) {
            return false;
        }
        return true;
    }

    public ResponseEntity<?> createNewProperty(Property property) {
        if(!isValidProperty(property)) {
            return ResponseEntity.status(400).body("Invalid property object");
        }

        propertyRepository.save(property);
        return ResponseEntity.status(201).body("");
    }

    public ResponseEntity<?> updateProperty(Property property) {
        Optional<Property> propertyOptional = propertyRepository.findById(property.getId());
        if(!propertyOptional.isPresent()) {
            return ResponseEntity.status(400).body("No property with the given ID was found");
        }
        if(!isValidProperty(property)) {
            return ResponseEntity.status(400).body("Invalid property object");
        }

        propertyRepository.save(property);
        return ResponseEntity.status(200).body("");
    }

    public ResponseEntity<?> deleteProperty(Long id) {
        propertyRepository.deleteById(id);
        return ResponseEntity.status(200).body("");
    }
}
