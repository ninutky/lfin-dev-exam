package kr.lfin.exam.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.lfin.exam.common.utils.ApiResult;
import kr.lfin.exam.domains.UserVO;
import kr.lfin.exam.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "User management APIs")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResult> insert(@RequestBody UserVO userVO) {
        userService.insert(userVO);
        return ResponseEntity.ok().body(new ApiResult());
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResult> findAll(final Pageable pageable) {
        return ResponseEntity.ok().body(
                new ApiResult(userService.findByDeletedFalse(pageable)));
    }

    @PutMapping( "/{id}")
    public ResponseEntity<ApiResult> update(@PathVariable Long id, @RequestBody UserVO userVO) {
        userService.update(id, userVO);
        return ResponseEntity.ok().body(new ApiResult());
    }

    @PutMapping( "delete/{id}")
    public ResponseEntity<ApiResult> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().body(new ApiResult());
    }
}
