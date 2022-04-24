package inc.starry.accommodation.propertyType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/propertyType")
public class PropertyTypeController {
    private final PropertyTypeService propertyTypeService;

    @Autowired
    public PropertyTypeController(PropertyTypeService propertyTypeService) {
        this.propertyTypeService = propertyTypeService;
    }

    @GetMapping()
    public List<PropertyType> getAllPropertyTypes() {
        return propertyTypeService.getAllPropertyTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPropertyTypeById(@PathVariable String id) {
        try {
            return propertyTypeService.getPropertyTypeById(Long.parseLong(id));
        } catch(Exception ex) {
            return ResponseEntity.status(400).body("Invalid ID");
        }
    }

    @PostMapping
    public ResponseEntity<?> createNewPropertyType(@RequestBody PropertyType propertyType) {
        return propertyTypeService.createNewPropertyType(propertyType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePropertyType(@PathVariable String id) {
        try {
            return propertyTypeService.deletePropertyType(Long.parseLong(id));
        } catch(Exception ex) {
            return ResponseEntity.status(400).body("Invalid ID");
        }
    }
}
