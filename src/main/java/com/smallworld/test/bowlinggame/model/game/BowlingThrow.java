package com.smallworld.test.bowlinggame.model.game;

//   Standard Libraries Imports

//   Third Party Libraries Imports
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

//   ns Framework Imports

//   Domain Imports


/**
 * BowlingThrow.java<br><br>
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
@Entity
@Data
public class BowlingThrow {

    @Id
    public Long id;

    @Max( value= 10, message="Value cannot be greater than 10")
    @Min( value = 0, message="Value cannot be lesser than 0" )
    private int numPinsInThrow;

}
