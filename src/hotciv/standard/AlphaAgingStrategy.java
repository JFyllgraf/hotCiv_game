package hotciv.standard;

public class AlphaAgingStrategy implements AgingStrategy {

    @Override
    public int incrementAge(int age) {
        return age + 100;
    }
}
