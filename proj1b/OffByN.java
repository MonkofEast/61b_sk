public class OffByN implements CharacterComparator {
    int N;
    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int charDiff = Math.abs(x - y);
        if (charDiff == N) return true;
        return false;
    }
}
