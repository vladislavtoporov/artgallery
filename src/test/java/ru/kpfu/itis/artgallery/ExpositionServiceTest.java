package ru.kpfu.itis.artgallery;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kpfu.itis.artgallery.config.Application;
import ru.kpfu.itis.artgallery.enums.Role;
import ru.kpfu.itis.artgallery.models.Exposition;
import ru.kpfu.itis.artgallery.repositories.UserRepository;
import ru.kpfu.itis.artgallery.services.ExpositionService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExpositionServiceTest {

    @Autowired
    public ExpositionService expositionService;
    @Autowired
    public UserRepository userRepository;

//    @Before
//    public void setUp() {
//        Mockito.when(exhibitService.findAllByQuerry().thenReturn();
//    }

    @Test
    public void whenFindByQuerryThenReturnListExhibits() {
        String querry = "описание";
        Exposition exposition = new Exposition();
        exposition.setOwner(userRepository.findAllByRoleIn(new Role[]{Role.STAFF, Role.ARTIST}).get(0));
        Page<Exposition> exhibits = expositionService.findSimilar(exposition);
        exhibits.forEach(item -> {
            if (item.getOwner() != exposition.getOwner())
                Assert.fail();
        });
//        List<Exhibit> exhibits = exhibitService.findAllByQuerry("");
//        Assert.assertTrue(optional.isPresent());
    }

}
