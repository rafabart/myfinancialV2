package com.myfinancial.model.mapper;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.enums.ProfileType;
import com.myfinancial.model.domain.request.CustomerRequest;
import com.myfinancial.model.domain.response.CustomerResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CategoryMapper.class})
public interface CustomerMapper {

    @Mapping(target = "profileType", ignore = true)
    Customer to(final CustomerRequest customerRequest);

    @Mapping(target = "profileType", ignore = true)
    void toUpdate(@MappingTarget Customer customer, final CustomerRequest customerRequest);

    @Mapping(source = "profileType.name", target = "profileType")
    CustomerResponse toReponse(final Customer customer);

    List<CustomerResponse> toResponseList(final List<Customer> customerList);


    @AfterMapping
    default void setProfileType(@MappingTarget Customer customer, final CustomerRequest customerRequest) {
        customer.setProfileType(ProfileType.toEnum(customerRequest.getProfileType()));
    }
}
