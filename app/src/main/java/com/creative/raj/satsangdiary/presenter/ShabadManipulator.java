package com.creative.raj.satsangdiary.presenter;

public interface ShabadManipulator {
    void callbackEditSuccess();

    void callbackEditFailed(String message);

    void callbackRemoveSuccess();

    void callbackRemoveFailed(String message);
}
