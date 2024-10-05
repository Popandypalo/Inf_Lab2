package hamming;
public class HammingCode {
    private final ErrorDetection errorDetector;

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
            System.out.println("Ошибка обнаружена в бите:      " + errorPosition);
            codeword[errorPosition - 1] ^= 1;
            System.out.println("Исправленное кодовое слово:    " + BitUtils.bitArrayToStringWithHighlight(codeword, errorPosition));
        } else {
            System.out.println("Ошибок не обнаружено.");
        }

        System.out.println("Статус:                        " + errorDetector.getStatus().getMessage());

        return extractDataBits(codeword);
    }

    private int[] extractDataBits(int[] codeword) {
        return new int[]{codeword[2], codeword[4], codeword[5], codeword[6]};
    }
}