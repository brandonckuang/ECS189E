import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by brandonkuang on 2/25/17.
 */
public class TestInstructor {

    private IInstructor instructor;
    private IAdmin admin;
    private IStudent student;

    @Before
    public void setup() {
        this.instructor = new Instructor();
        this.admin = new Admin();
        this.student = new Student();

    }

    //Creating homework and seeing if it exists
    //Should return true
    @Test
    public void testAddHomework() {
        this.admin.createClass("ECS1", 2017, "Instructor1", 15);
        this.instructor.addHomework("Instructor1", "ECS1", 2017, "HW1",
                "homework1 description");
        assertTrue(this.instructor.homeworkExists("ECS1", 2017, "HW1"));
    }

    //Testing to see if instructor is assigned to the class
    //Test should fail since instructor assigned is Instructor 2 but Instructor 1 can add homework
    @Test
    public void testAddHomework2() {
        this.admin.createClass("ECS2", 2017, "Instructor2", 15);
        this.instructor.addHomework("Instructor1", "ECS2", 2017, "HW2",
                "homework2 description");
        assertFalse(this.instructor.homeworkExists("ECS2", 2017, "HW2"));
    }

    //Finding homework for a class that does not currently exist
    //Test should pass
    @Test
    public void testAddHomework3() {
        this.admin.createClass("ECS3", 2017, "Instructor3", 15);
        this.instructor.addHomework("Instructor3", "ECS3", 2017, "HW3" ,
                "homework 3 description");
        assertFalse(this.instructor.homeworkExists("ECS4", 2017, "HW3"));
    }

    //Testing to see if homework exists. Class will exist but the homework does not
    //Test should pass
    @Test
    public void testAddHomework4() {
        this.admin.createClass("ECS4", 2017, "Instructor4", 15);
        this.instructor.addHomework("Instructor4", "ECS4", 2017, "HW4",
                "homework 4 description");
        assertFalse(this.instructor.homeworkExists("ECS4", 2017, "HW5"));
    }


    @Test
    public void testAddHomework5() {

    }
}

