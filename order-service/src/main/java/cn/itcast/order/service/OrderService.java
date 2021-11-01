package cn.itcast.order.service;

import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    // @Autowired
    // private OrderMapper orderMapper;

    // @Autowired
    // private UserClient userClient;

    @Autowired
    private RestTemplate restTemplate;


    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        // Order order = orderMapper.findById(orderId);
        Order order = new Order();
        order.setId(orderId);
        order.setName("Computer");
        order.setPrice(10000L);
        order.setNum(1);
        order.setUserId(orderId);
        // 2.用Feign远程调用
        // User user = userClient.findById(order.getUserId());
        // 3.封装user到Order

        String url = "http://userservice/user/" + order.getUserId();
        // String url = "http://localhost:8081/user/101" + order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);
        // 4.返回
        return order;
    }

    /*@Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.利用RestTemplate发起http请求，查询用户
        // 2.1.url路径
        String url = "http://userservice/user/" + order.getUserId();
        // 2.2.发送http请求，实现远程调用
        User user = restTemplate.getForObject(url, User.class);
        // 3.封装user到Order
        order.setUser(user);
        // 4.返回
        return order;
    }*/
}
