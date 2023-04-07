package ru.drKonarev.requestapp.request;

import org.springframework.data.domain.Sort;

import java.util.List;

public interface RequestService {

    RequestDto save(RequestDto requestDto, Long ownerId);

    RequestDto updateRequest(RequestDto requestDto, Long requestId, Long ownerId); // и статус, и текст заявки

    List<RequestDto> getAllRequestsByOwnerId(Long ownerId, Sort sort);

    RequestOperatorDto getRequestById(Long requestId);

    RequestOperatorDto changeStatusRequestByOperator(Long requestId, Boolean status);
    RequestDto changeStatusRequestByUser(Long requestId, Long ownerId, Boolean status);

    List<RequestDto> searchRequestByName(String text, Sort sort);


}
