package com.pr.sepp.history.service;

import com.pr.sepp.history.model.SEPPHistory;

import java.util.List;
import java.util.Map;

public interface HistoryService {

    int historyInsert(SEPPHistory history);

    int historyInsertBatch(List<SEPPHistory> history);

    List<SEPPHistory> historyDirectQuery(Map<String, String> dataMap);

    List<SEPPHistory> historyReferQuery(Map<String, String> dataMap);
}
