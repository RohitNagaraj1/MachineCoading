import java.util.*;

public class SplitWise {
    ArrayList<User> users;
    int numberOfUser;
    HashMap<User, Double> balance = new HashMap<>();
    HashMap<String, ArrayList<String>> owesById;
    ArrayList<String> whoOwesToWhom;
    Scanner sc = new Scanner(System.in);
    public User getUserFromUserId(String userId)
    {
        if (users != null)
        {
            for (User user : users)
            {
                if (user.getId().equals(userId))
                    return user;
            }
        }
        return null;
    }
    public void getUsers()
    {
        System.out.println("Enter the no of Users");
        this.numberOfUser = Integer.parseInt(sc.nextLine());
        users = new ArrayList<>();
        System.out.println("Enter User details");

        for (int i = 1; i <= numberOfUser; i++)
        {
            User tempUser = new User();
            System.out.println("Enter User " + i + "  details such as Name Email PhoneNumber separated by space");
            tempUser.setId("u" + i);
            String[] details = sc.nextLine().split(" ");
            tempUser.setName(details[0]);
            tempUser.setEmail(details[1]);
            tempUser.setPhoneNumber(details[2]);
            users.add(tempUser);
            balance.put(tempUser, 0.0);
        }
    }
    public void createExpense(String expenseValue)
    {
        Expense expense = ExpenseFactory.factoryMethod(expenseValue);
        expense.evaluate(this, expenseValue);
    }
    public void simplify()
    {
        owesById = new HashMap<>();
        whoOwesToWhom = new ArrayList<>();
        HashMap<User, Double> pays = new HashMap<>();
        HashMap<User, Double> receives = new HashMap<>();

        for (Map.Entry<User, Double> entry : balance.entrySet())
        {
            if (entry.getValue() < 0)
            {
                pays.put(entry.getKey(), entry.getValue());
            }
            else if (entry.getValue() > 0)
            {
                receives.put(entry.getKey(), entry.getValue());
            }
        }

        for (Map.Entry<User, Double> pay : pays.entrySet())
        {
            User payUser = pay.getKey();
            double payableAmount = pay.getValue();

            for (Map.Entry<User, Double> receive : receives.entrySet())
            {
                User receiveUser = receive.getKey();
                if (Math.abs(payableAmount) <= Math.abs(receive.getValue()))
                {
                    String owesString = payUser.getId() + " owes " + receiveUser.getId() + " : " + ExpenseSummary.roundTwoDecimals(Math.abs(payableAmount));
                    fillOwesValue(owesString, payUser.getId(), receiveUser.getId());
                    receives.put(receiveUser, receives.get(receiveUser) - Math.abs(payableAmount));
                    pays.put(payUser, 0.0);
                    break;
                }
                else if (receive.getValue() != 0.0)
                {
                    String owesString = payUser.getId() + " owes " + receiveUser.getId() + " : " + ExpenseSummary.roundTwoDecimals(Math.abs(receive.getValue()));
                    fillOwesValue(owesString, payUser.getId(), receiveUser.getId());
                    payableAmount += Math.abs(receive.getValue());
                    receives.put(receiveUser, 0.0);
                    pays.put(payUser, pays.get(payUser) + Math.abs(receive.getValue()));
                }
            }
        }
    }
    public void fillOwesValue(String owesString, String payUser, String receiveUser)
    {
        whoOwesToWhom.add(owesString);

        if (!owesById.containsKey(payUser))
        {
            owesById.put(payUser, new ArrayList<>());
        }
        owesById.get(payUser).add(owesString);

        if (!owesById.containsKey(receiveUser))
        {
            owesById.put(receiveUser, new ArrayList<>());
        }
        owesById.get(receiveUser).add(owesString);
    }
}
