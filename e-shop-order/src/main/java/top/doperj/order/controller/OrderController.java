package top.doperj.order.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.doperj.order.POJO.OrderUser;
import top.doperj.order.POJO.ViewOrder;
import top.doperj.order.domain.Order;
import top.doperj.order.service.OrderService;
import top.doperj.common.Entity.ViewOrderResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.*;

@Controller
@RequestMapping("/api/order")
public class OrderController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OrderService orderService;

/*    @GetMapping(value = "/user", produces = "application/json")
    @ResponseBody
    public String getLoginedUser(HttpServletRequest request, HttpSession httpSession) {
        String username = (String) httpSession.getAttribute("username");
        System.out.println(httpSession.getId());
        System.out.println(username);
        return "session id: " + httpSession.getId() + "; username: " + username;
    }*/

    @GetMapping(value = "/failed", produces = "application/json")
    @ResponseBody
    public String loginFailed() {
        System.out.println("failed");
        return restTemplate.getForObject("http://user-services/api/login_user", String.class);
    }

    @PostMapping("")
    @ResponseBody
    public Order createOrder(HttpServletRequest httpServletRequest) {
        try {
            ViewOrder viewOrder = new ViewOrder();
            BufferedReader br = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String reqBody = URLDecoder.decode(sb.toString(), "UTF-8");
            logger.info("Request Body:" + reqBody);
            JSONObject json = new JSONObject(reqBody);
            List<Integer> skuList = new LinkedList<Integer>();
            // items 为订单中的产品信息
            JSONObject jsonObject = json.getJSONObject("items");
            Iterator<String> keyIterator = jsonObject.keys();
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            System.out.println(json.keySet());
            while (keyIterator.hasNext()) {
                String key = keyIterator.next();
                System.out.println(key);
                int skuId = Integer.valueOf(key);
                System.out.println("key: " + key + ", skuId: " + skuId);
                map.put(skuId, jsonObject.getInt(key));
            }
            viewOrder.setItems(map);
            viewOrder.setAddressId(json.getInt("addressId"));
            viewOrder.setFirst_name(json.getString("firstName"));
            viewOrder.setLast_name(json.getString("lastName"));
            logger.info("[getRequestPostJson][" + json
                    + "]-- get request body with json success.");
            System.out.println(viewOrder);
            return orderService.addOrderByViewOrder(viewOrder);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
        //return orderService.addOrderByViewOrder(viewOrder);
    }

    @GetMapping(value = "/{username}", produces = "application/json")
    @ResponseBody
    public List<ViewOrderResponse> getOrdersByUserName(@PathVariable("username") String userName) {
        System.out.println("find " + userName + " orders.");
        List<ViewOrderResponse> viewOrderResponseList = orderService.findViewOrderResponseByUserName(userName);
        System.out.println(viewOrderResponseList);
        return viewOrderResponseList;
    }

    @DeleteMapping(value = "")
    @ResponseBody
    public String removeOrderById(@RequestParam("orderId") Integer orderId) {
        Order order = orderService.selectOrderByOrderId(orderId);
        System.out.println("request order: " + order);
        OrderUser orderUser = orderService.getUserNameByOrder(order);
        //if (orderUser.getUserName().equals(user)) {
            orderService.deleteOrderByOrderId(orderId);
        //}
        return orderUser.getUserName();
    }
}
