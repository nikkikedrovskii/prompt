package com.promptpicture.backend.db_adapter.order.adapter;

import com.promptpicture.backend.core.common.enumeration.OrderStatus;
import com.promptpicture.backend.core.exception.business.BadRequestException;
import com.promptpicture.backend.core.order.adapter.OrderDetailsRepositoryAdapter;
import com.promptpicture.backend.core.order.domain.OrderDetails;
import com.promptpicture.backend.db_adapter.component.ImageCompressionComponent;
import com.promptpicture.backend.jpa.cart.entity.CartEntity;
import com.promptpicture.backend.jpa.cart.entity.CartItemEntity;
import com.promptpicture.backend.jpa.cart.repository.CartEntityRepository;
import com.promptpicture.backend.jpa.order.entity.OrderDetailsEntity;
import com.promptpicture.backend.jpa.order.entity.PromptInOrderEntity;
import com.promptpicture.backend.jpa.order.repository.OrderDetailsEntityRepository;
import com.promptpicture.backend.jpa.order.repository.OrderStatusEntityRepository;
import com.promptpicture.backend.jpa.order.repository.PromptInOrderEntityRepository;
import com.promptpicture.backend.jpa.payment_method.repository.PaymentMethodEntityRepository;
import com.promptpicture.backend.jpa.prompt.entity.PromptOrderDataEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static com.promptpicture.backend.core.exception.error_code.ErrorCode.CART_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class OrderDetailsJpaRepositoryAdapter implements OrderDetailsRepositoryAdapter {



    private final CartEntityRepository cartEntityRepository;
    private final OrderDetailsEntityRepository orderDetailsEntityRepository;
    private final OrderStatusEntityRepository orderStatusEntityRepository;
    private final PaymentMethodEntityRepository paymentMethodEntityRepository;
    private final PromptInOrderEntityRepository promptInOrderEntityRepository;
    private final ImageCompressionComponent imageCompressionComponent;


    @Override
    @Transactional
    public void saveOrder(UUID externalCustomerId, OrderDetails orderDetails) {
        var cartEntity = getCartEntity(orderDetails.getCartId());


        var orderDetailsEntity = createOrderDetails(cartEntity,orderDetails);
        orderDetailsEntity.setExternalCustomerId(externalCustomerId);

        orderDetailsEntityRepository.save(orderDetailsEntity);
    }

    private OrderDetailsEntity createOrderDetails(CartEntity cartEntity, OrderDetails orderDetails){
        var orderDetailsEntity = new OrderDetailsEntity();

        orderDetailsEntity.setCity(orderDetails.getCity());
        orderDetailsEntity.setInvoiceEmail(orderDetails.getContactEmail());
        orderDetailsEntity.setEmail(orderDetails.getContactEmail());
        orderDetailsEntity.setCountry(orderDetails.getCountry());
        orderDetailsEntity.setPhone(orderDetails.getPhone());
        orderDetailsEntity.setName(orderDetails.getName());
        orderDetailsEntity.setLastName(orderDetails.getLastName());
        orderDetailsEntity.setZipCode(orderDetails.getZipCode());
        orderDetailsEntity.setAddress(orderDetails.getAddress());
        orderDetailsEntity.setConsentData(OffsetDateTime.now());

        var statusEntity = orderStatusEntityRepository.findOrderStatusEntityByStatus(String.valueOf(OrderStatus.NEW));
        var paymentMethod = paymentMethodEntityRepository.findPaymentMethodEntitiesByPaymentMethodName(orderDetails.getPaymentMethod());
        orderDetailsEntity.setPaymentMethodEntity(paymentMethod.get());
        orderDetailsEntity.setOrderStatusEntity(statusEntity.get());
        orderDetailsEntity.setDescription(orderDetails.getDescription());
        orderDetailsEntity.setVatValue(cartEntity.getVatValue());
        orderDetailsEntity.setDialcode("420");
        orderDetailsEntity.setPriceWithVat(cartEntity.getPriceWithVat());
        orderDetailsEntity.setPriceWithoutVat(cartEntity.getPriceWithoutVat());


        var promptInOrderList = getListOfOrderEntity(cartEntity.getCartItemEntities(),orderDetailsEntity);
        orderDetailsEntity.setPromptInOrderEntityList(promptInOrderList);

        return orderDetailsEntity;

    }

    private List<PromptInOrderEntity> getListOfOrderEntity(List<CartItemEntity> cartItemEntityList, OrderDetailsEntity orderDetails){
        return cartItemEntityList.stream().map(cartItemEntity -> {
            var newPromptInOrderEntity = new PromptInOrderEntity();
             var promptInOrderEntityOptional = promptInOrderEntityRepository.getPromptInOrderEntitiesByPromptIdAndResolution(cartItemEntity.getPromptEntity().getId(),cartItemEntity.getPriceEntity().getResolution());

            if (promptInOrderEntityOptional.isEmpty()) {
                var promptPictureEntity = cartItemEntity.getPromptEntity().getPromptPictureEntity();
                newPromptInOrderEntity.setOrderDetailsEntity(orderDetails);
                newPromptInOrderEntity.setPromptId(cartItemEntity.getPromptEntity().getId());
                newPromptInOrderEntity.setResolution(cartItemEntity.getPriceEntity().getResolution());
                newPromptInOrderEntity.setPriceWithoutVat(cartItemEntity.getPriceEntity().getDefaultPrice());
                newPromptInOrderEntity.setIndividual(false);

                var promptDataEntity = new PromptOrderDataEntity();
                var compressB64Json = imageCompressionComponent.compressImage(promptPictureEntity.getFileB64JsonText(),cartItemEntity.getPriceEntity().getResolution());
                promptDataEntity.setPromptText(promptPictureEntity.getText());
                promptDataEntity.setPromptData(compressB64Json);
                promptDataEntity.setCreatedAt(cartItemEntity.getPromptEntity().getCreatedAt());
                promptDataEntity.setPromptId(cartItemEntity.getPromptEntity().getId());
                newPromptInOrderEntity.setPromptOrderDataEntity(promptDataEntity);

            } else {
                newPromptInOrderEntity.setOrderDetailsEntity(orderDetails);
                newPromptInOrderEntity.setPromptId(promptInOrderEntityOptional.get().getPromptId());
                newPromptInOrderEntity.setResolution(promptInOrderEntityOptional.get().getResolution());
                newPromptInOrderEntity.setIndividual(promptInOrderEntityOptional.get().getIndividual());
                newPromptInOrderEntity.setPromptOrderDataEntity(promptInOrderEntityOptional.get().getPromptOrderDataEntity());
                newPromptInOrderEntity.setPriceWithoutVat(promptInOrderEntityOptional.get().getPriceWithoutVat());
            }
            return newPromptInOrderEntity;
        }).toList();

    }
    private CartEntity getCartEntity(Long cartId){
        return cartEntityRepository.findById(cartId)
                .orElseThrow(() -> new BadRequestException(CART_NOT_FOUND));
    }
}
