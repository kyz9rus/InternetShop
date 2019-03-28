package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.model.OrderStatus;
import ru.tsystems.internetshop.model.PaymentMethod;
import ru.tsystems.internetshop.model.PaymentStatus;
import ru.tsystems.internetshop.model.RevenueInfo;
import ru.tsystems.internetshop.service.CategoryService;
import ru.tsystems.internetshop.service.ClientService;
import ru.tsystems.internetshop.service.OrderService;
import ru.tsystems.internetshop.service.ProductService;
import ru.tsystems.internetshop.util.CategoryInfo;

import java.util.List;

@Controller
@RequestMapping("employeeProfile")
public class EmployeeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryInfo categoryInfo;

    @Autowired
    private ClientService clientService;

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("get-orders")
    public String getOrders(Model model) {
        List<OrderDTO> orders = orderService.getOrders();

        model.addAttribute("orders", orders);
        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/orders";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("clientStatistic")
    public String clientStatisticPage(Model model) {
        model.addAttribute("clients", clientService.getTop10Clients());
        model.addAttribute("categories", categoryInfo.getInstance());
        return "employeeProfile/statistic/topClients";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("productStatistic")
    public String productStatisticPage(Model model) {
        model.addAttribute("products", productService.getTop10Products());
        model.addAttribute("categories", categoryInfo.getInstance());
        return "employeeProfile/statistic/topProducts";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("revenue")
    public String revenuePage(Model model) {
        RevenueInfo revenueInfo = orderService.getRevenueInfo();

        model.addAttribute("categories", categoryInfo.getInstance());
        model.addAttribute("revenue", revenueInfo);
        return "employeeProfile/statistic/revenue";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("addProduct")
    public String addProductPage(Model model) {
        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/addProduct";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("manageCategories")
    public String manageCategoriesPage(Model model) {
        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/manageCategories";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("import-products-from-file")
    public String importFromFile(Model model) {
//        ...

        model.addAttribute("categories", categoryInfo.getInstance());

        model.addAttribute("successMessage", "import of products successfully completed");

        return "employeeProfile/importProductsFromFile";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping("change-order-status")
    public String changeOrderStatus(@RequestParam("id") Long id, @RequestParam("orderStatus") String orderStatusString, Model model) {
        OrderDTO orderDTO = orderService.getOrder(id);
        if (orderDTO != null) {
            orderDTO.setOrderStatus(orderService.getOrderStatus(orderStatusString));
            orderService.updateOrder(orderDTO);

            model.addAttribute("successMessage", "Order status for order with id " + orderDTO.getId() + " successfully changed!");
        } else
            model.addAttribute("errorMessage", "Order with id " + id + " doesn't exist. Inform the administrator!");

        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/orders";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping("change-payment-status")
    public String changePaymentStatus(@RequestParam("id") Long id, @RequestParam("paymentStatus") String paymentStatusString, Model model) {
        OrderDTO orderDTO = orderService.getOrder(id);
        if (orderDTO != null) {
            orderDTO.setPaymentStatus(orderService.getPaymentStatus(paymentStatusString));
            orderService.updateOrder(orderDTO);

            model.addAttribute("successMessage", "Payment status for order with id " + orderDTO.getId() + " successfully changed!");
        } else
            model.addAttribute("errorMessage", "Order with id " + id + " doesn't exist. Inform the administrator!");

        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/orders";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping("create-product")
    public String createProduct(@Validated @ModelAttribute("product") ProductDTO productDTO, Model model) {
        System.out.println(productDTO);

        if (productService.getProductByName(productDTO.getName()) == null) {
            productService.saveProduct(productDTO);

            model.addAttribute("successMessage", "Product saved successfully.");
        } else
            model.addAttribute("errorMessage", "Product with this name already exists.");

        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/addProduct";
    }

    @PostMapping("create-category")
    public String createCategory(@ModelAttribute("category") CategoryDTO categoryDTO, Model model) {
        categoryDTO.setName(categoryDTO.getName().toLowerCase());

        if (categoryService.getCategoryByName(categoryDTO.getName()) == null) {

            categoryService.saveCategory(categoryDTO);

            categoryInfo.getInstance().clear();

            List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
            categoryDTOS.forEach(category -> category.setName(category.getName().replaceAll("_", " ").toUpperCase()));

            categoryInfo.getInstance().addAll(categoryDTOS);

            model.addAttribute("successMessage", "Category successfully changed.");
        } else
            model.addAttribute("errorMessage", "Category already exist.");


        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/manageCategories";
    }

    @PostMapping("update-category")
    public String updateCategory(@ModelAttribute("category") CategoryDTO categoryDTO, @RequestParam("oldName") String oldName, Model model) {
        categoryDTO.setName(categoryDTO.getName().toLowerCase());
        oldName = oldName.toLowerCase();

        categoryService.updateCategory(oldName, categoryDTO);

        categoryInfo.getInstance().clear();

        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
        categoryDTOS.forEach(category -> category.setName(category.getName().replaceAll("_", " ").toUpperCase()));

        categoryInfo.getInstance().addAll(categoryDTOS);

        model.addAttribute("successMessage", "Category successfully changed.");
        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/manageCategories";
    }

    @PostMapping("remove-category")
    public String removeCategory(@RequestParam("oldName") String categoryName, Model model) {
        CategoryDTO categoryDTO = categoryService.getCategoryByName(categoryName);
        List<OrderDTO> orders = orderService.getOrdersByCategory(categoryDTO);
        if (orders.isEmpty()) {
            categoryName = categoryName.toLowerCase();

            categoryService.removeCategoryByName(categoryName);

            categoryInfo.getInstance().clear();

            List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
            categoryDTOS.forEach(category -> category.setName(category.getName().replaceAll("_", " ").toUpperCase()));

            categoryInfo.getInstance().addAll(categoryDTOS);

            model.addAttribute("successMessage", "Category successfully changed.");
        } else {
            model.addAttribute("errorMessage", "Category cannot be removed (There are incomplete orders)");
        }


        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/manageCategories";
    }
}
