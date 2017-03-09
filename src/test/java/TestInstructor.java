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
        assertFalse(this.instructor.homeworkExists("ECS4444", 2017, "HW3"));
    }

    //Testing to see if homework exists. Class will exist but the homework does not
    //Test should pass
    @Test
    public void testAddHomework4() {
        this.admin.createClass("ECS4", 2017, "Instructor4", 15);
        this.instructor.addHomework("Instructor4", "ECS4", 2017, "HW4",
                "homework 4 description");
        assertFalse(this.instructor.homeworkExists("ECS4", 2017, "fail"));
    }


    //Test for adding homework to a class that does not exist/has not been added by admin. Condition should return false
    //Test should pass
    @Test
    public void testAddHomework5() {
        this.instructor.addHomework("Instructor5", "ECS5", 2017, "HW5",
                "homework 5 description");
        assertFalse(this.instructor.homeworkExists("ECS5", 2017, "HW5"));

    }

    //adding hw with null parameters i.e. null instructor/classname?
    //should fail
    @Test
    public void testAddHomework6() {
        this.admin.createClass("ECS6", 2017, "Instructor6", 15);
        this.instructor.addHomework("", "ECS6", 2017, "HW6",
                "homework 6 description");
        assertFalse(this.instructor.homeworkExists("ECS6", 2017, "HW6"));
    }

    //adding hw with null HW name
    //should fail
    @Test
    public void testAddHomework7() {
        this.admin.createClass("ECS7", 2017, "Instructor7", 15);
        this.instructor.addHomework("Instructor7", "ECS7", 2017, "",
                "homework 7 description");
        assertFalse(this.instructor.homeworkExists("ECS7", 2017, ""));
    }

    //Adding hw to a null class name
    //should fail in current testing 
    @Test
    public void testAddHomework8() {
        this.admin.createClass("", 2017, "Instructor8", 15);
        this.instructor.addHomework("Instructor8", "", 2017, "HW8",
                "homework 8 description");
        assertTrue(this.instructor.homeworkExists("", 2017, "HW8"));
    }

    //Testing add homework with correct parameters
    //Add a course through admin
    //assign an instructor to the class
    //add a student to the class
    //then add homework
    //Student submits homework
    //then assign grade
    @Test
    public void testAssignHomework() {
        this.admin.createClass("ECS9", 2017, "Instructor9", 15);
        this.instructor.addHomework("Instructor9", "ECS9", 2017, "HW9",
                "homework 9 description");
        this.student.registerForClass("student9", "ECS9", 2017);
        this.student.submitHomework("student9", "HW9", "answer9", "ECS9", 2017);
        this.instructor.assignGrade("Instructor9", "ECS9", 2017, "HW9",
                "student9", 100);
        assertTrue(this.instructor.getGrade("ECS9", 2017, "HW9", "student9") == 100);
    }

    //Assigning a grade for a student not in the class
    //should fail
    @Test
    public void testAssignHomework2() {
        this.admin.createClass("ECS10", 2017, "Instructor10", 15);
        this.instructor.addHomework("Instructor10", "ECS10", 2017, "HW10",
        "homework 10 description");
        this.student.registerForClass("student10", "ECS10", 2017);
        this.instructor.assignGrade("Instructor10", "ECS10", 2017, "HW10",
                "student_fail", 100);
       // System.out.println(this.instructor.getGrade("ECS10", 2017, "HW10", "student_fail"));
        if( this.instructor.getGrade("ECS10", 2017, "HW10", "student_fail") == 100 )
        {
            fail("student is not enrolled in course)");
        }
    }

    //give a negative number as a grade
    //Should fail
    @Test
    public void testAssignHomework3() {
        this.admin.createClass("ECS11", 2017, "Instructor11", 15);
        this.instructor.addHomework("Instructor11", "ECS11", 2017, "HW11",
                "HW11 Description");
        this.student.registerForClass("student11", "ECS11", 2017);
        this.student.submitHomework("student11", "HW11", "answer11", "ECS11", 2017);
        this.instructor.assignGrade("Instructor11", "ECS11", 2017, "HW11",
                "student11", -1);
        //System.out.println(this.instructor.getGrade("ECS11", 2017, "HW11", "student11"));
        if(this.instructor.getGrade("ECS11", 2017, "HW11", "student11") < 0)
        {
            fail("Student grade assigned is negative");
        }
        //assertFalse(this.instructor.getGrade("ECS11", 2017, "HW11", "student11") != 0);
    }

    //Give incorrect class name
    @Test
    public void testAssignHomework4() {
        this.admin.createClass("ECS12", 2017, "Instructor12", 15);
        this.instructor.addHomework("Instructor12", "ECS12", 2017, "HW12",
                "homework 12 description");
        this.student.registerForClass("student12", "ECS12", 2017);
        this.student.submitHomework("student12", "HW12", "answer 12", "ECS12", 2017);
        this.instructor.assignGrade("Instructor12", "ECS111", 2017, "HW12",
                "student12", 100);
        //System.out.println(this.instructor.getGrade("ECS12", 2017, "HW12", "student12"));
        assertNull(this.instructor.getGrade("ECS12", 2017, "HW12", "student12"));
    }

    //Teacher not registered to teach class and assigns homework
    //Should Fail
    @Test
    public void testAssignHomework5() {
        this.admin.createClass("ECS13", 2017, "Instructor13", 15);
        this.instructor.addHomework("Instructor13", "ECS13", 2017, "HW13" ,
                "hw 13 description");
        this.student.registerForClass("student13", "ECS13", 2017);
        this.student.submitHomework("student13", "HW13", "answer 13", "ECS13", 2017);
        this.instructor.assignGrade("Instructor_fail", "ECS13", 2017, "HW13",
                "student13", 100);
        //System.out.println(this.instructor.getGrade("ECS13", 2017, "HW13", "student13"));
        if((this.instructor.getGrade("ECS13", 2017, "HW13", "student13")) == 100) {
            fail("Instructor not assigned to class and cannot assign grade");
        }
    }


    //Assign homework to a class that has not been created
    @Test
    public void testAssignHomework6() {
        this.instructor.addHomework("Instructor14", "ECS14", 2017, "HW14" ,
                "hw 14 description");
        this.student.registerForClass("student14", "ECS14", 2017);
        this.student.submitHomework("student14", "HW14", "answer 14", "ECS14", 2017);
        this.instructor.assignGrade("Instructor14", "ECS14", 2017, "HW14",
                "student14", 100);
        //System.out.println(this.instructor.getGrade("ECS14", 2017, "HW14", "student14"));
        assertNull(this.instructor.getGrade("ECS14", 2017, "HW14", "student14"));
    }



    //give incorrect student a grade. Similar to giving a grade to student not in the class
    //should be false
    @Test
    public void testAssignHomework7() {
        this.admin.createClass("ECS15", 2017, "Instructor15", 15);
        this.instructor.addHomework("Instructor15", "ECS15", 2017, "HW15" ,
                "hw 15 description");
        this.instructor.assignGrade("Instructor15", "ECS15", 2017, "HW15",
                "student111", 100);
        //System.out.println(this.instructor.getGrade("ECS15", 2017, "HW15", "student111"));
        assertNull(this.instructor.getGrade("ECS15", 2017, "HW15", "student111"));
    }

    //passing a positive number over 100 as a grade
    @Test
    public void testAssignHomework8() {
        this.admin.createClass("ECS16", 2017, "Instructor16", 16);
        this.instructor.addHomework("Instructor16", "ECS16", 2017, "HW16" ,
                "hw 16 description");
        this.student.registerForClass("student16", "ECS16", 2017);
        this.student.submitHomework("student16", "HW16", "answer 16", "ECS16", 2017);
        this.instructor.assignGrade("Instructor16", "ECS16", 2017, "HW16",
                "student16", 101);
        //System.out.println(this.instructor.getGrade("ECS16", 2017, "HW16", "student16"));
        assertTrue(this.instructor.getGrade("ECS16", 2017, "HW16", "student16") == 101);
    }


}

