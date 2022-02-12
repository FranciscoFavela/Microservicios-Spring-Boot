package com.microservicios.clientservice.Dtos;


import org.modelmapper.ModelMapper;

public class BaseMapperDTO {
    private final ModelMapper modelMapper = new ModelMapper();

    private static BaseMapperDTO baseMapperDTO;

    private BaseMapperDTO(){}

    public static BaseMapperDTO singleInstance(){
        if(baseMapperDTO == null){
            baseMapperDTO = new BaseMapperDTO();
        }
        return baseMapperDTO;
    }

    /*public AppoimentDto mapToAppoimentDto(Appoiment appoiment){
        return modelMapper.map(appoiment,AppoimentDto.class);
    }
*/
}
