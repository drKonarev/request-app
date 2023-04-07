package ru.drKonarev.requestapp.request.service;

import org.springframework.stereotype.Component;
import ru.drKonarev.requestapp.request.dto.RequestDto;
import ru.drKonarev.requestapp.request.dto.RequestOperatorDto;
import ru.drKonarev.requestapp.request.model.Request;
import ru.drKonarev.requestapp.user.model.User;

@Component
public class RequestMapper {

    RequestDto toDto(Request request) {
        return new RequestDto(request.getId(),
                request.getOwner().getId(),
                request.getDescription(),
                request.getCreated(),
                request.getStatus());
    }

    RequestOperatorDto toOperator(Request request) {
        return new RequestOperatorDto(request.getId(),
                descToDesc(request.getDescription()),
                request.getCreated());
    }

    String descToDesc(String desc) {
        String result = "";
        for (int i = 0; i < desc.length(); i++) {
            result += desc.charAt(i) + "-";
        }
        return result;
    }

    Request toRequest(RequestDto requestDto, User owner) {
        return new Request(null,
                requestDto.getDescription(),
                owner,
                requestDto.getCreated(),
                requestDto.getStatus());
    }

    Request patch(Request request, RequestDto requestDto) {
        return new Request(request.getId(),
                requestDto.getDescription(),
                request.getOwner(),
                requestDto.getCreated(),
                request.getStatus());
    }
}
