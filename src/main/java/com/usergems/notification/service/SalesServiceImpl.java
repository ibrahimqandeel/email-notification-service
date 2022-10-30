package com.usergems.notification.service;

import com.usergems.notification.dto.SalesDto;
import com.usergems.notification.entity.SalesEntity;
import com.usergems.notification.repository.SalesRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;
    private final ModelMapper modelMapper;

    public SalesServiceImpl(SalesRepository salesRepository, ModelMapper modelMapper) {
        this.salesRepository = salesRepository;
        this.modelMapper = modelMapper;
    }

//    @Override
//    public Optional<String> findApiKeyByEmail(String email) {
//        Optional<SalesEntity> sales = salesRepository.findByEmail(email);
//        if (sales.isPresent()) {
//            return Optional.ofNullable(sales.get().getApiKey());
//        }
//        return Optional.empty();
//    }

    @Override
    public List<SalesDto> findAllWhenApiKeyNotEmpty() {
        return modelMapper.map(salesRepository.findByApiKeyIsNotNull(), new TypeToken<List<SalesDto>>(){}.getType());
    }
}
