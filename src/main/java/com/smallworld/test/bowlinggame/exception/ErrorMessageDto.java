package com.smallworld.test.bowlinggame.exception;

//   Standard Libraries Imports

//   Third party Imports
import lombok.Data;

//   ns Framework Imports

//   Domain Imports


/**
 * ErrormessageDto.java<br><br>
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
@Data
public class ErrorMessageDto {
    private String object;
    private String field;
    private String message;
    private Object rejectedValue;

    public ErrorMessageDto() {
    }

    public ErrorMessageDto(String object, String field, String message, Object rejectedValue) {
        this.object = object;
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

}
