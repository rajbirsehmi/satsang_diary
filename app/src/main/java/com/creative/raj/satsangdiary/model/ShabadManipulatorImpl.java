package com.creative.raj.satsangdiary.model;

import android.os.AsyncTask;
import android.os.Debug;

import com.creative.raj.satsangdiary.presenter.ShabadManipulator;
import com.creative.raj.satsangdiary.roomdatabase.database.DiaryDatabase;
import com.creative.raj.satsangdiary.roomdatabase.database.QueryOrganiser;
import com.creative.raj.satsangdiary.roomdatabase.entities.Shabad;

public class ShabadManipulatorImpl {

    private ShabadManipulator shabadManipulator;

    public ShabadManipulatorImpl(ShabadManipulator shabadManipulator) {
        this.shabadManipulator = shabadManipulator;
    }

    public void updateShabad(int shabadId, String newShabadText) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                if (Debug.isDebuggerConnected())
                    Debug.waitForDebugger();

                Shabad modifiedShabad = new Shabad();
                modifiedShabad.setShabadId(shabadId);
                modifiedShabad.setShabadText(newShabadText);

                return QueryOrganiser.updateExistingShabad(DiaryDatabase.getInstance(), modifiedShabad);
            }

            @Override
            protected void onPostExecute(Integer result) {
                super.onPostExecute(result);
                if (result != 0) {
                    shabadManipulator.callbackEditFailed("Error Occured While Updating Shabad");
                    return;
                }
                shabadManipulator.callbackEditSuccess();
            }
        }.execute();
    }

    public void removeShabad(int shabadId) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                if (Debug.isDebuggerConnected())
                    Debug.waitForDebugger();

                Shabad shabadToDelete = new Shabad();
                shabadToDelete.setShabadId(shabadId);
                shabadToDelete.setShabadText("does not matter");

                return QueryOrganiser.removeShabad(DiaryDatabase.getInstance(), shabadToDelete);
            }

            @Override
            protected void onPostExecute(Integer result) {
                super.onPostExecute(result);
                if (result != 0) {
                    shabadManipulator.callbackRemoveFailed("Error Occured While Removing Shabad");
                    return;
                }
                shabadManipulator.callbackRemoveSuccess();
            }
        }.execute();
    }
}
