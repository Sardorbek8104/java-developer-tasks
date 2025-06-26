public class Main {
    public static void main(String[] args) {
        double[] nums = {215.41, 151.44, 147.22, 937.13, 239.91, 491.45, 521.17, 768.99, 613.84};
        double target = 143.5;

        findTarget(nums, target);
    }

    public static void findTarget(double[] nums, double target) {
        int n = nums.length;
        int totalComb = 1 << n;

        for (int i = 1; i < totalComb; i++) {
            for (int j = 0; j < (1 << n); j++) {
                double sum = 0;
                StringBuilder expr = new StringBuilder();
                boolean used = false;

                for (int k = 0; k < n; k++) {
                    if (((i >> k) & 1) == 1) {
                        used = true;
                        boolean add = ((j >> k) & 1) == 1;
                        sum += add ? nums[k] : -nums[k];
                        expr.append(add ? " + " : " - ").append(nums[k]);
                    }
                }

                if (used && Math.abs(sum - target) < 0.01) {
                    System.out.println("Found: " + expr + " = " + sum);
                    return;
                }
            }
        }

        System.out.println("No combination found for target " + target);
    }
}
