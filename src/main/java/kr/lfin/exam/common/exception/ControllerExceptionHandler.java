package kr.lfin.exam.common.exception;

import kr.lfin.exam.common.utils.ApiResult;
import kr.lfin.exam.common.utils.ResultStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResult resourceNotFoundException() {
        return new ApiResult(ResultStatus.CLIENT_NOT_FOUND_RESOURCE);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResult sqlIntegrityConstraintViolationException() {
        return new ApiResult(ResultStatus.CLIENT_INVALID_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResult illegalArgumentException() {
        return new ApiResult(ResultStatus.CLIENT_INVALID_REQUEST);
    }

    @ExceptionHandler(NotNullFieldsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResult notNullException(){return new ApiResult(ResultStatus.CLIENT_NOT_NULL_FIELDS);}

    @ExceptionHandler(DeletedResourceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResult deletedResourceException(){return new ApiResult(ResultStatus.CLIENT_DELETED_RESOURCE);}

    @ExceptionHandler(ExistChildrenDataException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResult existChildrenDataException(){return new ApiResult(ResultStatus.CLIENT_EXIST_CHILDREN_DATA);}
}