import java.io.*;
import java.util.*;
class IntList {
	int[] list;
	IntList(int[] list) {
		this.list = new int[0];
	}
	void add(int x) {
		this.list = Arrays.copyOf(this.list, this.list.length + 1);
		this.list[this.list.length - 1] = x;
	}
	void set(int position, int x) {
		this.list[position] = x;
	}
	int get(int position) {
		return this.list[position];
	}
	int length() {
		return this.list.length;
	}
}