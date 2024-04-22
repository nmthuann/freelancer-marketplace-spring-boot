package com.nmt.freelancermarketplacespringboot.services.orders.impl;

import com.nmt.freelancermarketplacespringboot.common.enums.OrderStatusEnum;
import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.orders.CreateOrderDto;
import com.nmt.freelancermarketplacespringboot.dto.orders.UpdateOrderDto;
import com.nmt.freelancermarketplacespringboot.entities.orders.OrderEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import com.nmt.freelancermarketplacespringboot.repositories.orders.IOrderRepository;
import com.nmt.freelancermarketplacespringboot.services.orders.IOrderService;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPackageService;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService
        extends AbstractBaseService<OrderEntity, UUID>
        implements IOrderService {

    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    IUserService userService;

    @Autowired
    IPackageService packageService;


    @Autowired
    public OrderService(IOrderRepository orderRepository) {
        super(orderRepository);
    }


    /**
     * @param email
     * @param data: CreateOrderDto
     * @return
     *
     */
    @Override
    @Transactional
    public OrderEntity createOne(String email, CreateOrderDto data) {
        UserEntity buyer = this.userService.getUserByEmail(email);
        PackageEntity findPackage = this.packageService.getOneById(data.packageId());
        UserEntity seller = findPackage.getPost().getUser();

        OrderEntity newOrder = new OrderEntity();
        newOrder.setBuyer(buyer);
        newOrder.setSeller(seller);
        newOrder.setPackageEntity(findPackage);
        newOrder.setStatus(OrderStatusEnum.ORDER_PENDING.getStatus());
        newOrder.setDeliveryDay(data.deliveryDay());
        newOrder.setTotalPrice(data.totalPrice());

        newOrder.setReason(null);
        newOrder.setTransaction(null);
        newOrder.setRevisions(null);

        return this.orderRepository.save(newOrder);
    }

    @Override
    public OrderEntity updateStatus(UUID orderId, UpdateOrderDto data) {
        OrderEntity findOrder = this.getOneById(orderId);

        OrderStatusEnum orderStatusEnum = OrderStatusEnum.valueOf(data.status());
        return switch (orderStatusEnum) {
            case ORDER_PENDING -> {
                System.out.println("Order is pending. Taking action...");
                yield findOrder;
            }
            case ORDER_CONFIRMED -> {
                System.out.println("Order is confirmed. Taking action...");
                yield findOrder;
            }
            case ORDER_IN_PROGRESS -> {
                System.out.println("Order is in progress. Taking action...");
                yield findOrder;
            }
            case ORDER_CANCELED -> {
                System.out.println("Order is canceled. Taking action...");
                yield findOrder;
            }
            case ORDER_COMPLETED -> {
                System.out.println("Order is completed. Taking action...");
                yield findOrder;
            }
        };
    }
}


//switch (orderStatusEnum) {
//            case ORDER_PENDING:
//                System.out.println("Order is pending. Taking action...");
//                return findOrder;
//            case ORDER_CONFIRMED:
//                System.out.println("Order is confirmed. Taking action...");
//
//                return findOrder;
//            case ORDER_IN_PROGRESS:
//                System.out.println("Order is in progress. Taking action...");
//                return findOrder;
//            case ORDER_CANCELED:
//                System.out.println("Order is canceled. Taking action...");
//                return findOrder;
//            case ORDER_COMPLETED:
//                System.out.println("Order is completed. Taking action...");
//                return findOrder;
//            default:
//                System.out.println("Unknown order status.");
//                return findOrder;
//        }