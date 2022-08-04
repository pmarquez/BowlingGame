package com.smallworld.test.bowlinggame.service;

//   Standard Libraries Imports

//   Third Party Libraries Imports
import com.smallworld.test.bowlinggame.config.CurrentGameState;
import com.smallworld.test.bowlinggame.config.CurrentThrow;
import com.smallworld.test.bowlinggame.model.game.BowlingFrame;
import com.smallworld.test.bowlinggame.model.game.BowlingGameState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//   commons Imports

//   Domain Imports


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
@Service
public class BowlingGame implements BowlingGameIntf {

    /**
     * Receives the number of pins knocked down in a throw.
     *
     * @param pinCount
     */
    @Override
    public void roll ( int pinCount ) throws RuntimeException {

        this.decideIfGameIsOver ( );

        boolean isFirstThrow = BowlingGameState.currentThrow == CurrentThrow.FIRST_THROW;

        if ( !BowlingGameState.gameOver ) {

            if ( BowlingGameState.gameState == CurrentGameState.NORMAL ) {
                log.info ( "NORMAL" );
                this.handleNormalScenario ( isFirstThrow, pinCount );

            } else if (BowlingGameState.gameState == CurrentGameState.SPARE ) {
                log.info ( "SPARE" );
                this.handleSpareScenario ( isFirstThrow, pinCount );

            } else if ( BowlingGameState.gameState == CurrentGameState.STRIKE ) {
                log.info ( "STRIKE" );
                this.handleStrikeScenario ( isFirstThrow, pinCount );

            }

            this.handleSpareExtraPoints ( );
            this.handleStrikeExtraPoints ( );

            log.info ( ( BowlingGameState.gameState == CurrentGameState.NORMAL ) ? "NORMAL" : ( BowlingGameState.gameState == CurrentGameState.SPARE ) ? "SPARE" : "STRIKE" );
            log.info ( "Current Frame: " + BowlingGameState.currentFrame );
            this.dumpScoreBoard ( );

        } else {
            log.info("GAME IS OVER, PLEASE RE-RUN!");

        }

    }

    private void decideIfGameIsOver ( ) {

        boolean isGamOver = false;

        isGamOver = ( ( BowlingGameState.currentFrame >= BowlingGameState.NUM_FRAMES_PER_GAME ) &&
                    ( BowlingGameState.frames [ BowlingGameState.NUM_FRAMES_PER_GAME - 1 ].getFrameState ( ) == CurrentGameState.NORMAL ) );

        BowlingGameState.gameOver = isGamOver;
    }

    /**
     * Returns the score of the game.
     *
     * @return
     */
    @Override
    public int score ( ) {

        int total = 0;

        for ( int i = 0; i < BowlingGameState.NUM_FRAMES_PER_GAME; i++ ) {
            total += BowlingGameState.frames [ i ].getFirstThrow  ( ) +
                    BowlingGameState.frames [ i ].getSecondThrow ( ) +
                    BowlingGameState.frames [ i ].getExtraPoints ( );

        }

        return total;

    }

    /**
     * Utility method to dump the score
     */
    private void dumpScoreBoard ( ) {
        for ( int i = 0; i < BowlingGameState.frames.length; i++ ) {
            System.out.print ( "          (" + i + ") " + BowlingGameState.frames [ i ].getFirstThrow ( ) );
            System.out.print ( "-" + BowlingGameState.frames [ i ].getSecondThrow ( ) );
            System.out.print ( "-" + BowlingGameState.frames [ i ].getExtraPoints ( ) + " x:" );
            System.out.println ( ( BowlingGameState.frames [ i ].getFrameState ( ) == CurrentGameState.NORMAL ) ? "NORMAL" : ( BowlingGameState.frames [ i ].getFrameState ( ) == CurrentGameState.SPARE ) ? "SPARE" : ( BowlingGameState.frames [ i ].getFrameState ( ) == CurrentGameState.UNUSED ) ? "UNUSED" : "STRIKE" );

        }
    }

    /**
     * Handles the NORMAL state of a game.
     * @param pinCount
     */
    private void handleNormalScenario ( boolean firstThrow, int pinCount ) {

        if ( firstThrow ) {

            if ( pinCount < 10 ) {
                BowlingGameState.gameState = CurrentGameState.NORMAL;
                BowlingGameState.frames [ BowlingGameState.currentFrame ].setFirstThrow ( pinCount );
                BowlingGameState.currentThrow = CurrentThrow.SECOND_THROW;

            } else {
                this.setStrikeState ( pinCount );

            }

        } else {
            int frameRunningTotal = pinCount + BowlingGameState.frames [ BowlingGameState.currentFrame ].getFirstThrow ( );

            if ( frameRunningTotal < 10 ) {
                BowlingGameState.gameState = CurrentGameState.NORMAL;
                BowlingGameState.frames [ BowlingGameState.currentFrame ].setSecondThrow ( pinCount );
                BowlingGameState.frames [ BowlingGameState.currentFrame ].setFrameState ( CurrentGameState.NORMAL );
                BowlingGameState.currentFrame++;
                BowlingGameState.currentThrow = CurrentThrow.FIRST_THROW;

            } else if ( frameRunningTotal == 10 ) {
                this.setSpareState ( pinCount );

            } else {
                throw new RuntimeException ( );

            }
        }

    }

    //   EVERYTHING SPARE-RELATED
    //   EVERYTHING SPARE-RELATED
    //   EVERYTHING SPARE-RELATED

    /**
     * Handles the SPARE state of a game.
     * @param pinCount
     */
    private void handleSpareScenario ( boolean firstThrow, int pinCount ) {

        if ( firstThrow ) {
            if ( pinCount < 10 ) {
                //   set NORMAL state
                BowlingGameState.gameState = CurrentGameState.NORMAL;
                BowlingGameState.frames [ BowlingGameState.currentFrame ].setFirstThrow ( pinCount );
                BowlingGameState.currentThrow = CurrentThrow.SECOND_THROW;

            } else {
                this.setStrikeState ( pinCount );

            }

            //this.handleSpareExtraPoints ( );

        }

    }

    /**
     * Handles the addition of the extra points from SPAREs
     */
    private void handleSpareExtraPoints ( ) {

        log.info ( "enter handleSpareExtraPoints" );

        for ( int i = 0; i < BowlingGameState.frames.length - 1; i++ ) {
            if ( BowlingGameState.frames [ i ].getFrameState ( ) == CurrentGameState.SPARE ) {
                this.processSpareExtraPoints ( i );
            }
        }
    }

    private void processSpareExtraPoints ( int i ) {

        BowlingFrame currentFrame = BowlingGameState.frames [ i ];
        BowlingFrame oneUpFrame   = BowlingGameState.frames [ i + 1 ];

        if ( oneUpFrame.getFrameState ( ) != CurrentGameState.UNUSED ) {
            currentFrame.setExtraPoints ( oneUpFrame.getFirstThrow ( ) );
            currentFrame.setFrameState ( CurrentGameState.NORMAL );
        }

    }

    /**
     * Sets the SPARE STATE
     * @param pinCount
     */
    private void setSpareState ( int pinCount ) {
        BowlingGameState.gameState = CurrentGameState.SPARE;
        BowlingGameState.frames [ BowlingGameState.currentFrame ].setSecondThrow ( pinCount );
        BowlingGameState.frames [ BowlingGameState.currentFrame ].setFrameState ( CurrentGameState.SPARE );
        BowlingGameState.currentFrame++;
        BowlingGameState.currentThrow = CurrentThrow.FIRST_THROW;
    }




    //   EVERYTHING STRIKE-RELATED
    //   EVERYTHING STRIKE-RELATED
    //   EVERYTHING STRIKE-RELATED

    /**
     * Handles the STRIKE state of a game.
     * @param pinCount
     */
    private void handleStrikeScenario ( boolean firstThrow, int pinCount ) {

        if ( firstThrow ) {
            if ( pinCount < 10 ) {
                BowlingGameState.gameState = CurrentGameState.NORMAL;
                BowlingGameState.frames [ BowlingGameState.currentFrame ].setFirstThrow ( pinCount );
                BowlingGameState.currentThrow = CurrentThrow.SECOND_THROW;

            } else {
                this.setStrikeState ( pinCount );

            }

//            this.handleStrikeExtraPoints ( );
        }

    }

    /**
     * Handles the addition of the extra points from STRIKEs
     */
    private void handleStrikeExtraPoints ( ) {

        for ( int i = 0; i < BowlingGameState.frames.length - 2; i++ ) {
            if ( BowlingGameState.frames [ i ].getFrameState ( ) == CurrentGameState.STRIKE ) {
                this.processStrikeExtraPoints ( i );
            }
        }

    }

    private void processStrikeExtraPoints ( int i ) {

        BowlingFrame currentFrame = BowlingGameState.frames [ i ];
        BowlingFrame oneUpFrame   = BowlingGameState.frames [ i + 1 ];
        BowlingFrame twoUpFrame   = BowlingGameState.frames [ i + 2 ];

        if ( ( oneUpFrame.getFrameState ( ) == CurrentGameState.NORMAL ) || ( oneUpFrame.getFrameState ( ) == CurrentGameState.SPARE ) ) {
            log.info ( "enter handleStrikeExtraPoints NORMAL || SPARE" );

            currentFrame.setExtraPoints ( oneUpFrame.getFirstThrow ( ) + oneUpFrame.getSecondThrow ( ) );
            currentFrame.setFrameState ( CurrentGameState.NORMAL );

        } else if ( ( oneUpFrame.getFrameState ( ) == CurrentGameState.STRIKE ) && ( twoUpFrame.getFrameState ( ) != CurrentGameState.UNUSED ) ) {
            log.info ( "enter handleStrikeExtraPoints STRIKE && !UNUSED" );

            currentFrame.setExtraPoints ( oneUpFrame.getFirstThrow ( ) + twoUpFrame.getFirstThrow ( ) );
            currentFrame.setFrameState ( CurrentGameState.NORMAL );

        }

    }

    /**
     * Sets the STRIKE STATE
     * @param pinCount
     */
    private void setStrikeState ( int pinCount ) {
        BowlingGameState.gameState = CurrentGameState.STRIKE;
        BowlingGameState.frames [ BowlingGameState.currentFrame ].setFirstThrow ( pinCount );
        BowlingGameState.frames [ BowlingGameState.currentFrame ].setFrameState ( CurrentGameState.STRIKE );
        BowlingGameState.currentFrame++;
        BowlingGameState.currentThrow = CurrentThrow.FIRST_THROW;

    }

}
