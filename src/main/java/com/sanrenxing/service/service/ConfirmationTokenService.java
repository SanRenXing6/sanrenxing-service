package com.sanrenxing.service.service;

import com.sanrenxing.service.dao.token.TokenDao;
import com.sanrenxing.service.model.data.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<ConfirmationToken> getConfirmationToken(String token) {
        Optional<ConfirmationToken> result = tokenDao.getToken(token);
        return result;
    };

    public void setConfirmedAt(String token) {
        tokenDao.setConfirmedAt(token);
    }

}
