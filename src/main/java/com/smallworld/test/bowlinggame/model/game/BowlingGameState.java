package com.smallworld.test.bowlinggame.model.game;

//   Standard Libraries Imports

//   Third Party Libraries Imports
import com.smallworld.test.bowlinggame.config.CurrentThrow;
import lombok.extern.slf4j.Slf4j;
import lombok.Data;

//   ns Framework Imports

//   Domain Imports
import com.smallworld.test.bowlinggame.config.CurrentGameState;

import java.util.List;


/**
 * BowlingGameState.java<br><br>
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
public class BowlingGameState {
    public static final int ZERO                = 0;
    public static final int ONE                 = 1;
    public static final int TWO                 = 2;
    public static final int FIRST_FRAME         = 0;   //   ZERO BASED, so [ 0..11 ]
    public static final int NUM_FRAMES_PER_GAME = 10;
    public static final int MAX_PINS_PER_FRAME  = 10;

    public static boolean               gameOver                = false;
    public static int                   currentFrame            = FIRST_FRAME;

    public static CurrentThrow          currentThrow            = CurrentThrow.FIRST_THROW;
    public static int                   firstThrowCount         = ZERO;

    public static int                   firstExtraThrowCount    = ZERO;
    public static int                   secondExtraThrowCount   = ZERO;

    public static int                   currentScore            = ZERO;

    public static BowlingFrame          frames [ ] = new BowlingFrame [ ] { new BowlingFrame ( ),
                                                                            new BowlingFrame ( ),
                                                                            new BowlingFrame ( ),
                                                                            new BowlingFrame ( ),
                                                                            new BowlingFrame ( ),
                                                                            new BowlingFrame ( ),
                                                                            new BowlingFrame ( ),
                                                                            new BowlingFrame ( ),
                                                                            new BowlingFrame ( ),
                                                                            new BowlingFrame ( ),
                                                                            new BowlingFrame ( ),
                                                                            new BowlingFrame ( ) };

    public static CurrentGameState      gameState               = CurrentGameState.NORMAL;

}
