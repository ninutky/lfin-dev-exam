package kr.lfin.exam.common.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public enum ResultStatus {

    REQUEST_SUCCESS("0000", "정상적으로 처리되었습니다."),

    CLIENT_NOT_NULL_FIELDS("1101","필수 입력값이 누락되었습니다."),
    CLIENT_DELETED_RESOURCE("1003","삭제된 리소스입니다."),
    CLIENT_NOT_FOUND_RESOURCE("1001", "리소스를 찾을 수 없습니다."),
    CLIENT_EXIST_CHILDREN_DATA("1004", "하위 데이터가 존재합니다."),
    CLIENT_INVALID_REQUEST("1002", "형식이 올바르지 않습니다.");

    private final String status;
    private final String message;
}