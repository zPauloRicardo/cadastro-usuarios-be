package me.paulojr.cadastro.infra.api;


import me.paulojr.cadastro.domain.shared.search.Pagination;
import me.paulojr.cadastro.infra.context.user.models.UserRequestBody;
import me.paulojr.cadastro.infra.context.user.models.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/user")
public interface UserAPI {

    @PostMapping
    ResponseEntity<UserResponse> createUser(@RequestBody UserRequestBody body);

    @PutMapping("/{id}")
    ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequestBody body);

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findUser(@PathVariable Long id);

    @GetMapping
    ResponseEntity<Pagination<UserResponse>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false, defaultValue = "0") Integer page
    );
}
