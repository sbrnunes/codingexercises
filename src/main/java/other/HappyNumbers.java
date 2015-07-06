package other;

import java.util.HashSet;
import java.util.Set;

/**
 * A ideia deste exercicio e verificar se um determinado numero e Happy ou nao.
 * 
 * Um numero e happy se a soma do quadrado de cada digito desse numero e igual a 1, sendo que, em cada iteracao, esse numero 
 * e substituido pela soma calculada. Se o numero original voltar a aparecer na soma, o numero nunca podera ser Happy.
 * 
 * Ex: 19 : 1^2 + 9^2 = 82
 *     82 : 8^2 + 2^2 = 68
 *     68 : 6^2 + 8^2 = 100
 *     100 : 1^2 + 0 + 0 = 1 => Happy Number
 */
public class HappyNumbers {


    public static void main(String[] args) {

        System.out.println(isHappy(19));
        System.out.println(isHappy(4));
    }


    private static boolean isHappy(int number) {

        Set<Integer> accumulator = new HashSet<>();

        if(number == 1) {
            return true;
        }

        accumulator.add(number);

        while(true) {

            String str = "" + number;

            int sum = 0;
            for(int i = 0; i < str.length(); i++) {
                sum += Integer.valueOf("" + str.charAt(i)) * Integer.valueOf("" + str.charAt(i));
            }

            if(accumulator.contains(sum)) {
                return false;
            }

            accumulator.add(sum);

            if(sum == 1) {
                return true;
            } else {
                number = sum;
            }

        }

    }


}
