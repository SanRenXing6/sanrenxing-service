package com.sanrenxing.service.service;

import com.sanrenxing.service.dao.token.TokenDao;
import com.sanrenxing.service.model.data.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService {

    private final TokenDao tokenDao;

    @Autowired
    public ConfirmationTokenService(@Qualifier("tokenPostgreSQL") TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    public void saveConfirmationToken(ConfirmationToken token) {
        tokenDao.addToken(token);
    }

}
