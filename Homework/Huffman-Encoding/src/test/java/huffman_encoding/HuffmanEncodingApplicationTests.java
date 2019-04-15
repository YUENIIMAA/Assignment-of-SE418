package huffman_encoding;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "Richard",password = "Password",roles = "ADMIN")
public class HuffmanEncodingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testResponseController1() throws Exception {
		this.mockMvc.perform(get("/encode").param("text","abc"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().json("{'encodingTable':'a=10\\nb=11\\nc=0\\n','encodedContent':'10110'}"));
	}

	@Test
	public void testResponseController2() throws Exception {
		this.mockMvc.perform(get("/encode").param("text","11122222"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().json("{\"encodingTable\":\"1=0\\n2=1\\n\",\"encodedContent\":\"00011111\"}"));
	}

}
