package com.smallworld.test.bowlinggame.exception;

//   Standard Libraries Imports
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

//  Third Party Imports
import lombok.extern.java.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

//   ns Framework Imports

//   Domain Imports
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * PostalAddressExceptionHandler.java<br><br>
 * Creation Date 2022-08-05<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 * <PRE>
 * <table width="90%" border="1" cellpadding="3" cellspacing="2">
 * <tr><th colspan="2">   History   </th></tr>
 *
 * <tr>
 * <td width="20%">Version 1.0<br>
 * Version Date: 2022-08-05<br>
 *
 * @author pmarquezh </td>
 * <td width="80%"><p>Creation</p></td>
 * </tr>
 * </table>
 * </PRE>
 * @author pmarquezh
 * @version 1.0 - 2022-08-05
 */
@Log
@RestControllerAdvice
public class BowlingExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler ( value = { Exception.class, FrameScoreOutOfBoundsException.class } )
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public ErrorDetails handleExceptions ( Exception ex, WebRequest request ) {
        return new ErrorDetails ( LocalDate.now ( ), ex.getMessage ( ), "FrameScoreOutOfBoundsException" );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid ( MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request ) {

        List<ErrorMessageDto> validationErrorDetails = ex.getBindingResult ( )
                                                         .getFieldErrors ( )
                                                         .stream ( )
                                                         .map ( error -> new ErrorMessageDto ( error.getObjectName ( ), error.getField ( ), error.getDefaultMessage ( ), error.getRejectedValue ( ).toString ( ) ) )
                                                         .collect ( Collectors.toList ( ) );

        ErrorResponse response = new ErrorResponse ( status.name ( ), status.value ( ), validationErrorDetails );
        return new ResponseEntity<> ( response,status );

    }

}
