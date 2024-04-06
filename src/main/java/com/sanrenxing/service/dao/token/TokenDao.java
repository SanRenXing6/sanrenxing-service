package com.sanrenxing.service.dao.token;

import com.sanrenxing.service.model.data.ConfirmationToken;

import java.util.Optional;

public interface TokenDao {
    void addToken(ConfirmationToken token);

    Optional<ConfirmationToken> getToken(String token);

    void setConfirmedAt(String token);
}
