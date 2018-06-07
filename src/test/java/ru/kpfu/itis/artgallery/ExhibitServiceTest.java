package ru.kpfu.itis.artgallery;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kpfu.itis.artgallery.config.Application;
import ru.kpfu.itis.artgallery.models.Exhibit;
import ru.kpfu.itis.artgallery.services.ExhibitService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExhibitServiceTest {

    @Autowired
    public ExhibitService exhibitService;

//    @Before
//    public void setUp() {
//        Mockito.when(exhibitService.findAllByQuerry().thenReturn();
//    }

    @Test
    public void whenFindByQuerryThenReturnListExhibits() {
        String querry = "экспонат";
        String filter = "author";
        Page<Exhibit> exhibits = exhibitService.findAllByQuerry(querry, filter, 0);
        exhibits.forEach(item -> {
            if (!item.getName().contains(querry))
                Assert.fail();
        });
//        List<Exhibit> exhibits = exhibitService.findAllByQuerry("");
//        Assert.assertTrue(optional.isPresent());
    }

    @Test
    public void whenFindByQuerryThenReturnListSizeLessThenTen() {
        String querry = "экспонат";
        String filter = "author";
        Page<Exhibit> exhibits = exhibitService.findAllByQuerry(querry, filter, 0);
        int size = exhibits.getSize();
        Assert.assertTrue(size <= 10);
//        List<Exhibit> exhibits = exhibitService.findAllByQuerry("");
//        Assert.assertTrue(optional.isPresent());
    }

}
