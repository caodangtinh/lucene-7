package test;

public class App {

	public static void main(String[] args) {
		System.out.println(checkNameStartWith("Jonny Nguyen", "N"));
	}

	public static boolean checkNameStartWith(String name, String startWith) {
		if (name == null || name.length() <= 0)
			return false;
		String[] _arr = name.split(" ");
		return _arr[_arr.length - 1].startsWith(startWith);
	}
}
