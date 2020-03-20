package com.pensource.android.advice.database.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.pensource.android.advice.database.AdviceDatabase;
import com.pensource.android.advice.database.dao.AdviceDao;
import com.pensource.android.advice.database.model.Advice;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AdviceRepository {

    private AdviceDao dao;
    private LiveData<Advice> allAdvice;
    private LiveData<List<Advice>> allFavoriteAdvice;

    public AdviceRepository(Application application) {
        AdviceDatabase adviceDatabase = AdviceDatabase.getInstance(application);
        dao = adviceDatabase.adviceDao();
    }

    public Advice getAdvice() throws ExecutionException, InterruptedException {
        return new ReadAdviceAsyncTask(dao).execute().get();
    }

    public void insertAdvice(Advice advice) {
        new InsertAdviceASyncTask(dao).execute(advice);
    }

    public void insertAdviceList(List<Advice> advice) {
        new InsertAdviceListAsyncTask(dao).execute(advice);
    }

    private class InsertAdviceASyncTask extends AsyncTask<Advice, Void, Void> {

        private AdviceDao adviceDao;

        public InsertAdviceASyncTask(AdviceDao adviceDao) {
            this.adviceDao = adviceDao;
        }

        @Override
        protected Void doInBackground(Advice... advice) {
            adviceDao.insertAdvice(advice[0]);
            return null;
        }
    }

    private class InsertAdviceListAsyncTask extends AsyncTask<List<Advice>, Void, Void> {

        private AdviceDao adviceDao;

        public InsertAdviceListAsyncTask(AdviceDao adviceDao) {
            this.adviceDao = adviceDao;
        }

        @Override
        protected Void doInBackground(List<Advice>... advice) {
            adviceDao.insertAllAdvice(advice[0]);
            return null;
        }
    }

    private class ReadAdviceAsyncTask extends AsyncTask<Void, Void, Advice> {

        private AdviceDao adviceDao;

        public ReadAdviceAsyncTask(AdviceDao adviceDao) {
            this.adviceDao = adviceDao;
        }

        @Override
        protected Advice doInBackground(Void... id) {
            return adviceDao.getNextAdvice();
        }
    }

}
