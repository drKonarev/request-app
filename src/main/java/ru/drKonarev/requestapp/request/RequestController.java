package ru.drKonarev.requestapp.request;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;


    @PostMapping("/post")
    @Secured("USER")
    public RequestDto save(@RequestHeader("User-Id") Long ownerId,
                           @RequestBody RequestDto request) {
        return requestService.save(request, ownerId);
    }

    @GetMapping("/all")
    @Secured("USER")
    public List<RequestDto> getAllByOwnerId(@RequestHeader("User-Id") Long ownerId,
                                            @RequestParam("Sort") boolean sort) {
        return requestService.getAllRequestsByOwnerId(ownerId, sort(sort));
    }

    @PatchMapping("/{requestId}")
    @Secured({"OPERATOR"})
    public RequestOperatorDto updateStatusByOperator(@PathVariable(required = true, name = "requestId") Long requestId,
                                                     @RequestBody Boolean status) { // true - accepted, false - rejected
        return requestService.changeStatusRequestByOperator(requestId, status);
    }

    @PatchMapping("/send/{requestId}")
    @Secured({"USER"})
    public RequestDto updateStatusByUser(@RequestHeader("User-Id") Long ownerId,
                                         @PathVariable(required = true, name = "requestId") Long requestId,
                                         @RequestBody Boolean status) {
        return requestService.changeStatusRequestByUser(requestId, ownerId, status);
    }

    @PatchMapping("/update/{requestId}")
    @Secured("USER")
    public RequestDto updateRequest(@RequestHeader("User-Id") Long ownerId,
                                    @PathVariable(name = "requestId") Long requestId,
                                    @RequestBody RequestDto request) {
        return requestService.updateRequest(request, requestId, ownerId);
    }

    @GetMapping("/{requestId}")
    @Secured("OPERATOR")
    public RequestOperatorDto getRequestById(@PathVariable(name = "requestId") Long requestId) {

        return requestService.getRequestById(requestId);
    }

    @Secured("OPERATOR")
    @GetMapping("/search")
    public List<RequestDto> getRequestsByName(@RequestParam("text") @NotBlank @NotNull String text,
                                              @RequestParam(name = "sort") boolean sort) {
        return requestService.searchRequestByName(text, sort(sort));
    }

    private Sort sort(Boolean type) {
        if (type) return Sort.by(Sort.Direction.DESC, "created");
        return Sort.by(Sort.Direction.ASC, "created");
    }
}
