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
public class TestStudent {
	private IInstructor instructor;
    private IAdmin admin;
    private IStudent student;

    @Before
    public void setup() {
        this.instructor = new Instructor();
        this.admin = new Admin();
        this.student = new Student();
    }
        //correct registering for class
        @Test
        public void testRegisterClass() {
            this.admin.createClass("ECS1", 2017, "Instructor1", 15);
            int capacity = this.admin.getClassCapacity("ECS1", 2017);
            if(capacity > 0){
                this.student.registerForClass("student1", "ECS1", 2017);
            }
        assertTrue(this.student.isRegisteredFor("student1", "ECS1", 2017));
        }

    //Try to register for a class that does not exist
    @Test
    public void testRegisterClass2(){
        //this.admin.createClass("ECS2", 2017, "Instructor2", 15);
        this.student.registerForClass("student2", "ECS2", 2017);
        assertFalse(this.student.isRegisteredFor("student2", "ECS2", 2017));

    }

    //Register for a class that has met its enrollment capacity
    @Test
    public void testRegisterClass3() {
    	this.admin.createClass("ECS3", 2017, "Instructor3", 1);
    	this.student.registerForClass("student3_1", "ECS3", 2017);
    	this.student.registerForClass("student3_2", "ECS3", 2017);
    	//System.out.println(this.admin.getClassCapacity("ECS3", 2017));
    	assertFalse(this.student.isRegisteredFor("student3_2", "ECS3", 2017));
    }

    //Testing drop classes
    //One case with all the correct stuff
    @Test
    public void testDropClass() {
        this.admin.createClass("ECS4", 2017, "Instructor4", 15);
        this.student.registerForClass("student4", "ECS4", 2017);
        this.student.dropClass("student4", "ECS4", 2017);
        assertFalse(this.student.isRegisteredFor("student4", "ECS4", 2017));
    }

    //one case where the date is in the past
   @Test
    public void testDropClass2() {
        this.admin.createClass("ECS5", 2016, "Instructor5", 15);
        this.student.registerForClass("student5", "ECS5", 2016);
        this.student.dropClass("student5", "ECS5", 2016);
        assertTrue(this.student.isRegisteredFor("student5", "ECS5", 2016));
    }


    //Submit Homeowrk
    //One correct case
    @Test
    public void testSubmitHomework() {
        this.admin.createClass("ECS6", 2017, "Instructor6", 15);
        this.student.registerForClass("student6", "ECS6", 2017);
        this.instructor.addHomework("Instructor6", "ECS6", 2017, "HW6",
                "Hw 6 Description");
        this.student.submitHomework("student6", "HW6", "answer 6", "ECS6",
                2017);
        assertTrue(this.student.hasSubmitted("student6", "HW6", "ECS6", 2017));
    }

    //One where homework does not exist

    @Test
    public void testSubmitHomework2() {
        this.admin.createClass("ECS7", 2017, "Instructor7", 15);
        this.student.registerForClass("student7", "ECS7", 2017);
       // this.instructor.addHomework("Instructor7", "ECS7", 2017, "HW7", "Hw 7 Description");
        this.student.submitHomework("student7", "HW7", "answer 7", "ECS7",
                2017);
        //System.out.println(this.student.hasSubmitted("student7", "HW7", "ECS7", 2017));
        assertFalse(this.student.hasSubmitted("student7", "HW7", "ECS7", 2017));
    }
    //one where student is not registered for class
    //Should fail
    @Test
    public void testSubmitHomework3() {
        this.admin.createClass("ECS8", 2017, "Instructor8", 15);
        //this.student.registerForClass("student8", "ECS8", 2017);
        this.instructor.addHomework("Instructor8", "ECS8", 2017, "HW8", "Hw 8 Description");
        this.student.submitHomework("student8", "HW8", "answer 8", "ECS8",
                2017);
        //System.out.println(this.student.hasSubmitted("student8", "HW8", "ECS8", 2017));
        assertFalse(this.student.hasSubmitted("student8", "HW8", "ECS8", 2017));
    }

    //one where class is not in the current year(past year)
    //Should Fail
    @Test
    public void testSubmitHomework4() {
        this.admin.createClass("ECS9", 2016, "Instructor9", 15);
        this.student.registerForClass("student9", "ECS9", 2016);
        this.instructor.addHomework("Instructor9", "ECS9", 2016, "HW9", "Hw 9 Description");
        this.student.submitHomework("student9", "HW9", "answer 9", "ECS9",
                2016);
        //System.out.println(this.student.hasSubmitted("student9", "HW9", "ECS9", 2016));
        assertFalse(this.student.hasSubmitted("student9", "HW9", "ECS9", 2016));
    }    

    //one where class has not even started
    //one where class is not in the current year(past year)
    //Should Fail
    @Test
    public void testSubmitHomework5() {
        this.admin.createClass("ECS10", 2018, "Instructor10", 15);
        this.student.registerForClass("student10", "ECS10", 2018);
        this.instructor.addHomework("Instructor10", "ECS10", 2018, "HW10", "Hw 10 Description");
        this.student.submitHomework("student10", "HW10", "answer 10", "ECS10",
                 2018);
        //System.out.println(this.student.hasSubmitted("student10", "HW10", "ECS10", 2018));
        assertFalse(this.student.hasSubmitted("student10", "HW10", "ECS10", 2018));
    }    
    
}
