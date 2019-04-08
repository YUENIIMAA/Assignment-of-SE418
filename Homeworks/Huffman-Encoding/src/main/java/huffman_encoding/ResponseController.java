package huffman_encoding;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    @RequestMapping("/encoding")
    public Compression compress(@RequestParam(value="text", defaultValue="Huffman Encoding") String text) {
        return new Compression(text);
    }
}