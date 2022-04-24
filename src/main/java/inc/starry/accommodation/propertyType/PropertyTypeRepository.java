package inc.starry.accommodation.propertyType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyTypeRepository extends JpaRepository<PropertyType, Long> {
    Optional<PropertyType> findByName(String name);
}
