package huffman_encoding;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class EncodingTest {
    @Test
    public void compressTest() throws Exception {
        Encoding test = new Encoding();
        Class<Encoding> cl = Encoding.class;
        Method declaredMethod = cl.getDeclaredMethod("compress", String.class, Character.class);

        declaredMethod.setAccessible(true);
        Object invoke = declaredMethod.invoke(test,"abc",'t');
        declaredMethod.setAccessible(false);
        Assert.assertEquals("a=10\nb=11\nc=0\n",invoke);
    }
}