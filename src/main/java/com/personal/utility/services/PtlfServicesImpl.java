package com.personal.utility.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.utility.dto.PtlfById;
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
    public ResponseData<Object> getAllPtlf(Pageable reqPage, String date) throws CustomNullException {
        if (!mongoTemplate.collectionExists(date)) {
            throw new CustomNullException("Date isn't Active");
        }
        try {

            Query paginationQuery = new Query().with(reqPage);

            List<PtlfResult> ptlfResult = mongoTemplate.find(paginationQuery, PtlfResult.class, date);

            Page<PtlfResult> ptlfPaging = PageableExecutionUtils.getPage(ptlfResult, reqPage,
                    () -> mongoTemplate.count(paginationQuery, PtlfResult.class));

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

    @Override
    public ResponseData<Object> getDataPtlfId(String date, String id) throws CustomNullException {

        PtlfResult ptlfResult = mongoTemplate.findById(id, PtlfResult.class, date);

        responseData = new ResponseData<Object>(200, "Success to Get Data", ptlfResult);
        return responseData;
    }

    @Override
    public ResponseData<Object> getAllPtlfById(Pageable reqPage, PtlfById searchData) throws CustomNullException {
        final String id = searchData.getId();
        final String date = searchData.getDate();
        PtlfPagesDetails ptlfDetails = new PtlfPagesDetails(null, null, null, null);

        if (!mongoTemplate.collectionExists(date)) {
            throw new CustomNullException("Date isn't Active");
        }

        try {
            Query termId = new Query().with(reqPage);
            Query cardNumb = new Query().with(reqPage);

            termId.addCriteria(Criteria.where("head.crd.term.term_id").is(id));
            cardNumb.addCriteria(Criteria.where("head.crd.card.crd_num.num").is(id));

            List<PtlfResult> termIdQuery = mongoTemplate.find(termId, PtlfResult.class, date);
            List<PtlfResult> cardNumbQuery = mongoTemplate.find(cardNumb, PtlfResult.class, date);

            // ONLY TO GET TOTAL ELEMENTS AND TOTAL PAGES
            Query termIdTotal = new Query();
            Query cardNumbTotal = new Query();
            termIdTotal.addCriteria(Criteria.where("head.crd.term.term_id").is(id));
            cardNumbTotal.addCriteria(Criteria.where("head.crd.card.crd_num.num").is(id));
            List<PtlfResult> termIdQueryTotal = mongoTemplate.find(termIdTotal, PtlfResult.class, date);
            List<PtlfResult> cardNumbQueryTotal = mongoTemplate.find(cardNumbTotal, PtlfResult.class, date);

            if (!termIdQuery.isEmpty() || !cardNumbQuery.isEmpty()) {
                if (termIdQuery.isEmpty()) {

                    Page<PtlfResult> ptlfPaging = PageableExecutionUtils.getPage(cardNumbQuery,
                            reqPage,
                            () -> mongoTemplate.count(cardNumb, PtlfResult.class));

                    Integer pagesShowed = ptlfPaging.getContent().size();
                    // Integer totalElements = mongoTemplate.findAll(PtlfResult.class, date).size();
                    Integer totalElements = cardNumbQueryTotal.size();
                    Integer totalPages = (totalElements / reqPage.getPageSize()) + 1;
                    List<PtlfResult> contents = ptlfPaging.getContent();
                    ptlfDetails = new PtlfPagesDetails(pagesShowed, totalPages, totalElements, contents);
                } else if (cardNumbQuery.isEmpty()) {

                    Page<PtlfResult> ptlfPaging = PageableExecutionUtils.getPage(termIdQuery,
                            reqPage,
                            () -> mongoTemplate.count(termId, PtlfResult.class));

                    Integer pagesShowed = ptlfPaging.getContent().size();
                    // Integer totalElements = mongoTemplate.findAll(PtlfResult.class, date).size();
                    Integer totalElements = termIdQueryTotal.size();
                    Integer totalPages = (totalElements / reqPage.getPageSize()) + 1;
                    List<PtlfResult> contents = ptlfPaging.getContent();
                    ptlfDetails = new PtlfPagesDetails(pagesShowed, totalPages, totalElements, contents);
                }

            } else {
                throw new CustomNullException("Failed to get Data");
            }

            responseData = new ResponseData<Object>(200, "Success to Get Data", ptlfDetails);
            return responseData;
        } catch (Exception e) {
            throw new CustomNullException("Failed to get Data");
        }

    }
}