package beat;

public class PrintLog {
	public static void ShowThreadID() {
		System.out.printf("This thread id = %d\n", Thread.currentThread().getId());
	}
	
	public static void ShowThreadID(String model) {
		System.out.printf("[%s], This thread id = %d\n", model, Thread.currentThread().getId());
	}
}
