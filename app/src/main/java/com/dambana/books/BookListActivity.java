package com.dambana.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    private ProgressBar mLoadingProgress;
    private RecyclerView rvBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        mLoadingProgress = (ProgressBar) findViewById(R.id.pb_loading);
        rvBooks = (RecyclerView) findViewById(R.id.rv_books);
        try {
            URL bookUrl = ApiUtil.buildURL("cooking");
            new BooksQueryTask().execute(bookUrl);

        } catch (Exception e) {
            Log.d("error",e.getMessage());
        }
    }

    public class BooksQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;

            try {
                result = ApiUtil.getJson(searchURL);
            } catch (IOException e) {
                Log.e("error", e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            TextView tvError = (TextView) findViewById(R.id.tv_error);
            mLoadingProgress.setVisibility(View.INVISIBLE );
            LinearLayoutManager booksLayoutManager = new LinearLayoutManager(BookListActivity.this,
                    LinearLayoutManager.VERTICAL,false);
            rvBooks.setLayoutManager(booksLayoutManager);
            if(result == null){
                tvError.setVisibility(View.VISIBLE);
                rvBooks.setVisibility(View.INVISIBLE);
            }else{
                tvError.setVisibility(View.INVISIBLE);
                rvBooks.setVisibility(View.VISIBLE);
            }
            ArrayList<Book> books = ApiUtil.getBooksFromJson(result);
            String resultString = "";

            BooksAdapter adapter = new BooksAdapter(books);
            rvBooks.setAdapter(adapter);

            //tvResult.setText(resultString);
            //super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }
    }
}


