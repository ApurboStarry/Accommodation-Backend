package inc.starry.accommodation.propertyType;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyTypeService {
    private PropertyTypeRepository propertyTypeRepository;

    @Autowired
    public PropertyTypeService(PropertyTypeRepository propertyTypeRepository) {
        this.propertyTypeRepository = propertyTypeRepository;
    }

    public List<PropertyType> getAllPropertyTypes() {
        return propertyTypeRepository.findAll();
    }

    public ResponseEntity<?> getPropertyTypeById(Long id) {
        Optional<PropertyType> propertyType = propertyTypeRepository.findById(id);

        if(!propertyType.isPresent()) {
            return ResponseEntity.status(404).body("No property type with the given ID was found");
        }
        return ResponseEntity.status(200).body(propertyType.get());
    }

    public ResponseEntity<?> createNewPropertyType(PropertyType propertyType) {
        Optional<PropertyType> propertyTypeOptional = propertyTypeRepository.findByName(propertyType.getName());

        if(propertyTypeOptional.isPresent()) {
            return ResponseEntity.status(400).body("A property type with the same name already exists");
        }

        propertyTypeRepository.save(propertyType);
        return ResponseEntity.status(200).body("");
    }

    public ResponseEntity<?> updatePropertyType(PropertyType propertyType) {
        Optional<PropertyType> propertyTypeOptional = propertyTypeRepository.findById(propertyType.getId());

        if(!propertyTypeOptional.isPresent()) {
            return ResponseEntity.status(404).body("No property type with the given ID was found");
        }

        propertyTypeRepository.save(propertyType);
        return ResponseEntity.status(200).body("");
    }

    public ResponseEntity<?> deletePropertyType(Long id) {
        Optional<PropertyType> propertyTypeOptional = propertyTypeRepository.findById(id);
        if(!propertyTypeOptional.isPresent()) {
            return ResponseEntity.status(404).body("No property type with the given ID was found");
        }
        return ResponseEntity.status(200).body("");
    }
}
