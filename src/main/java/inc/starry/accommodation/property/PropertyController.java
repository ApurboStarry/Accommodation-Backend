package inc.starry.accommodation.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/property")
public class PropertyController {
    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping()
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPropertyById(@PathVariable String id) {
        try {
            return propertyService.getPropertyById(Long.parseLong(id));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body("Invalid ID");
        }
    }

    @PostMapping()
    public ResponseEntity<?> createNewProperty(@RequestBody Property property) {
        return propertyService.createNewProperty(property);
    }

    @PutMapping()
    public ResponseEntity<?> updateProperty(@RequestBody Property property) {
        return propertyService.updateProperty(property);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable String id) {
        try {
            return propertyService.deleteProperty(Long.parseLong(id));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body("Invalid ID");
        }
    }
}
