package com.psato.devfest.presentation.MVP;

public class QueryContract {
    interface View{
        void showLoading();
        void hideLoading();
        void showResult(String name);
        void showError();
    }

    interface Presenter {
        void setView(View view);
        void requestData(String value);
    }
}
