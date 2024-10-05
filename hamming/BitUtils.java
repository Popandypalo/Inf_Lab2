package hamming;
public class BitUtils {
    public static int[] stringToBitArray(String input) {
        if (input.length() != 7) {
            throw new IllegalArgumentException("Длина входной строки должна быть 7 символов");
        }

        int[] bits = new int[7];
        for (int i = 0; i < 7; i++) {
            char ch = input.charAt(i);
            if (ch != '0' && ch != '1') {
                throw new IllegalArgumentException("Ввод может содержать только символы '0' и '1'");
            }
            bits[i] = Character.getNumericValue(ch);
        }
        return bits;
    }

    public static String bitArrayToString(int[] bits) {
        StringBuilder sb = new StringBuilder();
        for (int bit : bits) {
            sb.append(bit);
        }
        return sb.toString();
    }

    // Метод для вывода массива битов с выделением исправленного бита
    public static String bitArrayToStringWithHighlight(int[] bits, int errorPosition) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bits.length; i++) {
            if (i == errorPosition - 1) {
                sb.append("\u001B[31m").append(bits[i]).append("\u001B[0m");
            } else {
                sb.append(bits[i]);
            }
        }
        return sb.toString();
    }
}