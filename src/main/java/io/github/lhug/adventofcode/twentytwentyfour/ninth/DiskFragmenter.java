package io.github.lhug.adventofcode.twentytwentyfour.ninth;

import java.util.Arrays;

public class DiskFragmenter {

	public String parse(String input) {
		StringBuilder result = new StringBuilder();
		var asArray = input.toCharArray();
		int count = 0;
		for (int i = 0; i < asArray.length; i++) {
			int value = asArray[i] - '0';
			if((i & 1) == 0) {
				result.repeat(count++ +'0', value);
			} else {
				result.append(".".repeat(value));
			}
		}
		return result.toString();
	}

	public String compress(String in) {
		var array = in.toCharArray();
		var index = indexOfNextFreeBlock(array, 0);
		var maxIndex = array.length - freeBlocks(array);
		for(int i = array.length - 1; i >= maxIndex; i--) {
			var current = array[i];
			if(current != '.') {
				array[index] = current;
				array[i] = '.';
				index = indexOfNextFreeBlock(array, index);
			}
		}
		return new String(array);
	}

	private long freeBlocks(char[] array) {
		int count = 0;
		for(char c : array) {
			if(c == '.') {
				count++;
			}
		}
		return count;
	}

	private int indexOfNextFreeBlock(char[] array, int start) {
		for(int i = start; i < array.length; i++) {
			if(array[i] == '.') {
				return i;
			}
		}
		return -1;
	}

	public long checksum(String in) {
		long result = 0;
		var array = in.toCharArray();
		for (int i = 0; i < array.length; i++) {
			var current = array[i];
			if (current != '.') {
				result += Math.multiplyExact(i, current - '0');
			}
		}
		return result;
	}

	public long phaseOne(String input) {
		var parsed = parse(input);
		var compressed = compress(parsed);
		return checksum(compressed);
	}
}
