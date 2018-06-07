package ru.kpfu.itis.artgallery;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.kpfu.itis.artgallery.config.Application;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.repositories.ExhibitRepository;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class ExhibitRestTest {

    @Autowired
    public MockMvc mvc;

    @MockBean
    public ExhibitRepository exhibitRepositoryMock;

    @Test
    public void whenGetExhibitsThenReturnJsonArray()
            throws Exception {

        Exhibit exhibit = new Exhibit();
        exhibit.setName("exhibit");

        List<Exhibit> exhibits = Arrays.asList(exhibit);

        given(exhibitRepositoryMock.findAll()).willReturn(exhibits);

        mvc.perform(get("/rest/exhibits")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(exhibits.size())))
                .andExpect(jsonPath("$[0].name", is(exhibit.getName())));

    }
}
