import api.IAdmin;
import api.core.impl.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAdmin {

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
        this.admin.createClass("ECS120", 2017, "Rogaway", 15);
        assertTrue(this.admin.classExists("ECS120", 2017));
    }

    //Creates a test in the function and tests for its existence
    @Test
    public void testMakeClass3() {
        this.admin.createClass("ECS129", 2016, "Koehl", 15);
        assertTrue(this.admin.classExists("ECS129", 2016));
    }

    //Tests for a Course that does not exist
    @Test
    public void testMakeClass4() {
        assertFalse(this.admin.classExists("Fail", 2016));
    }

    //Tests whether a professor teaches a class, which should be true
    @Test
    public void testGetClassInstructor() {
        this.admin.createClass("ECS127", 2017, "Rogaway", 15);
        assertTrue(this.admin.getClassInstructor("ECS127", 2017).equals("Rogaway"));
    }

    //Tests for a false professor teaching a class.
    @Test
    public void testGetClassInstructor2() {
        this.admin.createClass("ECS127", 2017, "Rogaway", 15);
        assertFalse(this.admin.getClassInstructor("ECS127", 2017).equals("False"));
    }

    //Tests for the null condition when a nonexistent class is given as parameters
    @Test
    public void testGetClassInstructor3() {
        assertNull(this.admin.getClassInstructor("False", 0));
    }


    @Test
    public void testGetCapacity(){
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertEquals(15, (double)(this.admin.getClassCapacity("Test", 2017)), 0);

    }

    @Test
    public void testGetCapacity2() {
        assertEquals(-1, (double)(this.admin.getClassCapacity("fail", 2016)), 0);
    }

    @Test
    public void testChangeCapacity() {
        this.admin.createClass("ECS127", 2017, "Rogaway", 15);
        this.admin.changeCapacity("ECS127", 2017, 16);
        assertEquals(16, (double)(this.admin.getClassCapacity("ECS127", 2017)), 0);
    }

    @Test
    public void testChangeCapacity2() {
        this.admin.createClass("ECS127", 2017, "Rogaway", 15);
        this.admin.changeCapacity("False", 2017, 0);
    }
}
