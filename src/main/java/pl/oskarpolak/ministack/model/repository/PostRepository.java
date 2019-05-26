package pl.oskarpolak.ministack.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.ministack.model.entity.PostEntity;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Integer> {
    Iterable<PostEntity> findTop10ByOrderByIdDesc();

}
