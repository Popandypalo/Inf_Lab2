package hamming;
public class HammingErrorDetector implements ErrorDetection {
    private HammingStatus status = HammingStatus.NO_ERROR;

    @Override
    public int detectError(int[] codeword) {
        int s1 = codeword[6] ^ codeword[4] ^ codeword[2] ^ codeword[0];
        int s2 = codeword[6] ^ codeword[5] ^ codeword[2] ^ codeword[1];
        int s3 = codeword[6] ^ codeword[5] ^ codeword[4] ^ codeword[3];

        int errorPosition = (s3 << 2) | (s2 << 1) | s1;

        status = errorPosition != 0 ? HammingStatus.ERROR : HammingStatus.NO_ERROR;

        return errorPosition;
    }

    @Override
    public HammingStatus getStatus() {
        return status;
    }
}

