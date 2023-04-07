package ru.drKonarev.requestapp.request;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.drKonarev.requestapp.user.User;
import ru.drKonarev.requestapp.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    private final UserRepository userRepository;

    private final RequestMapper requestMapper;


    @Override
    public RequestDto save(RequestDto requestDto, Long ownerId) {
        Optional<User> owner = userRepository.findById(ownerId);
        if (owner.isEmpty())
            throw new RuntimeException("User not exist!");
        return requestMapper.toDto(requestRepository.save(requestMapper.toRequest(requestDto, owner.get())));
    }

    @Override
    public RequestDto updateRequest(RequestDto requestDto, Long requestId, Long ownerId) {
        Optional<Request> request = requestRepository.findById(requestId);
        if (request.isEmpty())
            throw new RuntimeException("Request not found");
        Optional<User> owner = userRepository.findById(ownerId);
        if (owner.isEmpty())
            throw new RuntimeException("User not exist!");
        return requestMapper.toDto(requestRepository.save(requestMapper.patch(request.get(), requestDto)));
    }

    @Override
    public List<RequestDto> getAllRequestsByOwnerId(Long ownerId, Sort sort) {
        return requestRepository.findAllRequestsByOwner_Id_OrderByCreated(ownerId, PageRequest.of(1, 5, sort))
                .stream()
                .map(requestMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public RequestOperatorDto getRequestById(Long requestId) {
        Optional<Request> request = requestRepository.findById(requestId);
        if (request.isEmpty())
            throw new RuntimeException("Request not found");
        return requestMapper.toOperator(request.get());
    }

    @Override
    public RequestOperatorDto changeStatusRequestByOperator(Long requestId, Boolean status) {
        Optional <Request> request = requestRepository.findById(requestId);
        if (request.isEmpty()) throw new RuntimeException("Request not found");
        if (!request.get().getStatus().equals(Status.SENT)) throw new IllegalStateException("Cannot change status");
        if (status) {
            request.get().setStatus(Status.ACCEPTED);
        }else {
            request.get().setStatus(Status.REJECTED);
        };
        return requestMapper.toOperator(requestRepository.save(request.get()));
    }

    @Override
    public RequestDto changeStatusRequestByUser(Long requestId, Long ownerId, Boolean status) {
        Optional<Request> request = requestRepository.findRequestByOwner_IdAndId(ownerId, requestId);
        if (request.isEmpty()) throw new RuntimeException("Request not found");
        if (!request.get().getStatus().equals(Status.NEW)) throw new IllegalArgumentException("Cannot change status");
        request.get().setStatus(Status.SENT);
        return requestMapper.toDto(requestRepository.save(request.get()));
    }

    @Override
    public List<RequestDto> searchRequestByName(String text, Sort sort) {
        Optional<User> owner = userRepository.findByName(text, Pageable.unpaged()).stream().findFirst();
        if (owner.isEmpty())
            throw new RuntimeException("User not found");
        return requestRepository.findAllRequestByOwner_IdAndStatus(owner.get().getId(), Status.SENT, PageRequest.of(1, 5, sort))
                .stream()
                .map(requestMapper::toDto)
                .collect(Collectors.toList());
    }
}
