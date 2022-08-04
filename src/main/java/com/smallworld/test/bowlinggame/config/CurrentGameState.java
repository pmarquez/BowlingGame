package com.smallworld.test.bowlinggame.config;

/**
 * CurrentGameState.java<br><br>
 * Creation Date 2022-08-03 19:09<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 * <PRE>
 * <table width="90%" border="1" cellpadding="3" cellspacing="2">
 * <tr><th colspan="2">   History   </th></tr>
 *
 * <tr>
 * <td width="20%">Version 1.0<br>
 * Version Date: 2022-08-03 19:09<br>
 *
 * @author pmarquezh </td>
 * <td width="80%"><p>Creation</p></td>
 * </tr>
 * </table>
 * </PRE>
 * @author pmarquezh
 * @version 1.0 - 2022-08-03 19:09
 */
public enum CurrentGameState {
    NORMAL,         //   Nothing extra to add in next frame.
    SPARE,          //   Waiting for next frame to add ten point plus next throw.
    STRIKE,         //   Waiting for next frame to add ten point plus next two throws.
    UNUSED;         //   FRAME NOT PLAYED YET.
}
