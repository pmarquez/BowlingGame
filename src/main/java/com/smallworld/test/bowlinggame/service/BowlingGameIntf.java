package com.smallworld.test.bowlinggame.service;

//   Standard Libraries Imports

//   Third Party Libraries Imports

//   ns Framework Imports

//   Domain Imports
import com.smallworld.test.bowlinggame.exception.FrameScoreOutOfBoundsException;


/**
 * BowlingGameIntf.java<br><br>
 * Creation Date 2022-08-03<br><br>
 * <b>DESCRIPTION:</b><br><br>
 * <p></p>
 *
 * <PRE>
 * <table width="90%" border="1" cellpadding="3" cellspacing="2">
 * <tr><th colspan="2">   History   </th></tr>
 *
 * <tr>
 * <td width="20%">Version 1.0<br>
 * Version Date: 2022-08-03<br>
 *
 * @author pmarquezh </td>
 * <td width="80%"><p>Creation</p></td>
 * </tr>
 * </table>
 * </PRE>
 * @author pmarquezh
 * @version 1.0 - 2022-08-03
 */
public interface BowlingGameIntf {

    /**
     * Receives the number of pins knocked down in a turn.
     * @param pinCount
     */
    void roll ( int pinCount ) throws FrameScoreOutOfBoundsException;

    /**
     * Returns the score of the game.
     * @return
     */
    int score ( );

}
