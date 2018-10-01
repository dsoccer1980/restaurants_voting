package ru.dsoccer1980.web.user;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.dsoccer1980.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dsoccer1980.testdata.TestUtil.userHttpBasic;
import static ru.dsoccer1980.testdata.UserTestData.USER1;

public class UserRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserRestController.REST_URL + '/';


    @Test
    public void getTest() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

}