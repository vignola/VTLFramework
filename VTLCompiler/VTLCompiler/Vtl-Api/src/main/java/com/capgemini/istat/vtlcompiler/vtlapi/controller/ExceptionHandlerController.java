package com.capgemini.istat.vtlcompiler.vtlapi.controller;

import com.capgemini.istat.vtlcompiler.vtlapi.response.ErrorResponse;
import com.capgemini.istat.vtlcompiler.vtlapi.response.VtlCompilerResponse;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.SyntaxException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.TraslationException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.exception.ValidationException;
import com.capgemini.istat.vtlcompiler.vtlcommon.model.semantic.ValidationCheck;
import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/")
public class ExceptionHandlerController {
    private MessageSource messageSource;
    public Logger logger = LogManager.getLogger(ExceptionHandlerController.class);


    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<VtlCompilerResponse> handleValidationException(ValidationException validationException, Locale locale) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCommand(validationException.getCommand());
        errorResponse.setCode(validationException.getSemanticMessage().getCode());
        errorResponse.setMessage(validationException.getSemanticMessage().getMessage());
        errorResponse.setMessage(messageSource.getMessage(validationException.getSemanticMessage().getMessage(), validationException.getSemanticMessage().getParameters(), locale));
        if (!Strings.isNullOrEmpty(validationException.getLiteCommand()) && !validationException.getLiteCommand().equalsIgnoreCase(validationException.getCommand().substring(0, validationException.getCommand().length() - 1))) {
            errorResponse.setMessage(errorResponse.getMessage() + "  ->  " + validationException.getLiteCommand());
        }
        VtlCompilerResponse vtlCompilerResponse = new VtlCompilerResponse();
        vtlCompilerResponse.setErrorResponse(errorResponse);
        return new ResponseEntity<>(vtlCompilerResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SyntaxException.class)
    public ResponseEntity<VtlCompilerResponse> handleSyntaxException(SyntaxException syntaxException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(syntaxException.getMessage());
        VtlCompilerResponse vtlCompilerResponse = new VtlCompilerResponse();
        vtlCompilerResponse.setErrorResponse(errorResponse);
        return new ResponseEntity<>(vtlCompilerResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TraslationException.class)
    public ResponseEntity<VtlCompilerResponse> handleTraslationException(TraslationException traslationException, Locale locale) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(traslationException.getCode());
        errorResponse.setMessage(messageSource.getMessage(traslationException.getMessage(), traslationException.getParameters(), locale));
        errorResponse.setCommand(traslationException.getCommand());
        VtlCompilerResponse vtlCompilerResponse = new VtlCompilerResponse();
        vtlCompilerResponse.setErrorResponse(errorResponse);
        return new ResponseEntity<>(vtlCompilerResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<VtlCompilerResponse> handleExceptions(Exception ex, Locale locale) {
        logger.error(ex);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ValidationCheck.UNEXPECTED_ERROR.getCode());
        errorResponse.setMessage(messageSource.getMessage(ValidationCheck.UNEXPECTED_ERROR.getMessage(), null, locale));
        String stackTrace = "";
        for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
            stackTrace = stackTrace + stackTraceElement.toString();
        }
        errorResponse.setMessage(errorResponse.getMessage() + "-->" + stackTrace);
        VtlCompilerResponse vtlCompilerResponse = new VtlCompilerResponse();
        vtlCompilerResponse.setErrorResponse(errorResponse);
        return new ResponseEntity<>(vtlCompilerResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
