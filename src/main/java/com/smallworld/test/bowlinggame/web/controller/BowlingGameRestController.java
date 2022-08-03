package com.smallworld.test.bowlinggame.web.controller;

//   Standard Libraries Imports


//   Third Party Libraries Imports
import com.smallworld.test.bowlinggame.model.game.BowlingThrow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

//   ns Framework Imports


//   Domain Imports
import com.smallworld.test.bowlinggame.service.BowlingGame;


/**
 * BowlingGameRestController.java<br><br>
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
 * @version 1.0 - 2022-08-03 18:28
 */
@Slf4j
@RestController
@RequestMapping( "/bowlingGameAPI/1.0/games" )
public class BowlingGameRestController {

    private BowlingGame bowlingGameService;

    @Autowired
    public void setBowlingGameServices ( BowlingGame bowlingGameService ) { this.bowlingGameService = bowlingGameService; }

    /**
     * Posts a throw in a frame of bowling game [C].
     * @return
     */
    @PostMapping( { "" } )
    public ResponseEntity<Void> postThrow ( @Valid @RequestBody BowlingThrow numberOfPins ) {

        bowlingGameService.roll ( numberOfPins.getNumPinsInThrow ( ) );
        HttpHeaders headers = new HttpHeaders ( );


        return new ResponseEntity<> ( HttpStatus.OK );

    }

    /**
     * Requests the score of a game of bowling [R].
     * @return
     */
    @GetMapping( { "" } )
    public ResponseEntity<Integer> requestScore ( ) {
        int score = bowlingGameService.score ( );
        return new ResponseEntity<> ( score, HttpStatus.OK );
    }


}
