public class SaveStudentOrder {
    public static void main(String[] args) {

        StudentOrder so1 = new StudentOrder();
        so1.hFirstName = "Алексей";
        so1.hLastName = "Петров";
        so1.wFirstName = "Галина";
        so1.wLastName = "Петрова";

        StudentOrder so2 = new StudentOrder();
        so2.hFirstName = "Алексей";
        so2.hLastName = "Петров";
        so2.wFirstName = "Галина";
        so2.wLastName = "Петрова";

        long ans1 = saveStudentOrder(so1);
        System.out.println(ans1);
        long ans2 = saveStudentOrder(so2);
        System.out.println(ans2);

    }
    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
        System.out.println("SaveStudentOrder 1");
        return answer;
    }
}
