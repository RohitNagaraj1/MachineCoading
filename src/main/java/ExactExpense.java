public class ExactExpense implements Expense {
    @Override
    public void evaluate(SplitWise sw, String expense) {
        String[] expenseFormat = expense.split(" ");
        String paidUserId = expenseFormat[1];
        Double amount = Double.parseDouble(expenseFormat[2]);

        User paidUser = sw.getUserFromUserId(paidUserId);
        sw.balance.put(paidUser, sw.balance.get(paidUser) + amount);

        int numberOfSplit = Integer.parseInt(expenseFormat[3]);
        for (int i = 1; i <= numberOfSplit; i++)
        {
            User receivedUser = sw.getUserFromUserId(expenseFormat[3 + i]);
            Double exactValue = Double.parseDouble(expenseFormat[3 + numberOfSplit + 1 + i]);
            sw.balance.put(receivedUser, sw.balance.get(receivedUser) - exactValue);
        }

        sw.simplify();
    }
}
