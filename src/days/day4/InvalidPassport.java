package days.day4;

public class InvalidPassport implements PassportCandidate{
    @Override
    public boolean isValidPassport() {
        return false;
    }
}
