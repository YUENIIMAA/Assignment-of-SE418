package se418.richard.serviceserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @RequestMapping("/encode")
    public Encoding compress(@RequestParam(value="text", defaultValue="Huffman Encoding") String text) {
        return new Encoding(text);
    }

}
