import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.nimbus.State;
import java.util.HashMap;
import java.util.Map;

public class PayCalculator{
    Map employees = new HashMap<String, Employee>();
    Map stateTaxes = new HashMap<String, StateTax>();
    Map salaries = new HashMap<String, Integer>();
    String[] taxOfStates = {"ON=3.3,2.44,22", "QU=2.5,2.11,19.8", "BC=2.66,2.22,19.7", "NF=2,1.83,19.7"};

    // Insert test data in PayCalculator constructor to simulate actual records
    public PayCalculator(){
        employees.put("alpha bravo", new Employee("alpha", "bravo", "ON", "Engineering", "Engineer"));
        employees.put("jhon tim", new Employee("jhon", "tim", "ON", "Engineering", "Sr. Engineer"));
        employees.put("alexa tesla", new Employee("alexa", "tesla", "QU", "Project Management", "Project Manager"));
        employees.put("robin hood", new Employee("robin", "hood", "BC", "Accounting", "Accountant"));

        salaries.put("Jr. Engineer", 40000);
        salaries.put("Engineer", 50000);
        salaries.put("Sr. Engineer", 65000);
        salaries.put("Project Manager", 55000);
        salaries.put("Sr. Project Manager", 65000);
        salaries.put("Accountant", 50000);

        parseStateTaxes(taxOfStates);
    }

    Employee getEmployee (String employeeName) {
        Employee employee;

        try {
            employee = (Employee) this.employees.get(employeeName);
        } catch (Exception e) {
            throw new java.lang.Error("employee record not found for " + employeeName);
        }

        return employee;
    }

    StateTax getStateTax (String stateCode) {
        StateTax stateTax;

        try {
            stateTax = (StateTax) this.stateTaxes.get(stateCode);
        } catch (Exception e) {
            throw new java.lang.Error("state tax record not found for " + stateCode);
        }

        return stateTax;
    }

    float getSalary (String jobTitle) {
        int salary;
        try {
            salary = (int) this.salaries.get(jobTitle);
            return salary;
        } catch (Exception e) {
            throw new java.lang.Error("salary record not found for " + jobTitle);
        }
    }

    int calculatePay(String employeeName, Map<String, Integer> expenses) {
        float salary = 0;
        float pay = 0;
        float taxes = 0;
        Employee employee;
        StateTax stateTax;

        employee = getEmployee(employeeName);
        stateTax = getStateTax(employee.residentState);
        salary = getSalary(employee.role); // this cases pay as float for calculation
        taxes = ((stateTax.EI + stateTax.health + stateTax.income) / 100) * salary;

        pay = salary - taxes;

        pay += calculateExpenses(expenses);

        return Math.round(pay);
    }

    // Parses state tax string and applies it to stateTaxes map
    private void parseStateTaxes(String[] stateTaxes) {
        for (String stateTax : taxOfStates) {
            String[] tmp1 = stateTax.split("=", 2);
            String state = tmp1[0];
            String[] tmp2 = tmp1[1].split(",", 3);
            float EI = Float.parseFloat(tmp2[0]);
            float health = Float.parseFloat(tmp2[1]);
            float income = Float.parseFloat(tmp2[2]);

            // Some error handling wouldn't hurt

            this.stateTaxes.put(state, new StateTax(state, EI, health, income));
        }
    }

    private int calculateExpenses (Map<String, Integer> expenses) {
        int reimbursement = 0;
        int gymExpenses;
        int phoneExpenses;
        int UberExpenses;
        int foodExpenses;

        if (expenses.get("Gym") != null) {
            gymExpenses = expenses.get("Gym");
            reimbursement += (gymExpenses <= 25) ? gymExpenses : 25;
        }

        if (expenses.get("Phone") != null) {
            phoneExpenses = expenses.get("Phone");
            reimbursement += (phoneExpenses <= 60) ? phoneExpenses : 60;
        }

        if (expenses.get("Uber") != null) {
            UberExpenses = expenses.get("Uber");
            reimbursement += UberExpenses;
        }

        if (expenses.get("Food") != null) {
            foodExpenses = expenses.get("Food");
            reimbursement += foodExpenses;
        }

        return reimbursement;
    }
}