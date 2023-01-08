import java.text.DecimalFormat;

public class ExpenseSummary {
    private SplitWise sw;
    public void setSplitWiseValue(SplitWise sw)
    {
        this.sw = sw;
    }
    public void show()
    {
        if (sw == null || sw.whoOwesToWhom == null || sw.whoOwesToWhom.size() == 0)
        {
            System.out.println("No balances");
            return;
        }

        for (String value : sw.whoOwesToWhom)
            System.out.println(value);
    }
    public void show(String userId)
    {
        if (sw == null || sw.owesById == null || !sw.owesById.containsKey(userId))
        {
            System.out.println("No balances");
            return;
        }

        for (String value : sw.owesById.get(userId))
            System.out.println(value);
    }

    public static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
}
