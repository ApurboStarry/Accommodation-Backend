package inc.starry.accommodation.typeOfPlace;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/typeOfPlace")
public class TypeOfPlaceController {
    private final TypeOfPlaceService typeOfPlaceService;

    public TypeOfPlaceController(TypeOfPlaceService typeOfPlaceService) {
        this.typeOfPlaceService = typeOfPlaceService;
    }

    @GetMapping()
    public List<TypeOfPlace> getAllTypesOfPlaces() {
        return typeOfPlaceService.getAllTypesOfPlaces();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTypeOfPlaceById(@PathVariable String id) {
        try {
            return typeOfPlaceService.getTypeOfPlaceById(Long.parseLong(id));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body("Invalid ID");
        }

    }

    @PostMapping()
    public ResponseEntity<?> createNewTypeOfPlace(@RequestBody TypeOfPlace typeOfPlace) {
        return typeOfPlaceService.createNewTypeOfPlace(typeOfPlace);
    }

    @PutMapping()
    public ResponseEntity<?> updateTypeOfPlace(@RequestBody TypeOfPlace typeOfPlace) {
        return typeOfPlaceService.updateTypeOfPlace(typeOfPlace);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTypeOfPlace(@PathVariable String id) {
        try {
            return typeOfPlaceService.deleteTypeOfPlace(Long.parseLong(id));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body("Invalid ID");
        }
    }
}
