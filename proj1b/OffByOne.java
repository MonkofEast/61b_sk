public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int charDiff = y - x;
        charDiff = Math.abs(charDiff);
        if (charDiff == 1) return true;
        return false;
    }
}
