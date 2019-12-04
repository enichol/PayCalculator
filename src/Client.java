import java.util.HashMap;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        PayCalculator payCalculator = new PayCalculator();

        Map<String, Integer> expenses = new HashMap<String, Integer>();

        expenses.put("Gym", 20);
        expenses.put("Food", 22);
        expenses.put("Phone", 85);
        expenses.put("Socks", 98);
        expenses.put("Uber", 12);

        //int rtrn = payCalculator.calculatePay("Derek", expenses);
        //System.out.println(rtrn);

        Employee charles = new Employee("charles", "mcDude", "Ontario", "Accounting", "Accountant");
        System.out.println(charles.department);
    }
}
