package hsb.mkss1.order_system.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Test

@SpringBootTest
@AutoConfigureMockMvc
class StatusControllerIntegrationTest @Autowired constructor(
    val mockMvc: MockMvc
) {

    @Test
    fun `status returns running message`() {
        mockMvc.perform(get("/status"))
            .andExpect(status().isOk)
            .andExpect(content().string("Order System is running"))
    }

}