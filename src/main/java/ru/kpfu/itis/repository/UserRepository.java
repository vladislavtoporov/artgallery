package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
