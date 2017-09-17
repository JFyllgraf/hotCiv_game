package hotciv.standard;

public class AlphaAgeingStrategy implements AgeingStrategy {

    @Override
    public int incrementAge(int age) {
        return age + 100;
    }
}
