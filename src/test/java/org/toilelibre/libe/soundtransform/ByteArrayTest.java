package org.toilelibre.libe.soundtransform;

import java.util.Arrays;

import org.junit.Test;
import org.toilelibre.libe.soundtransform.infrastructure.service.observer.Slf4jObserver;
import org.toilelibre.libe.soundtransform.ioc.SoundTransformTest;

public class ByteArrayTest extends SoundTransformTest {

    byte [] array1 = { 42, -127, 23, 0 };

    private int byteArrayToInt (byte [] b) {
        int i = 0;
        for (int j = 0 ; j < b.length ; j++) {
            i += (b [j]) << (j * 8);
        }
        return i;
    }

    private byte [] intToByteArray (int n) {
        final byte [] b = new byte [4];
        int k = n;
        for (int i = 0 ; i < b.length ; i++) {
            b [i] = (byte) (k % 256);
            k >>= 8;
        }
        return b;
    }

    @Test
    public void test1 () {
        int i = this.byteArrayToInt (this.array1);
        i -= 320;
        final byte [] array2 = this.intToByteArray (i);
        new Slf4jObserver ().notify (Arrays.toString (this.array1));
        new Slf4jObserver ().notify ("" + i);
        new Slf4jObserver ().notify (Arrays.toString (array2));
    }
}