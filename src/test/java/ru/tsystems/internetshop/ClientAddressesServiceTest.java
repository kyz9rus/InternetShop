package ru.tsystems.internetshop;

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
import ru.tsystems.internetshop.dao.ClientAddressDAO;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.ClientAddress;
import ru.tsystems.internetshop.service.ClientAddressService;
import ru.tsystems.internetshop.service.Impl.ClientAddressServiceImpl;
import ru.tsystems.internetshop.util.Mapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ClientAddressesServiceTest {

    @Configuration
    static class ClientServiceTestContextConfiguration {
        @Bean
        public ClientAddressService clientAddressService() {
            return new ClientAddressServiceImpl();
        }

        @Bean
        public ClientAddressDAO clientAddressDAO() {
            return Mockito.mock(ClientAddressDAO.class);
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
    private ClientAddressService clientAddressService;

    @Autowired
    private ClientAddressDAO clientAddressDAO;

    @Before
    public void setup() {
        Client client = new Client();
        client.setId(1L);
        List<ClientAddress> addresses = Arrays.asList(new ClientAddress(1L), new ClientAddress(2L));

        client.setAddresses(addresses);

        Mockito.when(clientAddressDAO.findAddressesByClient(client)).thenReturn(addresses);
    }

    @Test
    public void testGetClientByEmail() {
        ClientDTO client = new ClientDTO();
        client.setId(1L);

        List<ClientAddressDTO> adresses = clientAddressService.getAddressesByClient(client);

        assertEquals(2, adresses.size());

        long addressId1 = adresses.get(0).getId();
        long addressId2 = adresses.get(1).getId();

        assertEquals(1L, addressId1);
        assertEquals(2L, addressId2);
    }
}

