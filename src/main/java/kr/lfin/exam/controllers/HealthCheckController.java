package kr.lfin.exam.controllers;

import kr.lfin.exam.common.utils.ApiResult;
import kr.lfin.exam.domains.Health;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/health")
public class HealthCheckController {
    /**
     * 서비스 상태 체크 /v1/health/ping
     * @return
     */
    @GetMapping("/ping")
    public ResponseEntity<ApiResult> Ping() {
        return ResponseEntity.ok().body(
                new ApiResult(new Health("Ok")));
    }
}
