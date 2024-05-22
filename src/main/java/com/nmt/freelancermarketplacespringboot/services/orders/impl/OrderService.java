package com.nmt.freelancermarketplacespringboot.services.orders.impl;

import com.nmt.freelancermarketplacespringboot.common.enums.OrderStatusEnum;
import com.nmt.freelancermarketplacespringboot.common.enums.TransactionStatusEnum;
import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.ModuleException;
import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.orders.OrderExceptionMessages;
import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.orders.CreateOrderDto;
import com.nmt.freelancermarketplacespringboot.dto.orders.CreateTransactionDto;
import com.nmt.freelancermarketplacespringboot.dto.orders.UpdateOrderDto;
import com.nmt.freelancermarketplacespringboot.entities.orders.OrderEntity;
import com.nmt.freelancermarketplacespringboot.entities.orders.PaymentMethodEntity;
import com.nmt.freelancermarketplacespringboot.entities.orders.TransactionEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import com.nmt.freelancermarketplacespringboot.repositories.orders.IOrderRepository;
import com.nmt.freelancermarketplacespringboot.repositories.orders.ITransactionRepository;
import com.nmt.freelancermarketplacespringboot.services.orders.IOrderService;
import com.nmt.freelancermarketplacespringboot.services.orders.IPaymentMethodService;
import com.nmt.freelancermarketplacespringboot.services.orders.ITransactionService;
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


    private final IOrderRepository orderRepository;
    private final IUserService userService;
    private final IPackageService packageService;
    private final ITransactionService transactionService;
    private final IPaymentMethodService paymentMethodService;

    @Autowired
    public OrderService(
            IOrderRepository orderRepository,
            IUserService userService,
            IPackageService packageService,
            ITransactionService transactionService,
            IPaymentMethodService paymentMethodService
    ) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.packageService = packageService;
        this.userService = userService;
        this.transactionService = transactionService;
        this.paymentMethodService = paymentMethodService;
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
            case ORDER_TRANSACTED -> {
                System.out.println("Order is transacted. Taking action...");
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

    @Override
    @Transactional
    public OrderEntity createTransaction(
            String email,
            CreateTransactionDto data
    ) throws ModuleException {
        OrderEntity findOrder = this.getOneById(data.orderId());
        UUID transactionId;
        if(findOrder.getStatus() == OrderStatusEnum.ORDER_CANCELED.getStatus()){
            throw new ModuleException(OrderExceptionMessages.ORDER_TRANSACTION_NOT_CREATED.getMessage());
        }
        else{
            UserEntity findUser = this.userService.getUserByEmail(email);
            PaymentMethodEntity findPaymentMethod = this.paymentMethodService.getOneById(data.paymentMethodId());
            TransactionEntity newTransaction = new TransactionEntity();
            newTransaction.setAmount(data.amount());
            newTransaction.setDescription(data.description());
            newTransaction.setStatus(TransactionStatusEnum.TRANSACTION_CREATED.getStatus());
            newTransaction.setPaymentMethod(findPaymentMethod);
            newTransaction.setOrder(findOrder);
            newTransaction.setUser(findUser);
            TransactionEntity transactionCreated =  this.transactionService.createOne(newTransaction);
            findOrder.setTransaction(transactionCreated);
            transactionId = transactionCreated.getTransactionId();
        }
        TransactionEntity findTransaction = this.transactionService.getOneById(transactionId);
        findTransaction.setStatus(TransactionStatusEnum.TRANSACTION_SUCCEEDED.getStatus());
        findOrder.setStatus(OrderStatusEnum.ORDER_TRANSACTED.getStatus());
        return findOrder;
    }
}