package ad.tech.databe.cursor.repository;

import ad.tech.databe.cursor.entity.RandomData;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface RandomDataRepository extends JpaRepository<RandomData, Long> {

}
