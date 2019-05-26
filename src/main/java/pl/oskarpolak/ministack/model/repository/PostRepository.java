package pl.oskarpolak.ministack.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.ministack.model.entity.PostEntity;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM post ORDER BY id DESC LIMIT 10")
    Iterable<PostEntity> findTop10ByOrderByIdDesc();

    @Query(nativeQuery = true, value = "SELECT * FROM post WHERE user_id = ?1")
    List<PostEntity> findAllUserPost(int userId);
}
