package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Integer> {
    // Optional<Topic> findByPostsId(final Integer id);
}