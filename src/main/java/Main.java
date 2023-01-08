public class Main {
    public static void main(String[] args) {
        SplitWise sw = new SplitWise();
        ExpenseSummary expenseSummary = new ExpenseSummary();
        expenseSummary.show();
        expenseSummary.show("u1");
        // enter 4 users along with their details
        sw.getUsers();
        expenseSummary.setSplitWiseValue(sw);
        // Expense in the format:
        // EXPENSE <user-id-of-person-who-paid> <no-of-users> <space-separated-list-of-users> <EQUAL/EXACT/PERCENT> <space-separated-values-in-case-of-non-equal>
        sw.createExpense("EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL");
        expenseSummary.show("u4");
        expenseSummary.show("u1");
        sw.createExpense("EXPENSE u1 1250 2 u2 u3 EXACT 370 880");
        expenseSummary.show();
        sw.createExpense("EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20");
        expenseSummary.show("u1");
        expenseSummary.show();
        sw.createExpense("EXPENSE u1 100 3 u1 u2 u3 EQUAL");
        expenseSummary.show();
    }
}
