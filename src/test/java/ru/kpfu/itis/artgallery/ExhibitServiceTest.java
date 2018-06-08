package ru.kpfu.itis.artgallery;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kpfu.itis.artgallery.config.Application;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.services.ExhibitService;

import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExhibitServiceTest {

    @Autowired
    public ExhibitService exhibitService;
    private String querry = "экспонат";
    private String filter = "author";

    @MockBean
    private ExhibitService service;

    @Before
    public void setUp() {
        querry = "экспонат";
        filter = "author";
        List<Exhibit> ex = Collections.singletonList(new Exhibit());
        Page<Exhibit> page = new PageImpl<>(ex);
        Mockito.when(service.findAllByQuerry(querry, filter, 0)).thenReturn(page);
    }

    @Test
    public void whenFindByQuerryThenReturnListSizeLessThenTen() {
        Page<Exhibit> exhibits = exhibitService.findAllByQuerry(querry, filter, 0);
        int size = exhibits.getSize();
        Assert.assertTrue(size <= 10);
    }

    @Test
    public void whenFindByQuerryThenReturnNotNull() {
        Assert.assertNotNull(service.findAllByQuerry(querry, filter, 0));
    }

}
