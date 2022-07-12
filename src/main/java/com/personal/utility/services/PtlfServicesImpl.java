package com.personal.utility.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.utility.dto.PtlfPagesDetails;
import com.personal.utility.dto.ResponseData;
import com.personal.utility.exception.custom.CustomNullException;
import com.personal.utility.model.ptlf.PtlfResult;

@Service
@Transactional
public class PtlfServicesImpl implements PtlfServices {

    ResponseData<Object> responseData;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public ResponseData<Object> getDataPtlf(Pageable reqPage, String date) throws CustomNullException {
        if(!mongoTemplate.collectionExists(date)){
            throw new CustomNullException("Date isn't Active");
        }
        try {
            
            Query paginationQuery = new Query().with(reqPage);
            
            List<PtlfResult> ptlfResult = mongoTemplate.find(paginationQuery, PtlfResult.class, date);
            
            Page<PtlfResult> ptlfPaging = PageableExecutionUtils.getPage(ptlfResult, reqPage, () -> mongoTemplate.count(paginationQuery, PtlfResult.class));
            
            Integer pagesShowed = ptlfPaging.getContent().size();
            Integer totalElements = mongoTemplate.findAll(PtlfResult.class, date).size();
            Integer totalPages = (totalElements / reqPage.getPageSize()) + 1;
            List<PtlfResult> contents = ptlfPaging.getContent();

            PtlfPagesDetails ptlfDetails = new PtlfPagesDetails(pagesShowed, totalPages, totalElements, contents);

            responseData = new ResponseData<Object>(200, "Success to Get Data", ptlfDetails);
            return responseData;
        } catch (Exception e) {
            responseData = new ResponseData<Object>(500, "Failed to Get Data", e);
            return responseData;
        }
    }
}
