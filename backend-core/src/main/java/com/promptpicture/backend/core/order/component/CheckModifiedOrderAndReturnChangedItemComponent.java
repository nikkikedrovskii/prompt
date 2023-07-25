package com.promptpicture.backend.core.order.component;

import com.promptpicture.backend.core.customer.adapter.CustomerRepositoryAdapter;
import com.promptpicture.backend.core.order.domain.Order;
import com.promptpicture.backend.core.order.domain.PromptInOrder;
import com.promptpicture.backend.core.price.adapter.PriceRepositoryAdapter;
import com.promptpicture.backend.core.vat.adapter.VatRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.promptpicture.backend.core.order.domain.OrderModifiedNotificationMessage.COUNTRY_VAT_MODIFIED;
import static com.promptpicture.backend.core.order.domain.OrderModifiedNotificationMessage.PRICE_WITHOUT_VAT_MODIFIED;
import static com.promptpicture.backend.core.order.domain.OrderModifiedNotificationMessage.PRICE_WITH_VAT_MODIFIED;
import static com.promptpicture.backend.core.order.domain.OrderModifiedNotificationMessage.PROMPT_PRICE_MODIFIED;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckModifiedOrderAndReturnChangedItemComponent {

    private final VatRepositoryAdapter vatRepositoryAdapter;
    private final CustomerRepositoryAdapter customerRepositoryAdapter;
    private final PriceRepositoryAdapter priceRepositoryAdapter;


    public List<String> checkModifiedOrderAndReturnChangedItem(Order order) {
        var listOfModifiedOrder = new ArrayList<String>();
        var promptInOrderList = order.getPromptInOrderList();

        checkModifiedVat(order,listOfModifiedOrder);
        checkModifiedPromptPrice(promptInOrderList,listOfModifiedOrder);
        checkModifiedOrderPrice(order,listOfModifiedOrder);



        return listOfModifiedOrder;
    }

    private void checkModifiedPromptPrice(List<PromptInOrder> promptInOrderList, List<String> listOfModifiedOrder){
        promptInOrderList.forEach(promptInOrder -> {
            var priceByResolution = priceRepositoryAdapter.getPriceByResolution(promptInOrder.getResolution());
            var priceFromDb = promptInOrder.isIndividual() ? priceByResolution.getIndividualPrice() : priceByResolution.getDefaultPrice();
            if (!Objects.equals(priceFromDb, promptInOrder.getPriceWithoutVat())){
                var promptMessage = MessageFormat.format(PROMPT_PRICE_MODIFIED,promptInOrder.getResolution(),priceFromDb);
                promptInOrder.setPriceWithoutVat(priceFromDb);
                listOfModifiedOrder.add(promptMessage);
            }
        });
    }

    private void checkModifiedVat(Order order, List<String> listOfModifiedOrder){
        var countryCode = customerRepositoryAdapter.findCountryCodeByExternalCustomerId(order.getExternalCustomerId());
        var vatFromDb = vatRepositoryAdapter.getVatByCountryCode(countryCode);

        if (!Objects.equals(vatFromDb.getVatRate(), order.getVatValue())){
            var vatMessage = MessageFormat.format(COUNTRY_VAT_MODIFIED,countryCode,vatFromDb.getVatRate());
            order.setVatValue(vatFromDb.getVatRate());
            listOfModifiedOrder.add(vatMessage);
        }
    }

    private BigDecimal calculateTotalPriceWithVat(BigDecimal priceWithoutVat, BigDecimal vatRate){
        var fullRate = new BigDecimal(1).subtract(vatRate.divide(new BigDecimal(100)));
        return priceWithoutVat.divide(fullRate,2, RoundingMode.HALF_EVEN);
    }

    private void checkModifiedOrderPrice(Order order, List<String> listOfModifiedOrder){
        BigDecimal priceWithoutVatFromDb = BigDecimal.ZERO;

          var promptInOrderList = order.getPromptInOrderList();

        for (PromptInOrder promptInOrder : promptInOrderList) {
            var price = priceRepositoryAdapter.getPriceByResolution(promptInOrder.getResolution());
            var promptPriceByResolution = promptInOrder.isIndividual()
                    ? price.getIndividualPrice() : price.getDefaultPrice();
            priceWithoutVatFromDb = priceWithoutVatFromDb.add(promptPriceByResolution);
        }

        if (!Objects.equals(priceWithoutVatFromDb, order.getPriceWithoutVat())){
            var promptMessage = MessageFormat.format(PRICE_WITHOUT_VAT_MODIFIED,priceWithoutVatFromDb);
            order.setPriceWithoutVat(priceWithoutVatFromDb);
            listOfModifiedOrder.add(promptMessage);
        }
        var countryCode = customerRepositoryAdapter.findCountryCodeByExternalCustomerId(order.getExternalCustomerId());
        var vatFromDb = vatRepositoryAdapter.getVatByCountryCode(countryCode);

        var priceWithVatFromDb = calculateTotalPriceWithVat(priceWithoutVatFromDb,vatFromDb.getVatRate());

        if (!Objects.equals(priceWithVatFromDb, order.getPriceWithVat())){
            var promptMessage = MessageFormat.format(PRICE_WITH_VAT_MODIFIED,priceWithVatFromDb);
            order.setPriceWithVat(priceWithVatFromDb);
            listOfModifiedOrder.add(promptMessage);
        }
    }

}
