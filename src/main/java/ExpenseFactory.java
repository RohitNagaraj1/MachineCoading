public class ExpenseFactory {
    public static Expense factoryMethod(String expense)
    {
        if (expense.contains("EQUAL"))
            return new EqualExpense();
        else if (expense.contains("EXACT"))
            return new ExactExpense();
        else
            return new PercentExpense();
    }
}
