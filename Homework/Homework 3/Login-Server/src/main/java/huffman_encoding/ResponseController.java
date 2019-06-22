package huffman_encoding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ResponseController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/encode")
    public ResponseModel compress(@RequestParam(value="text", defaultValue="Huffman Encoding") String text) {

        ResponseModel responseModel = restTemplate.getForObject("http://encoding-service/encode?text=" + text, ResponseModel.class);
        return responseModel;

    }
  
}
