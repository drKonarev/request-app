package ru.drKonarev.requestapp.request.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.drKonarev.requestapp.request.Status;
import ru.drKonarev.requestapp.request.model.Request;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query(value = "select * from requests as req, users as users " +
            "where req.owner_id=users.id and users.id=?1" +
            " and req.status like ?2 order by req.created_time DESC",
            countQuery = "select * from requests as req, users as users " +
                    "where req.owner_id=users.id and users.id=?1" +
                    " and req.status like ?2 order by req.created_time DESC ",
            nativeQuery = true)
    Page<Request> findAllRequestByOwner_IdAndStatus(Long ownerId, Status status, Pageable pageable);

    Page<Request> findAllRequestByOwner_IdAndStatus_OrderByCreated(Long ownerId, Status status, Pageable pageable);

    Page<Request> findAllRequestByStatus_OrderByCreated(Status status, Pageable pageable);

    Page<Request> findAllRequestsByOwner_Id_OrderByCreated(Long ownerId, Pageable pageable);

    Optional<Request> findRequestByOwner_IdAndId(Long ownerId, Long requestId);


}
