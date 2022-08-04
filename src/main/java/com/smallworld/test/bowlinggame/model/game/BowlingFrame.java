package com.smallworld.test.bowlinggame.model.game;

//   Standard Libraries Imports

//   Third Party Libraries Imports
import com.smallworld.test.bowlinggame.config.CurrentGameState;
import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//   ns Framework Imports

//   Domain Imports


/**
 * BowlingFrame.java<br><br>
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
@Slf4j
@Data
@NoArgsConstructor
public class BowlingFrame {
    int firstThrow              = 0;
    int secondThrow             = 0;
    int extraPoints             = 0;
//    int extraThrowsCount    = 0;
    CurrentGameState frameState = CurrentGameState.UNUSED;
}
