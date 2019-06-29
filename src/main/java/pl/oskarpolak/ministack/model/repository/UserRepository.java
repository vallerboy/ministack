package pl.oskarpolak.ministack.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pl.oskarpolak.ministack.model.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email); //zapytania czytane sa po nazwach metod
    boolean existsByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT (CASE WHEN COUNT(*) > 0 THEN 'TRUE' ELSE 'FALSE' END) FROM `user` WHERE `id` = ?1")
    boolean isUserExistById(int id);
}
