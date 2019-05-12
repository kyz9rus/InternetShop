package ru.tsystems.internetshop.facade;

import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DeliveryMethod;
import ru.tsystems.internetshop.model.PaymentMethod;

/**
 * This interface declares method which needs to use OrderService, ProductService and ClientService
 */
public interface OrderProductClientFacade {
    void issueOrder(ClientDTO clientDTO, ClientAddressDTO clientAddressDTO, Basket basket, DeliveryMethod deliveryMethod, PaymentMethod paymentMethod) throws Exception;
}
