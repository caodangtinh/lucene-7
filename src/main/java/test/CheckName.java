package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckName {

	public boolean verifyLastNameStartsWith(List<String> userName, String startWith) {
		for (String name : userName) {
			if (!this.checkNameStartWith(name, startWith))
				return false;
		}
		return true;
	}

	public boolean checkNameStartWith(String name, String startWith) {
		if (name == null || name.length() <= 0)
			return false;
		name = name.toLowerCase();
		startWith = startWith.toLowerCase();
		List<String> _arr = new ArrayList<>(Arrays.asList(name.split(" ")));
		// remove first name
		_arr.remove(0);
		if (_arr.size() > 0) {
			return _arr.get(0).startsWith(startWith);
		} else {
			return false;
		}
	}
}
