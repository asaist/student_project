import domain.Adult;
import domain.StudentOrder;

public class SaveStudentOrder {
    public static void main(String[] args) {

        StudentOrder so = new StudentOrder();

        long ans = saveStudentOrder(so);
        System.out.println(ans);

    }
    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
        System.out.println("SaveStudentOrder 1");
        return answer;
    }
    static StudentOrder buildStudentOrder(long id){
        StudentOrder so = new StudentOrder();
        so.setStudentOrderId(id);
        Adult husband = new Adult("","","",null);


        return so;
    }
}
