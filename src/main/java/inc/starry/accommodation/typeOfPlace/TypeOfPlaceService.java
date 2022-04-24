package inc.starry.accommodation.typeOfPlace;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeOfPlaceService {
    private final TypeOfPlaceRepository typeOfPlaceRepository;

    public TypeOfPlaceService(TypeOfPlaceRepository typeOfPlaceRepository) {
        this.typeOfPlaceRepository = typeOfPlaceRepository;
    }

    public List<TypeOfPlace> getAllTypesOfPlaces() {
        return typeOfPlaceRepository.findAll();
    }

    public ResponseEntity<?> getTypeOfPlaceById(Long id) {
        Optional<TypeOfPlace> typeOfPlace =
                typeOfPlaceRepository.findById(id);
        if(!typeOfPlace.isPresent()) {
            return ResponseEntity.status(404).body("No type of place with the given ID was found");
        }
        return ResponseEntity.status(200).body(typeOfPlace.get());
    }

    public ResponseEntity<?> createNewTypeOfPlace(TypeOfPlace typeOfPlace) {
        Optional<TypeOfPlace> typeOfPlaceOptional = typeOfPlaceRepository.findByName(typeOfPlace.getName());
        if(typeOfPlaceOptional.isPresent()) {
            return ResponseEntity.status(400).body("A type of place with the same name already exists");
        }

        typeOfPlaceRepository.save(typeOfPlace);
        return ResponseEntity.status(200).body("");
    }

    public ResponseEntity<?> updateTypeOfPlace(TypeOfPlace typeOfPlace) {
        Optional<TypeOfPlace> typeOfPlaceOptional = typeOfPlaceRepository.findById(typeOfPlace.getId());
        if(!typeOfPlaceOptional.isPresent()) {
            return ResponseEntity.status(404).body("No type of place with the given ID was found");
        }

        typeOfPlaceRepository.save(typeOfPlace);
        return ResponseEntity.status(200).body("");
    }

    public ResponseEntity<?> deleteTypeOfPlace(Long id) {
        Optional<TypeOfPlace> typeOfPlaceOptional = typeOfPlaceRepository.findById(id);
        if(!typeOfPlaceOptional.isPresent()) {
            return ResponseEntity.status(404).body("No type of place with the given ID was found");
        }

        typeOfPlaceRepository.deleteById(id);
        return ResponseEntity.status(200).body("");
    }
}
