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

    @Test
    public void testMakeClass() {
        this.admin.createClass("ECS", 2017, "Rogaway", 15);
        assertTrue(this.admin.classExists("ECS", 2017));
    }

    @Test
    public void testMakeClass2() {
        this.admin.createClass("ECS", 2016, "Koehl", 15);
        assertTrue(this.admin.classExists("ECS", 2016));
    }

    @Test
    public void testMakeClass3() {
        assertFalse(this.admin.classExists("ECS", 2015));

    }
}
