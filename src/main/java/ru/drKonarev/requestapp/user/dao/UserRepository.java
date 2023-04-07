package ru.drKonarev.requestapp.user.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.drKonarev.requestapp.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users as u where lower(u.name)" +
            " LIKE  lower(%?1%) order by u.name DESC",
            countQuery = "select * from users as u where lower(u.name)" +
                    " LIKE  lower(%?1%) order by u.name DESC",
            nativeQuery = true)
    Page<User> findByName(String name, Pageable pageable);

}
