package com.icloud.jpashopreview.controller.dto;

import com.icloud.jpashopreview.domain.Address;
import com.icloud.jpashopreview.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberListDto {

    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    public Member toEntity() {
        return Member.builder()
                .name(getName())
                .address(new Address(getCity(), getStreet(), getZipcode()))
                .build();
    }

    public MemberListDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        Address address = member.getAddress();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.zipcode = address.getZipcode();
    }
}
