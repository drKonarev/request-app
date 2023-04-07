package ru.drKonarev.requestapp.request.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.drKonarev.requestapp.markers.Create;
import ru.drKonarev.requestapp.markers.Update;
import ru.drKonarev.requestapp.request.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class RequestDto {

    private Long id;
    @NotNull(groups = {Create.class, Update.class})
    private Long ownerId;

    @NotNull(groups = {Create.class, Update.class})
    @NotBlank(groups = {Create.class, Update.class})
    private String description;

    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
    private LocalDateTime created = LocalDateTime.now();

    @NotNull(groups = {Create.class, Update.class})
    @NotBlank(groups = {Create.class, Update.class})
    private Status status;
}
