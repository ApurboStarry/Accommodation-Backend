package inc.starry.accommodation.typeOfPlace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeOfPlaceRepository extends JpaRepository<TypeOfPlace, Long> {
    Optional<TypeOfPlace> findByName(String name);
}
