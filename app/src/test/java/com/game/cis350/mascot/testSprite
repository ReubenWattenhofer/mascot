package com.game.cis350.mascot;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import com.game.cis350.mascot.models.Sprite;
/**
 * Created by Ally Stoll on 10/16/2017.
 */

public class testSprite {
    /***
     * this test case makes sure when the x value is set it will add properly and since x sets to a
     * final int it should only be equal
     * to the value it is set to after that they should be not equal
     ***/
    @Test
    public void testX(){
    ArrayList<String> Louie = new ArrayList<>(Sprite.animation("Louie", "abc"));
    Sprite x = new Sprite((Louie),2,3);
    assertEquals(x.getX(),2);
    assertNotEquals(x.getX(),3);
    assertNotEquals(x.getX(),4);

}
    /***
     * this test case makes sure when the y value is set it will add properly and since y sets to a
     * final int it should only be equal
     * to the value it is set to after that they should be not equal
     ***/
@Test
    public void testY(){
    ArrayList<String> Louie = new ArrayList<>(Sprite.animation("Louie", "abc"));
    Sprite y = new Sprite((Louie),2,1);
    assertEquals(y.getY(),1);
    assertNotEquals(y.getY(),2);
    assertNotEquals(y.getY(),3);
}
}

