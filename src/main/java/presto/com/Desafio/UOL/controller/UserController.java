package presto.com.Desafio.UOL.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import presto.com.Desafio.UOL.domain.user.UserEntity;
import presto.com.Desafio.UOL.domain.user.UserRequestDto;
import presto.com.Desafio.UOL.domain.user.UserResponseDto;
import presto.com.Desafio.UOL.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserRequestDto dto){
        var user = this.service.register(dto);

        return ResponseEntity.ok(new UserResponseDto(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> getAll(Pageable page){
        var list = this.service.getAll(page).map(UserResponseDto::new);
        return ResponseEntity.ok(list);
    }
}
