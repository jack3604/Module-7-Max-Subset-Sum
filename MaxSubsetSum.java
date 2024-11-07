import java.util.Scanner;
import java.util.Arrays;

class Result {
	int Start = 0;
	int End = 0;
	int Sum = 0;
	Result() {}
}

class MaxSubsetSum {
	public static void println(String s) { System.out.println(s); }
	
	public static void main(String[] args) {
		// test input from assignment { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		// my test input { -2, 1, -3, 4, -1, 2, 1, -5, 4, 5, 6, 4, 5, 6, 4, 9 };
		// my test input { -2, 1, -3, 4, -1, 2, 1, -5, 9, 5, 6, 4, 5, 6, 4, 1 };
		// initialize scanner and get input as string array
		Scanner sc = new Scanner(System.in);
		int[] nums;
		boolean inputGood = false;
		
		while (!inputGood) {
			int window = GetWindow();

			System.out.print("Enter numbers: ");
			String[] input = sc.nextLine().split(", ");
			nums = new int[input.length];

			try {
				for (int i = 0; i < input.length; i++) {
					nums[i] = Integer.parseInt(input[i]);
					if (i == input.length-1) { inputGood = true; }
				}
			} catch (Exception e) {
				int s = e.toString().indexOf("\"");
				int en = e.toString().lastIndexOf("\"") + 1;
				System.out.println(e.toString().substring(s, en) + " is not a number. Try again.");
			}
			
			if (inputGood) {
				//print starting array
				String out = String.join(", ", input);
				println(out);
				Result outres = FindMaxSubsetSum(nums, window);
				println(outres.Start + " " + outres.End + " " + outres.Sum);
			}
		}
	}

	public static int GetWindow() {
		Scanner sc = new Scanner(System.in);
		int window = 0;
		boolean inputGood = false;
		while (!inputGood) {
			System.out.print("Enter size of the array: ");
			try {
				window = sc.nextInt();
				inputGood = true;
			} catch (Exception e)
			{
				sc.next();
			}
		}

		return window;
	}

	public static int GetSum(int[] arr) {
		int sum = 0;
		for (int i : arr) {
			sum += i;
		}
		return sum;
	}
	
	public static Result FindMaxSubsetSum(int[] nums, int window) {
		Result res = new Result();
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < i + window; j++) {
				int[] copy = Arrays.copyOfRange(nums, i, j + 1);
				int sum = GetSum(copy);
				if (sum > res.Sum) {
					res.Start = i;
					res.End = j;
					res.Sum = sum;
				}
			}
		}
		return res;
	}
}
