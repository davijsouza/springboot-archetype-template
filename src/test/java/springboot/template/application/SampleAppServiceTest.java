package springboot.template.application;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import springboot.template.dto.InputDTO;
import springboot.template.dto.OutputDTO;
import springboot.template.exception.ValidateRegisterException;
import springboot.template.model.entity.SampleEntity;
import springboot.template.service.SampleService;

/**
 * Class auto-generated by archetype-spring project
 * Repository: http://californio.keynet.com.br:8089/java/archetype/archetype-spring
 */
@SpringBootTest
@AutoConfigureMockMvc
class SampleAppServiceTest {

    private static final SampleEntity SAMPLE_ENTITY = new SampleEntity(1, "Guilherme Biff Zarelli", 28);

    @Autowired
    private SampleAppService sampleApp;

    @MockBean
    private SampleService sampleService;

    @Test
    void contextLoad() {
        Assertions.assertNotNull(sampleApp);
    }

    @Test
    void test_if_register_method_returns_the_correct_OutputDTO() throws Throwable {
        InputDTO inputDTO = Mockito.mock(InputDTO.class);
        Mockito.when(sampleService.register(ArgumentMatchers.any())).thenReturn(SAMPLE_ENTITY);
        OutputDTO outputDTO = sampleApp.process(inputDTO);
        Assertions.assertEquals(outputDTO.getId(), SAMPLE_ENTITY.getId());
    }

    @Test
    void assert_ValidateRegisterException_on_return_method_and_log() throws Throwable {
        Mockito.when(sampleService.register(ArgumentMatchers.any())).thenThrow(new ValidateRegisterException());
        Assertions.assertThrows(ValidateRegisterException.class, () -> sampleApp.process(Mockito.mock(InputDTO.class)));
    }

}
