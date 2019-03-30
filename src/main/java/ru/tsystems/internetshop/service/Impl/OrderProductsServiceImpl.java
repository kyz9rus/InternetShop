package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.OrderProductDAO;
import ru.tsystems.internetshop.model.DTO.OrderProductDTO;
import ru.tsystems.internetshop.service.OrderProductsService;
import ru.tsystems.internetshop.util.Mapper;

@Transactional
@Service
public class OrderProductsServiceImpl implements OrderProductsService {

    @Autowired
    private OrderProductDAO orderProductDAO;

    @Autowired
    private Mapper mapper;

    @Override
    public void saveOrderProduct(OrderProductDTO orderProductDTO) {
        orderProductDAO.create(mapper.convertToEntity(orderProductDTO));
    }
}
