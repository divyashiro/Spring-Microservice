package com.codetech.user.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	static final Log LOG = LogFactory.getLog(ExceptionControllerAdvice.class);

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorDetail> exceptionHandlerBadRequestEx(BadRequestException e,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ErrorDetail errorResponse = new ErrorDetail();
		errorResponse.setError(e.getError());
		errorResponse.setMessage(e.getMessage());
		errorResponse.setStatus(400);
		LOG.error("MSS Bad Request Expection: " + e.getMessage(), e);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

    @ExceptionHandler(ValidateDataException.class)
    public ResponseEntity<ErrorDetail> exceptionHandlerBadRequestEx(ValidateDataException e,
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ErrorDetail errorResponse = new ErrorDetail();
        errorResponse.setError(e.getError());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(400);
        LOG.error("MSS Bad Request Expection: " + e.getMessage(), e);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);


    }
    
    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ErrorDetail> exceptionHandlerInternalEx(InternalException e) {
	   ErrorDetail errorResponse = new ErrorDetail();
       errorResponse.setError(e.getError());
       errorResponse.setMessage(e.getMessage());
       errorResponse.setStatus(500);
       LOG.error("MSS Internal Expection: " + e.getMessage(), e);
       return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetail> exceptionHandlerNotFoundEx(NotFoundException e) {
    	ErrorDetail errorResponse = new ErrorDetail();
        errorResponse.setError(e.getError());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(404);
        LOG.error("MSS Not found Expection: " + e.getMessage(), e);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }    
    
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorDetail> exceptionHandlerForbiddenEx(ForbiddenException e) {
        ErrorDetail errorResponse = new ErrorDetail();
        errorResponse.setError(e.getError());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(403);
        LOG.error("MSS Forbidden Expection: " + e.getMessage(), e);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);

    }
    
 
    
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ErrorDetail> exceptionHandlerInvalidInputEx(InvalidInputException e) {
		ErrorDetail errorResponse = new ErrorDetail();
		errorResponse.setError(e.getError());
		errorResponse.setMessage(e.getMessage());
		errorResponse.setStatus(422);
		LOG.error("UnprocessableEntity Expection:" + e.getMessage(), e);
		return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}

 

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> exceptionHandlerInternalAll(Exception e, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        ErrorDetail errorResponse = new ErrorDetail();
        errorResponse.setError("unknown");
        errorResponse.setMessage("Something went wrong. Please try again.");
        errorResponse.setStatus(500);
        LOG.error("MSS Internal Expection: " + e.getMessage(), e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<ErrorDetail> exceptionHandlerMessageEx(MessageException e) {
    	ErrorDetail errorResponse = new ErrorDetail();
        errorResponse.setError(e.getError());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(200);
        LOG.error("MSS Not found Expection: " + e.getMessage(), e);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }    

    // javax validators
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        StringBuilder errorMsg = new StringBuilder();
        ex.getBindingResult().getAllErrors().stream().forEach(error -> details.add(error.getDefaultMessage()));
        ErrorDetail errorResponse = new ErrorDetail();
        if (details.size() > 1) {
            for (int count = 0; count < details.size(); count++) {
                if (count < details.size() - 1) {
                    errorMsg.append(details.get(count)).append(",");
                } else if (count == details.size() - 1) {
                    errorMsg.append(details.get(count));
                }
            }
        } else if (details.size() == 1) {
            errorMsg.append(details.get(0));
        }
        errorResponse.setError("Invalid Input");
        errorResponse.setMessage(errorMsg.toString());
        errorResponse.setStatus(400);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
