package com.example.Caravan.Service.ServiceImp;

import com.example.Caravan.DTO.RequestDTO.CardRequestDto;
import com.example.Caravan.DTO.RequestDTO.CheckOutCartRequestDto;
import com.example.Caravan.DTO.RequestDTO.ItemRequestDto;
import com.example.Caravan.DTO.ResponseDTO.CartResponseDto;
import com.example.Caravan.DTO.ResponseDTO.OrderResponseDto;
import com.example.Caravan.Exception.CardNotFoundException;
import com.example.Caravan.Exception.CustomerNotFoundException;
import com.example.Caravan.Exception.EmptyCartException;
import com.example.Caravan.Exception.InsufficientProductQuantityException;
import com.example.Caravan.Model.*;
import com.example.Caravan.Repository.*;
import com.example.Caravan.Service.Interface.CartServiceInt;
import com.example.Caravan.Transformer.CartTransformer;
import com.example.Caravan.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartServiceInt {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderServiceImpl orderService;

    public Cart addCart(Customer customer){
        return Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();
    }

    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal() + item.getRequiredQuantity()*product.getPrice());
        item.setProduct(product);
        item.setCart(cart);
        cart.getItems().add(item);

        Cart savedCart = cartRepository.save(cart); //saves both cart and item.

        Item savedItem = savedCart.getItems().get(savedCart.getItems().size()-1);

        product.getItems().add(savedItem);

        return CartTransformer.cartToCartResponseDto(savedCart);
    }

    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto){
        Customer customer = customerRepository.findByEmailId(checkOutCartRequestDto.getEmailId());
        if(Objects.isNull(checkOutCartRequestDto))
            throw new CustomerNotFoundException();

        Card card = cardRepository.findByCardNo(checkOutCartRequestDto.getCardNo());
        Date date = new Date();

       /* if(Objects.isNull(card) || checkOutCartRequestDto.getCvv() != card.getCvv() || date.after(card.getValidTill()))
            throw new CardNotFoundException();*/


        Cart cart = customer.getCart();
        if(cart.getItems().size()==0){
            throw new EmptyCartException("Cart is empty!!");
        }

        try{
            OrderEntity order = orderService.placeOrder(cart,card);
            resetCart(cart);

            OrderEntity savedOrder = orderRepository.save(order);
            customer.getOrders().add(savedOrder);

            return OrderTransformer.orderToOrderResponseDto(savedOrder);
        }
        catch (InsufficientProductQuantityException e){
            throw e;
        }
    }

    private void resetCart(Cart cart){

        cart.setCartTotal(0);
        for(Item item: cart.getItems())
            item.setCart(null);
        cart.setItems(new ArrayList<>());
    }
}
