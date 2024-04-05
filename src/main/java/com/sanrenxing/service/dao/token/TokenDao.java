package com.sanrenxing.service.dao.token;

import com.sanrenxing.service.model.data.ConfirmationToken;

public interface TokenDao {
    void addToken(ConfirmationToken token);
}
