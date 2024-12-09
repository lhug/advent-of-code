package io.github.lhug.adventofcode.twentytwentyfour.ninth;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

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

	public String defragment(String in) {
		var array = in.toCharArray();
		int fileId = Integer.MAX_VALUE;
		for(int i = array.length - 1; i >= 0; i--) {
			var current = array[i];
			if(current != '.') {
				int value = current - '0';
				if(value < fileId) {
					fileId = value;
					var fileLength = lengthOfFile(array, i, current);
					var index = freeBlockWithLength(array, fileLength, i);
					if (index != -1) {
						for(int j = 0; j < fileLength; j++) {
							array[index + j] = current;
							array[i - j] = '.';
						}
					}
					i = i - fileLength +1;
				}
			}
		}
		return new String(array);
	}

	private int freeBlockWithLength(char[] array, int length, int maxIndex) {
		int start = -1;
		int space = 0;

		for (int i = 0; i <= array.length; i++) {
			if (i < array.length && array[i] == '.' && i < maxIndex) {
				if (start == -1) {
					start = i;
				}
				space++;
				if (space == length) {
					return start;
				}
			} else {
				start = -1;
				space = 0;
			}
		}
		return -1;
	}

	private int lengthOfFile(char[] array, int start, char value) {
		int count = 0;
		for (int i = start; i >= 0 ; i--) {
			if(array[i] == value) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}

	public long phaseTwo(String input) {
		var parsed = parse(input);
		var defragmented = defragment(parsed);
		return checksum(defragmented);
	}
}
