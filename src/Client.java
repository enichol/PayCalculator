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

        int pay = payCalculator.calculatePay("alpha bravo", expenses);
        System.out.println("$" + pay + ".00");
    }
}
