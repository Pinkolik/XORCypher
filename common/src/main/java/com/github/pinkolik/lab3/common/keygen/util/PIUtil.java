package com.github.pinkolik.lab3.common.keygen.util;

/**
 * Created by IntelliJ IDEA.
 * User: rsv
 * Date: 18.11.2019
 * Time: 19:15
 */
public class PIUtil {

    public static byte computePiNthDigit(final int n) {
        // Compute Pi
        double pi = 4.0 * sum(1, n) - 2.0 * sum(4, n) - sum(5, n) - sum(6, n);
        pi = pi - (int) pi + 1.0;

        return getLowByteFromDouble(pi);
    }

    private static double sum(final int i, final int n) {
        double s = 0.0;        // Summation of Total, Left
        double t;              // Each term of right summation
        int r;              // Denominator
        int k;              // Loop index
        double EPS = 1.0e-17;  // Loop-exit accuration of the right summation

        // Left Sum (0 ... d)
        for (k = 0; k <= n; k++) {
            r = 8 * k + i;
            t = (double) computeModularExponentiation(16, n - k, r);
            t /= r;
            s += t - (int) t;
            s -= (int) s;
        }

        // Right sum (d + 1 ...)
        while (true) {
            r = 8 * k + i;
            t = Math.pow(16.0, n - k);
            t /= r;
            if (t < EPS) {
                break;
            }
            s += t;
            s -= (int) s;
            k++;
        }

        return s;
    }

    private static byte getLowByteFromDouble(final double doubleNumber) {
        double y = Math.abs(doubleNumber);
        return (byte) (16.0 * (y - Math.floor(y)));
    }

    public static byte findFirstAppearance(final int offset, final byte lowByte) {
        if (lowByte > (byte) 0x0F) {
            throw new IllegalArgumentException("Only first four bits of byte are accepted");
        }
        int resultOffset = 0;
        while (true) {
            byte nthDigit = computePiNthDigit(offset + resultOffset);
            if (nthDigit == lowByte) {
                return (byte) resultOffset;
            }
            resultOffset++;
        }
    }

    private static long computeModularExponentiation(final int base, final int exp, final int mod) {
        long ans;

        if (exp == 0) {
            return 1;
        }

        ans = computeModularExponentiation(base, exp / 2, mod);
        ans = (ans * ans) % mod;
        if ((exp % 2) == 1) {
            ans = (ans * base) % mod;
        }

        return ans;
    }
}
