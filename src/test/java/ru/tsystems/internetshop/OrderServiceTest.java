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
import ru.tsystems.internetshop.dao.OrderDAO;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.PaymentStatus;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.Order;
import ru.tsystems.internetshop.service.Impl.OrderServiceImpl;
import ru.tsystems.internetshop.service.OrderService;
import ru.tsystems.internetshop.util.Mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class OrderServiceTest {

    @Configuration
    static class ClientServiceTestContextConfiguration {
        @Bean
        public OrderService orderService() {
            return new OrderServiceImpl();
        }

        @Bean
        public OrderDAO orderDAO() {
            return Mockito.mock(OrderDAO.class);
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
    private OrderService orderService;

    @Autowired
    private OrderDAO orderDAO;

    @Before
    public void setup() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setId(1L);
        order.setPaymentStatus(PaymentStatus.WAITING_FOR_PAYMENT);

        Order order2 = new Order();
        order2.setId(2L);
        order2.setPaymentStatus(PaymentStatus.PAID);

        orders.add(order);
        orders.add(order2);

        Client client = new Client();
        client.setId(1L);

        List<Order> orders2 = new ArrayList<>(Arrays.asList(new Order(3L), new Order(4L)));

        Mockito.when(orderDAO.findOrdersByClient(client)).thenReturn(orders);
        Mockito.when(orderDAO.findOrdersByCategory(new Category("frangrancy"))).thenReturn(orders2);
        Mockito.when(orderDAO.findUnfinishedOrdersByClient(client)).thenReturn(Arrays.asList(order2));
    }

    @Test
    public void testGetOrdersByClient() {
        ClientDTO clientDTO = new ClientDTO(1L);
        List<OrderDTO> orders = orderService.getOrdersByClient(clientDTO);

        assertEquals(2, orders.size());

        long order1Id = orders.get(0).getId();
        long order2Id = orders.get(1).getId();

        assertEquals(1L, order1Id);
        assertEquals(2L, order2Id);
    }

    @Test
    public void testGetOrdersByCategory() {
        List<OrderDTO> orders = orderService.getOrdersByCategory(new CategoryDTO("frangrancy"));

        assertEquals(2, orders.size());

        long order1Id = orders.get(0).getId();
        long order2Id = orders.get(1).getId();

        assertEquals(3L, order1Id);
        assertEquals(4L, order2Id);
    }

    @Test
    public void testGetUnfinishedOrdersByClient() {
        ClientDTO clientDTO = new ClientDTO(1L);
        List<OrderDTO> orders = orderService.getUnfinishedOrdersByClient(clientDTO);

        assertEquals(1, orders.size());

        long order1Id = orders.get(0).getId();

        assertEquals(2L, order1Id);
        assertEquals(PaymentStatus.PAID, orders.get(0).getPaymentStatus());
    }

    @After
    public void verify() {
        Mockito.reset(orderDAO);
    }
}

