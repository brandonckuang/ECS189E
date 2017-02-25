import api.IAdmin;
import api.IInstructor;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by brandonkuang on 2/25/17.
 */
public class TestInstructor {

    private IInstructor instructor;

    @Before
    public void setup() {
        this.instructor = new Instructor();
    }

    @Test
    public void testMakeClass() {
        //this.instructor.()

    }
}
