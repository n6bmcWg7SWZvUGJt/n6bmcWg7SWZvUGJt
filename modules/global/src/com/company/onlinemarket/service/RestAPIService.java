package com.company.onlinemarket.service;

public interface RestAPIService {
    String NAME = "onlinemarket_RestAPIService";

    String getGoodsList(String shop);

    String createCustomer(String name);

    String createOrder(String shop, String goodsName, String count);

    String deleteOrder(String orderNo);

}