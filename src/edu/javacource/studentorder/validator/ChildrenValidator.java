package edu.javacource.studentorder.validator;

import edu.javacource.studentorder.domain.children.AnswerChildren;
import edu.javacource.studentorder.domain.StudentOrder;

public class ChildrenValidator {
    public static AnswerChildren checkChildren(StudentOrder so){
        return new AnswerChildren();
    }
}
