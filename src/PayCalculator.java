import java.util.HashMap;
import java.util.Map;

public class PayCalculator{
    static int calculatePay(String employeeName, Map<String, Integer> expenses) {
            int sum = 0;
            sum += expenses.get("Gym");
            sum += expenses.get("Food");
            sum += expenses.get("Phone");
            sum += expenses.get("Socks");
            sum += expenses.get("Uber");
            return sum;
        }
}