package com.snscard.web.service;

import com.snscard.web.utils.ResultVO;
import com.snscard.web.utils.Result_Information;
import org.json.JSONObject;

public interface InformationService {
    Result_Information getInformation(String id);
    Result_Information insertInformation(JSONObject jsonObject);
}
