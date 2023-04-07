package ru.drKonarev.requestapp.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.drKonarev.requestapp.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "description")
    private String description;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;


    @Column(name = "created_time")
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private LocalDateTime created;

    @Enumerated(EnumType.STRING)
    private Status status;
}