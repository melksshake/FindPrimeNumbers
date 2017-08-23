package com.natalia.melkonyan.findprimenumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Hulk on 23.08.2017.
 */

public class EratosphenBolterFuncs {

    public static List<Integer> calculatePrimeNumbers(int N) {
        List<Integer> calculatedPrimeNumbers = new ArrayList<>(N + 1);

        for (int i = 0; i < N + 1; i++) {
            calculatedPrimeNumbers.add(i);
        }

        for (int s = 2; s < N + 1; s++) {
            if (calculatedPrimeNumbers.get(s) != 0) {
                for (int j = s * s; j < N + 1; j += s) {
                    calculatedPrimeNumbers.set(j, 0);
                }
            }
        }
        calculatedPrimeNumbers.removeAll(Collections.singleton(0));
        calculatedPrimeNumbers.remove(0);

        return calculatedPrimeNumbers;
    }

    public static List<Integer> getAllPrimesFromInput(List<Integer> input, List<Integer> calculated) {

        for (int i = 0; i < input.size(); i++) {
            if (!calculated.contains(input.get(i))) {
                input.set(i, 0);
            }
        }

        input.removeAll(Collections.singleton(0));
        return input;
    }
}
