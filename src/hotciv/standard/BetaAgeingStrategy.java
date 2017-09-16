package hotciv.standard;

public class BetaAgeingStrategy implements AgeingStrategy {

    @Override
    public int incrementAge(int age) {
        if (age >= -4000 && age < -100){
            return age+100;
        } if (age == -100){
            return age + 99;
        } if (age == -1) {
            return age +2;
        } if (age == 1){
            return age + 49;
        } if (age >= 50 && age < 1750){
            return age + 50;
        } if (age >=1750 && age <1900){
            return age + 25;
        } if (age >=1900 && age < 1970){
            return age + 5;
        } if (age >= 1970){
            return age + 1;
        }
        return age;
    }
}
