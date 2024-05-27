package com.example.courier.models.mapper;

import com.example.courier.models.dto.ClientDto;
import com.example.courier.models.dto.OrderDto;
import com.example.courier.models.dto.ParcelDto;
import com.example.courier.models.entity.Client;
import com.example.courier.models.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component("spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    default OrderDto toOrderDto(Order order) {

        return new OrderDto(
                ClientMapper.INSTANCE.toClientDto(order.getSender()),
                AddressMapper.INSTANCE.toAddressDto(order.getPickUpPoint()),

                ClientMapper.INSTANCE.toClientDto(order.getRecipient()),
                AddressMapper.INSTANCE.toAddressDto(order.getDeliveryPoint()),

                ParcelMapper.INSTANCE.toParcelDto(order.getParcel())
        );
    }

}
