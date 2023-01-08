import java.text.DecimalFormat;

public class PercentExpense implements Expense {
    @Override
    public void evaluate(SplitWise sw, String expense) {
        String[] expenseFormat = expense.split(" ");
        String paidUserId = expenseFormat[1];
        Double amount = Double.parseDouble(expenseFormat[2]);

        User paidUser = sw.getUserFromUserId(paidUserId);
        sw.balance.put(paidUser, sw.balance.get(paidUser) + amount);

        int numberOfSplit = Integer.parseInt(expenseFormat[3]);
        double splitAmount = 0.0;

        for (int i = 1; i <= numberOfSplit; i++)
        {
            User receivedUser = sw.getUserFromUserId(expenseFormat[3 + i]);
            double receivedAmount = ExpenseSummary.roundTwoDecimals(Double.parseDouble(expenseFormat[3 + numberOfSplit + 1 + i]) * amount / 100);
            splitAmount += receivedAmount;
            sw.balance.put(receivedUser, sw.balance.get(receivedUser) - receivedAmount);
        }

        if (splitAmount != amount)
        {
            User receivedUser = sw.getUserFromUserId(expenseFormat[4]);
            sw.balance.put(receivedUser, sw.balance.get(receivedUser) - ExpenseSummary.roundTwoDecimals(amount - splitAmount));
        }

        sw.simplify();
    }
}
