package com.example.Caravan.Service.ServiceImp;

import com.example.Caravan.DTO.RequestDTO.CardRequestDto;
import com.example.Caravan.DTO.ResponseDTO.CardResponseDto;
import com.example.Caravan.Enum.CardType;
import com.example.Caravan.Exception.CardTypeNotFoundException;
import com.example.Caravan.Exception.CustomerNotFoundException;
import com.example.Caravan.Model.Card;
import com.example.Caravan.Model.Customer;
import com.example.Caravan.Repository.CustomerRepository;
import com.example.Caravan.Service.Interface.CardServiceInt;
import com.example.Caravan.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CardServiceImpl implements CardServiceInt {

    @Autowired
    CustomerRepository customerRepository;
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CardTypeNotFoundException {
        List<CardType> cardTypeList = List.of(CardType.values());
        if(!cardTypeList.contains(cardRequestDto.getCardType()))
            throw new CardTypeNotFoundException();

        Customer customer = customerRepository.findByEmailId(cardRequestDto.getCustomerEmailId());
        if(Objects.isNull(customer))
            throw new CustomerNotFoundException();

        Card card = CardTransformer.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);


        Customer savedCustomer = customerRepository.save(customer);

        return CardTransformer.cardToCardResponseDto(savedCustomer.getCards().get(savedCustomer.getCards().size()-1)); // OR cardToCardResponseDto(card);
    }
}
