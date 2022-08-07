package com.smallworld.test.bowlinggame.exception;

//   Standard Libraries Imports

//   Third Party Libraries Imports
import lombok.extern.slf4j.Slf4j;

//   ns Framework Imports

//   Domain Imports


/**
 * FrameScoreOutOfBoundsException.java<br><br>
 * Creation Date 2022-08-07<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p>Creates own type of exception for business specific scenario where the total count of throws in a frame exceeds the allowed limit.</p>
 *
 * <PRE>
 * <table width="90%" border="1" cellpadding="3" cellspacing="2">
 * <tr><th colspan="2">   History   </th></tr>
 *
 * <tr>
 * <td width="20%">Version 1.0<br>
 * Version Date: 2022-08-07<br>
 *
 * @author pmarquezh </td>
 * <td width="80%"><p>Creation</p></td>
 * </tr>
 * </table>
 * </PRE>
 * @author pmarquezh
 * @version 1.0 - 2022-08-07
 */
@Slf4j
public class FrameScoreOutOfBoundsException extends Exception {
    public FrameScoreOutOfBoundsException ( String errorMessage ) {
        super ( errorMessage );
    }
}
