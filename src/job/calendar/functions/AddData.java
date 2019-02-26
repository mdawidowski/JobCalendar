package job.calendar.functions;

public class AddData {
    public static void insertData(String name, int amount){
        if (!name.isEmpty()){
            System.out.println(name + " + " + amount);
        } else {
            System.out.println("Błędnie podana wartość");
        }
    }
}
