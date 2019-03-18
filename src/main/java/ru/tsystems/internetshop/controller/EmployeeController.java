package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.internetshop.facade.OrderFacade;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Order;
import ru.tsystems.internetshop.model.OrderStatus;
import ru.tsystems.internetshop.model.entity.Product;
import ru.tsystems.internetshop.service.CategoryService;
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
    private OrderFacade orderFacade;

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("get-orders")
    public String getOrders(Model model) {
//        List<OrderDTO> orders = orderService.getOrders();

        List<OrderDTO> orders = orderFacade.getOrders();

        if (orders.isEmpty())
            model.addAttribute("emptyListMessage", "Order list is empty.");
        else
            model.addAttribute("orders", orders);

        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/orders";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("changeOrderStatus")
    public String changeOrderStatusPage(Model model) {
        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/changeOrderStatus";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("saleStatistic")
    public String saleStatisticPage(Model model) {
        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/saleStatistic";
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
        model.addAttribute("categories", categoryInfo.getInstance());

//        ...

        model.addAttribute("successMessage", "import of products successfully completed");

        return "employeeProfile/importProductsFromFile";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping("change-order-status")
    public String changeOrderStatus(@RequestParam("id") Long id, @RequestParam("orderStatus") OrderStatus orderStatus, Model model) {

        OrderDTO orderDTO = orderService.getOrder(id);
        if (orderDTO != null) {
////            Order order = null;
////            try {
////                order = orderDTO.clone();
////            } catch (CloneNotSupportedException e) {
////                e.printStackTrace();
////            }
//
//            order.setOrderStatus(orderStatus);
//
//            orderService.updateOrder(order);

            model.addAttribute("successMessage", "Order status for order with id " + orderDTO.getId() + " successfully changed!");
        } else
            model.addAttribute("errorMessage", "Order with id " + id + " doesn't exist. Inform the administrator!");

        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/changeOrderStatus";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping("create-product")
    public String createProduct(@Validated @ModelAttribute("product") Product product, Model model) {
        System.out.println(product);

        if (productService.getProductByName(product.getName()) == null) {
            productService.saveProduct(product);

            model.addAttribute("successMessage", "Product saved successfully.");
        } else
            model.addAttribute("errorMessage", "Product with this name already exists.");

        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/addProduct";
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
    public String removeCategory(@RequestParam("oldName") String oldName, Model model) {
        oldName = oldName.toLowerCase();

        categoryService.removeCategory(new CategoryDTO(oldName));

        categoryInfo.getInstance().clear();

        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
        categoryDTOS.forEach(category -> category.setName(category.getName().replaceAll("_", " ").toUpperCase()));

        categoryInfo.getInstance().addAll(categoryDTOS);

        model.addAttribute("successMessage", "Category successfully changed.");
        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile/manageCategories";
    }

    @ResponseBody
    @GetMapping("/getAuthority")
    public Object getAuthority() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getPrincipal();
    }
}
