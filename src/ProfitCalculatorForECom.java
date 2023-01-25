import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


class ProfitCalculatorForECom {
    // returns sum of arithmetic sequence from start to stop (exclusive).
    private static int seqSum(int start, int stop) {
        return (start + stop ) * (stop - start + 1) / 2;
    }

    public static int findProfit(int[] inventory, int order) {
        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int item : inventory)
            counter.merge(item, 1, Integer::sum);
        // (stock, suppliers count) in stock desc
        List<Entry<Integer, Integer>> stocks = counter.entrySet().stream()
                .sorted(Comparator.comparingInt(Entry<Integer, Integer>::getKey).reversed()).toList();


        // number of suppliers with highest stock
        int suppliers = 0;
        // total profit
        int profit = 0;
        for(int i=0;i<stocks.size() && order>0;i++){
            int curSupplierStock=stocks.get(i).getKey();
            int nextSupplierStock=(stocks.size()!=(i+1))?stocks.get(i+1).getKey():0;
            suppliers+=stocks.get(i).getValue();
            int stocksFromCurrentSupplier = curSupplierStock - nextSupplierStock;
            int totalOrderFullFilled=stocksFromCurrentSupplier*suppliers;
            int orderRemaining=order-totalOrderFullFilled;
            int remain=0;
            if(orderRemaining<0) {
                nextSupplierStock = curSupplierStock - order / suppliers;
                remain=order%suppliers;
            }
            int profitFromCurSupplier=seqSum(nextSupplierStock+1,curSupplierStock)*suppliers + remain*nextSupplierStock;
            profit+=profitFromCurSupplier;
            order=orderRemaining;
        }
        return profit;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        int [] inventory={6,6,3};
        int order=14;
        int res = findProfit(inventory, order);
        System.out.println(res);
    }
}

