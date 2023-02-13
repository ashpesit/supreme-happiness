import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {


        Main s = new Main();
//        balanced array
//        int[] num = {5,5,2,5,8};
//        int va=s.balanceArray(num);
//        System.out.println(va);
        //5,6,7,8,9,10,1,2,3
        int[] num = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int idx = s.findInRotated(num, 10);
        System.out.println(idx);
    }

    //    public static void main(String[] args) {
//        Scratch s= new Scratch();
//        Map<Character, List<Character>> input=new HashMap<>();
//        input.put('A', Arrays.asList('B','C'));
//        input.put('B', List.of('E'));
//        input.put('C',Arrays.asList('D','E','F'));
//        input.put('G', List.of('C'));
//        System.out.println(s.findBuildOrder(input));
//    }
    public List<Character> findBuildOrder(Map<Character, List<Character>> input) {
        List<Character> result = new ArrayList<>();
        for (Character c : input.keySet()) {
            helper(c, result, input);
        }
        return result;
    }

    public void helper(Character c, List<Character> result, Map<Character, List<Character>> input) {
        if (result.contains(c)) return;
        if (!input.containsKey(c)) {
            result.add(c);
            return;
        }
        if (input.get(c).isEmpty()) {
            result.add(c);
            return;
        }
        for (Character ch : input.get(c)) {
            helper(ch, result, input);
        }
        result.add(c);
    }

    public int findInRotated(int[] arr, int key) {
        int h = arr.length - 1;
        int l = 0;
        while (h >= l) {
            int m = l + (h - l) / 2;
//            System.out.println(arr[l] + " " + arr[m] + " " + arr[h]);
            if (arr[m] == key) return m;
            if (arr[m] < arr[l] && arr[h] < key) {
                h = m - 1;
            } else if (arr[h] < arr[m] && key < arr[l]) {
                l = m + 1;
            } else {
                if (arr[m] > key) {
                    h = m - 1;
                } else l = m + 1;
            }
        }
        return -1;
    }

    public int balanceArray(int[] nums) {
        int n = nums.length;
        int[] leftOdd = new int[n], leftEven = new int[n];
        int[] rightOdd = new int[n], rightEven = new int[n];
        int odd = 0, even = 0;
        for (int i = 0; i < n; i++) {
            leftOdd[i] = odd;
            leftEven[i] = even;
            if (i % 2 == 0) even += nums[i];
            else odd += nums[i];
        }
        odd = 0;
        even = 0;
        for (int i = n - 1; i >= 0; i--) {
            rightOdd[i] = odd;
            rightEven[i] = even;
            if (i % 2 == 0) even += nums[i];
            else odd += nums[i];
        }
        for (int i = 0; i < n; i++)
            System.out.println(rightOdd[i] + " " + rightEven[i] + " " + leftOdd[i] + " " + leftEven[i]);
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (leftOdd[i] + rightEven[i] == leftEven[i] + rightOdd[i]) {
                System.out.println("id:" + i);
                count++;
            }
        }
        return count;
    }
    public static int findPairs(int[] A, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            int remainder = A[i] % k;
            int complement = (remainder == 0) ? 0 : k - remainder;
            if (map.containsKey(complement)) {
                count += map.get(complement);
            }
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }
        return count;
    }

}

