package org.tonight.mscustomerservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tonight.mscustomerservice.dto.ApiCustomerDetailsDto;
import org.tonight.mscustomerservice.dto.CustomerAccountDto;
import org.tonight.mscustomerservice.entity.CustomerDetails;
import org.tonight.mscustomerservice.service.customerDetailsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/customer")
public class CustomerApi {

    @Autowired
    customerDetailsService customerService;

    @PostMapping("/addCustomer")
    public void addCustomer(@RequestBody ApiCustomerDetailsDto customer) {
        customerService.addCustomerDetails(customer);
    }

    @GetMapping("/getCustomer")
    public ResponseEntity<List<CustomerDetails>> getCustomer() {
        //int x= 4/0;
        List<CustomerDetails> customerDetails = customerService.fetchDetails();
        if(Objects.nonNull(customerDetails)){

            return ResponseEntity.status(216).header("Content-Type","APPLICATION/JSON")
                    .body(customerDetails);
        }
        return ResponseEntity.status(589).header("Error","No data found").build();
    }

    @GetMapping("/getAccount")
    public CustomerAccountDto getAccount(@RequestParam Integer customerId) {
        return customerService.getAccountDetails(customerId);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, String>> handleException() {
        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("7896", "error occurred, please try again");
        return ResponseEntity.status(416).body(errorsMap);
    }
}



