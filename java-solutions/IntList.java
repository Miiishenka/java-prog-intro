import java.io.*;
import java.util.*;
class IntList {
	int[] list;
	int size;	
	IntList(int[] list) {
		this.list = list;
		size = this.list.length;
	}
	void add(int x) {
		if (size == this.list.length) {
			this.list = Arrays.copyOf(this.list, this.list.length * 2 + 1);
		}
		this.list[size] = x;
		size++;
	}
	void set(int position, int x) {
		this.list[position] = x;
	}
	int get(int position) {
		return this.list[position];
	}
}