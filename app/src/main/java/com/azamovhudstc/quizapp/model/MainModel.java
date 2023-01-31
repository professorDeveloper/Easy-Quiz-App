package com.azamovhudstc.quizapp.model;


import com.azamovhudstc.quizapp.contract.MainContract;
import com.azamovhudstc.quizapp.repository.AppRepository;

public class MainModel implements MainContract.MainModel {
    private AppRepository repository;

    public MainModel() {
            repository = AppRepository.getInstance();

    }
}
