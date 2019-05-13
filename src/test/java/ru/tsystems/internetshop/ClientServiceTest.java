package ru.tsystems.internetshop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.tsystems.internetshop.dao.ClientDAO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.service.ClientService;
import ru.tsystems.internetshop.service.Impl.ClientServiceImpl;
import ru.tsystems.internetshop.util.Mapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ClientServiceTest {

    @Configuration
    static class ClientServiceTestContextConfiguration {
        @Bean
        public ClientService clientService() {
            return new ClientServiceImpl();
        }

        @Bean
        public ClientDAO clientDAO() {
            return Mockito.mock(ClientDAO.class);
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public Mapper mapper() {
            return new Mapper();
        }
    }

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientDAO clientDAO;

    @Before
    public void setup() {
        Client client = new Client();
        client.setId(1L);
        client.setEmail("example@example.ru");

        Mockito.when(clientDAO.findByEmail("example@example.ru")).thenReturn(client);

        Mockito.when(clientDAO.findTop10Clients()).thenReturn(Arrays.asList(new Client(1L), new Client(2L), new Client(3L), new Client(4L), new Client(5L), new Client(6L), new Client(7L), new Client(8L), new Client(9L), new Client(10L)));
    }

    @Test
    public void testGetClientByEmail() {
        ClientDTO client = clientService.getClientByEmail("example@example.ru");

        long id = client.getId();

        assertEquals(1L, id);
    }

    @Test
    public void testGetTop10Clients() {
        List<ClientDTO> clients = clientService.getTop10Clients();

        assertEquals(10, clients.size());
    }

    @After
    public void verify() {
        Mockito.reset(clientDAO);
    }
}

