package app.rosettacloud.spring6reactive.mappers;

import org.mapstruct.Mapper;

import app.rosettacloud.spring6reactive.domain.Customer;
import app.rosettacloud.spring6reactive.model.CustomerDTO;

@Mapper
public interface CustomerMapper {

    CustomerDTO toDto(Customer customer);
    Customer toCustomer(CustomerDTO customerDTO);
}
