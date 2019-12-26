package com.company.parobch3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.ServerSocket;

public class MainActivity extends AppCompatActivity {

    MyTask mt;
    EditText editText;
    TextView field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        field = findViewById(R.id.info);
    }

    public void startServer(View view) {
        mt = new MyTask();
        mt.execute();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            field.append("Socket is created!\n");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                ServerConnection conn = new ServerConnection(new ServerSocket(Integer.parseInt(editText.getText().toString())));//создаем сокет для сервера
                conn.toDoDataAlgorithm();//выполняем операции с получеными данными отправляем результат обратно
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            field.append("All calculations was done!\n");
        }
    }
}
