package com.codigo.spring.controller;

import com.codigo.spring.request.BoletoRequest;
import com.codigo.spring.response.BoletoResponse;
import com.codigo.spring.response.ResponseBase;
import com.codigo.spring.service.BoletoService;
import com.codigo.spring.utils.Constants;
import org.aspectj.apache.bcel.classfile.Code;
import org.aspectj.apache.bcel.classfile.ConstantString;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/boleto")
public class BoletoController {
    private final BoletoService boletoService;

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @PostMapping("/save")
    public ResponseBase<BoletoResponse> save(@RequestBody BoletoRequest boletoRequest) {
        BoletoResponse boletoResponse = boletoService.save(boletoRequest);
        if (boletoResponse == null) {
            return new ResponseBase<>(Constants.CODE_NOT_FOUND, Constants.MESSAGE_NOT_FOUND, Optional.empty());
        }
        return new ResponseBase<>(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS_INSERT, Optional.of(boletoResponse));
    }

}
