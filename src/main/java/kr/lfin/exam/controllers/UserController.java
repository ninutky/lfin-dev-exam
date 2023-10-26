package kr.lfin.exam.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.lfin.exam.common.utils.ApiResult;
import kr.lfin.exam.domains.User;
import kr.lfin.exam.domains.UserVO;
import kr.lfin.exam.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
                new ApiResult(userService.findAll(pageable)));
    }

}
