package hamming;
public class HammingCode {
    private final ErrorDetection errorDetector;

    private static final String[] BIT_LABELS = {"r1", "r2", "i1", "r3", "i2", "i3", "i4"};

    public HammingCode(ErrorDetection errorDetector) {
        this.errorDetector = errorDetector;
    }

    public int[] decode(int[] codeword) {
        if (codeword.length != 7) {
            throw new IllegalArgumentException("Длина кодового слова должна быть 7 бит");
        }

        System.out.println("Исходное кодовое слово:        " + BitUtils.bitArrayToString(codeword));

        int errorPosition = errorDetector.detectError(codeword);

        if (errorPosition != 0) {
            String bitLabel = getBitLabel(errorPosition);
            System.out.println("\u001B[31mОшибка обнаружена в бите:      " + errorPosition + " (" + bitLabel + ")\u001B[0m");
            codeword[errorPosition - 1] ^= 1;
            System.out.println("Исправленное кодовое слово:    " + BitUtils.bitArrayToStringWithHighlight(codeword, errorPosition));
        } else {
            System.out.println("\u001B[32mОшибок не обнаружено.\u001B[0m");
        }

        System.out.println("Статус:                        " + errorDetector.getStatus().getMessage());

        return extractDataBits(codeword);
    }

    private String getBitLabel(int position) {
        // Индексы идут с 1 до 7, а массив с 0, поэтому нужно уменьшить индекс на 1
        return BIT_LABELS[position - 1];
    }

    private int[] extractDataBits(int[] codeword) {
        return new int[]{codeword[2], codeword[4], codeword[5], codeword[6]};
    }
}