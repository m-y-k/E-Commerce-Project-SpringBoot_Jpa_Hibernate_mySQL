package com.example.Caravan.Service.ServiceImp;

import com.example.Caravan.DTO.RequestDTO.OrderRequestDto;
import com.example.Caravan.DTO.ResponseDTO.OrderResponseDto;
import com.example.Caravan.Enum.ProductStatus;
import com.example.Caravan.Exception.*;
import com.example.Caravan.Model.*;
import com.example.Caravan.Repository.CardRepository;
import com.example.Caravan.Repository.CustomerRepository;
import com.example.Caravan.Repository.OrderRepository;
import com.example.Caravan.Repository.ProductRepository;
import com.example.Caravan.Service.Interface.OrderServiceInt;
import com.example.Caravan.Transformer.ItemTransformer;
import com.example.Caravan.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderServiceInt {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderRepository orderRepository;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto){
        Customer customer = customerRepository.findByEmailId(orderRequestDto.getCustomerEmailId());
        if(Objects.isNull(customer))
            throw new CustomerNotFoundException();

        Optional<Product> optionalProduct = productRepository.findById(orderRequestDto.getProductId());

        if(optionalProduct.isEmpty())
            throw new ProductNotFoundException();

        Product product = optionalProduct.get();

        Integer availableQuantity = product.getQuantity();

        if(availableQuantity < orderRequestDto.getQuantity())
            throw new InsufficientProductQuantityException(product.getName());

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        Date date = new Date();

        if(Objects.isNull(card) || orderRequestDto.getCardCvv() != card.getCvv() || date.after(card.getValidTill()))
            throw new CardNotFoundException();

        product.setQuantity(product.getQuantity() - orderRequestDto.getQuantity());

        if(product.getQuantity() == 0)
            product.setStatus(ProductStatus.OUT_OF_STOCK);

        Item item = ItemTransformer.itemRequestDtoToItem(orderRequestDto, product);

        String maskedCard = generateMaskedCardNo(card);
        OrderEntity order = OrderTransformer.orderRequestDtoToOrder(orderRequestDto,customer,item, maskedCard);
        order.getItems().add(item);
        item.setOrder(order);

        OrderEntity savedOrder = orderRepository.save(order);

        customer.getOrders().add(savedOrder);
        product.getItems().add(savedOrder.getItems().get(0));

        return OrderTransformer.orderToOrderResponseDto(order);
    }


    public OrderEntity placeOrder(Cart cart, Card card) throws InsufficientProductQuantityException {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(generateMaskedCardNo(card));

        int totalValue = 0;
        for(Item item: cart.getItems()){
            Product product = item.getProduct();
            if(item.getRequiredQuantity()>product.getQuantity()){
                throw new InsufficientProductQuantityException(product.getName());
            }

            totalValue += item.getRequiredQuantity()*product.getPrice();
            int newQuantity = product.getQuantity()-item.getRequiredQuantity();
            product.setQuantity(newQuantity);
            if(newQuantity==0){
                product.setStatus(ProductStatus.OUT_OF_STOCK);
            }

            item.setOrder(orderEntity);
        }

        orderEntity.setTotalValue(totalValue);
        orderEntity.setItems(cart.getItems());
        orderEntity.setCustomer(cart.getCustomer());

        return orderEntity;
    }


    private String generateMaskedCardNo(Card card) {
        String cardNo = "";

        for(int i = 0;i<card.getCardNo().length()-4;i++)
            cardNo+="*";

        cardNo+= card.getCardNo().substring(cardNo.length()-4);

        return cardNo;
    }
}
