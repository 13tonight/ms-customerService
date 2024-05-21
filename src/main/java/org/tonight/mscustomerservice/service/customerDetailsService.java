package org.tonight.mscustomerservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.tonight.mscustomerservice.dto.ApiCustomerDetailsDto;
import org.tonight.mscustomerservice.dto.CustomerAccountDto;
import org.tonight.mscustomerservice.entity.CustomerDetails;
import org.tonight.mscustomerservice.repository.CustomerDetailsRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class customerDetailsService {

    @Autowired
    CustomerDetailsRepo customerDetailsRepo;

    @Autowired
    WebClient webClient;

    final ObjectMapper objectMapper;

    public CustomerDetails addCustomerDetails(ApiCustomerDetailsDto customerDetailsApi) {

        CustomerDetails customerDetailActual = mapCustomerDetails(customerDetailsApi);
        CustomerDetails savedCustomerDetails = customerDetailsRepo.save(customerDetailActual);
        CustomerAccountDto accountDto = objectMapper.convertValue(customerDetailsApi, CustomerAccountDto.class);
        accountDto.setId(savedCustomerDetails.getCustomerId());
        postAccountDetails(accountDto);
        return customerDetailActual;
    }
    private CustomerDetails mapCustomerDetails(ApiCustomerDetailsDto customerDetailsApi) {
        CustomerDetails customerDetail = new CustomerDetails();
        customerDetail.setCustomerFirstName(customerDetailsApi.getFirstName());
        customerDetail.setCustomerLastName(customerDetailsApi.getLastName());
        customerDetail.setCustomerEmail(customerDetailsApi.getEmail());
        customerDetail.setCustomerPhone(customerDetailsApi.getPhone());
        customerDetail.setCustomerAddress(customerDetailsApi.getAddress());
        customerDetail.setCustomerCity(customerDetailsApi.getCity());
        customerDetail.setCustomerState(customerDetailsApi.getState());
        customerDetail.setCustomerZipCode(customerDetailsApi.getZipCode());
        customerDetail.setCustomerCountry(customerDetailsApi.getCountry());
//        return objectMapper.convertValue(customerDetailsApi, CustomerDetails.class);
        return customerDetail;
    }

    public List<CustomerDetails> fetchDetails(){
        return customerDetailsRepo.findAll();
    }

    public CustomerAccountDto getAccountDetails(Integer customerId) {
        return webClient.get().uri("http://localhost:8085/account/byCustomerId?customerId="+customerId)
                .retrieve()
                .bodyToMono(CustomerAccountDto.class)
                .block();
    }

    public void postAccountDetails(CustomerAccountDto customerAccountDto) {
        webClient.post()
                .uri("http://localhost:8085/account/addDetails")
                .bodyValue(customerAccountDto)
                .retrieve()
                .toBodilessEntity()
                .subscribe();
    }

}
