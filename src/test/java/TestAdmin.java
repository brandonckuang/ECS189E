import api.IAdmin;
import api.core.impl.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAdmin {

    //still need to test for uniqueness of a class/year combination. (key?)

    private IAdmin admin;

    @Before
    public void setup() {
        this.admin = new Admin();
    }

    //Tests whether the class exists, which was defined in setup
    @Test
    public void testMakeClass() {
        this.admin.createClass("ECS127", 2017, "Rogaway", 15);
        assertTrue(this.admin.classExists("ECS127", 2017));
    }

    //Tests whether the class exists, which was defined in setup
    @Test
    public void testMakeClass2() {
        this.admin.createClass("ECS120", 2018, "Rogaway", 15);
        assertTrue(this.admin.classExists("ECS120", 2018));
    }

    //Creates a class for the past and should assertFalse since it is not calendar year
    //Fails
    @Test
    public void testMakeClass3() {
        this.admin.createClass("ECS129", 2016, "Koehl", 15);
        assertFalse(this.admin.classExists("ECS129", 2016));
    }

    //Tests for a Course that does not exist
    @Test
    public void testMakeClass4() {
        assertFalse(this.admin.classExists("Fail", 2016));
    }

    //creating class with zero/negative capacity
    //Fails
    @Test
    public void testMakeClass5() {
        this.admin.createClass("ECS15", 2017, "Instructor6", 0);
        if((this.admin.getClassCapacity("ECS15", 2017)) <= 0 && this.admin.classExists("ECS15", 2017))
        {
            fail("Zero/negative class capacity");
        }
    }

    //Testing for more than 2 courses per professor
    //Fails
    @Test
    public void testMakeClass6() {
        this.admin.createClass("ECS1", 2017, "Instructor10", 15);
        this.admin.createClass("ECS2", 2017, "Instructor10", 15);
        this.admin.createClass("ECS3", 2017, "Instructor10", 15);
        if( this.admin.classExists("ECS1", 2017) && this.admin.classExists("ECS2", 2017) &&
                this.admin.classExists("ECS3", 2017)){
            fail("Professor already teaching two courses");
        }
       // assertFalse("Professor already teaching two courses",this.admin.classExists("ECS3", 2017));

    }

    //Testing for three classes for the same professor but one is in a different year and should assertTrue
    @Test
    public void testMakeClass7() {
        this.admin.createClass("ECS4", 2017, "Instructor", 15);
        this.admin.createClass("ECS5", 2017, "Instructor", 15);
        this.admin.createClass("ECS6", 2018, "Instructor", 15);
        assertTrue(this.admin.classExists("ECS6", 2018));

    }

    //Tests for empty string for instructor, which should assertFalse
    //Fails
    @Test
    public void testMakeClass8() {
        this.admin.createClass("ECS8", 2017, "", 15);
        assertFalse(this.admin.classExists("ECS8", 2017));
    }

    //Tests to see if there is a classname
    //Fails
    @Test
    public void testMakeClass9() {
        this.admin.createClass("", 2017, "Instructor9", 15);
        assertFalse(this.admin.classExists("", 2017));
    }

    //Test for different letter cases
    @Test
    public void testMakeClass10() {
        this.admin.createClass("ECS200", 2017, "Instructor200", 15);
        this.admin.createClass("ecs200", 2017, "Instructor200", 15);
        assertTrue(this.admin.classExists("ECS200", 2017));
        assertTrue(this.admin.classExists("ecs200", 2017));

    }

    //Tests for the creation of a class for year 0
    //Fail
    @Test
    public void testMakeClass11() {
        this.admin.createClass("ECS1000", 0, "Instructor1000", 15);
        assertFalse(this.admin.classExists("ECS1000", 0));
    }


    //Tests whether a professor teaches a class, which should be true

    @Test
    public void testGetClassInstructor() {
        this.admin.createClass("ECS12", 2017, "Instructor15", 15);
        assertTrue(this.admin.getClassInstructor("ECS12", 2017).equals("Instructor15"));
    }

    //Tests for a false professor teaching a class.
    @Test
    public void testGetClassInstructor2() {
        this.admin.createClass("ECS10", 2018, "Rogaway", 15);
        assertFalse(this.admin.getClassInstructor("ECS10", 2018).equals("False"));
    }

    //Tests for the null condition when a nonexistent class is given as parameters
    @Test
    public void testGetClassInstructor3() {
        assertNull(this.admin.getClassInstructor("SoFalse", 0));
    }

    @Test
    public void testGetClassInstructor4() {
        this.admin.createClass("ECS5000", 2017, "Instructor5000", 15);
        assertNull(this.admin.classExists("ECS5000", 0));
    }


    @Test
    public void testGetCapacity(){
        this.admin.createClass("Test", 2017, "Instructor2", 15);
        assertEquals(15, (double)(this.admin.getClassCapacity("Test", 2017)), 0);

    }

    @Test
    public void testGetCapacity2() {
        assertEquals(-1, (double)(this.admin.getClassCapacity("fail", 2016)), 0);
    }

    //Testing Change Capacity to make sure it does change the capacity
    @Test
    public void testChangeCapacity() {
        this.admin.createClass("ECS13", 2017, "Instructor4", 15);
        this.admin.changeCapacity("ECS13", 2017, 16);
        assertTrue(this.admin.getClassCapacity("ECS13", 2017) == 16);
    }

    //changed the capacity to 0 but expected that it will do nothing or should not change the capacity
    //Fails
    @Test
    public void testChangeCapacity2() {
        this.admin.createClass("ECS14", 2017, "Instructor5", 15);
        this.admin.changeCapacity("ECS14", 2017, 0);
        //assertEquals(15, (double)(this.admin.getClassCapacity("ECS14", 2017)), 0);
        assertTrue(this.admin.getClassCapacity("ECS14", 2017) == 15);
    }

    @Test
    public void testChangeCapacity3() {
        this.admin.createClass("ECS16", 2017, "Instructor15", 15);
        this.admin.changeCapacity("ECS16", 2017, 15);
      //  assertEquals(15, (double)(this.admin.getClassCapacity("ECS16", 2017)), 0);
        assertTrue(this.admin.getClassCapacity("ECS16", 2017) == 15);
    }

    //Tests for duplicate classes being added
    //fails
    @Test
    public void testUniqueClass() {
        this.admin.createClass("ECS123", 2017, "me", 15);
        this.admin.createClass("ECS123", 2017, "you", 15);
        if((this.admin.getClassInstructor("ECS123", 2017)).equals("you")) {
            fail("Duplicate class created");
        }
        else assertTrue(true);
    }

}

