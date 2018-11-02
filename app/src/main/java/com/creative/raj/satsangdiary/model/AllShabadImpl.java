package com.creative.raj.satsangdiary.model;

import android.content.Context;
import android.database.Cursor;

import com.creative.raj.satsangdiary.adapter.AllShabadAdapter;
import com.creative.raj.satsangdiary.dataholders.shabad.DataHolder;
import com.creative.raj.satsangdiary.datebasehelper.QueryManager;
import com.creative.raj.satsangdiary.parser.Parser;
import com.creative.raj.satsangdiary.presenter.AllShabad;

import java.util.List;

public class AllShabadImpl {
    private AllShabad allShabad;
    private Context context;

    public AllShabadImpl(AllShabad allShabad, Context context) {
        this.allShabad = allShabad;
        this.context = context;
    }

    public void loadAllShabad() {
        Cursor cursorAllShabad = QueryManager.getAllShabads(context);
        List<DataHolder> listDataHolder = Parser.parseShabadList(cursorAllShabad);
        if (listDataHolder.size() == 0) {
            allShabad.notifyNoShabadFound("No Shabad Found...");
            return;
        }
        allShabad.attachAdapterToView(new AllShabadAdapter(listDataHolder, context));
    }
}
