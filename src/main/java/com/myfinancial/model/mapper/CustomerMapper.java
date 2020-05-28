package com.myfinancial.model.mapper;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.enums.ProfileType;
import com.myfinancial.model.domain.request.CustomerRequest;
import com.myfinancial.model.domain.response.CustomerResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CategoryMapper.class})
public interface CustomerMapper {

    @Mapping(target = "profileType", qualifiedByName = "profileTypeMapper")
    Customer to(final CustomerRequest customerRequest);

    @Mapping(target = "profileType", source = "profileType", qualifiedByName = "profileTypeMapper")
    void toUpdate(@MappingTarget Customer customer, final CustomerRequest customerRequest);

    @Mapping(target = "profileType", source = "profileType.name")
    CustomerResponse toReponse(final Customer customer);

    List<CustomerResponse> toResponseList(final List<Customer> customerList);


    @Named("profileTypeMapper")
    default ProfileType profileTypeMapper(final String profileType) {
        return ProfileType.toEnum(profileType);
    }
}
