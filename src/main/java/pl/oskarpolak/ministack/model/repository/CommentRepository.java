package pl.oskarpolak.ministack.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.ministack.model.entity.CommentEntity;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {

}
