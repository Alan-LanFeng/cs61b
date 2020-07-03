public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) != 1) {
            return false;
        }
        return true;
    }
}
