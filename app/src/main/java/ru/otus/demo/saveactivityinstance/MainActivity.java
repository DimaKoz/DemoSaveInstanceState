package ru.otus.demo.saveactivityinstance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mMessageEditText;
    final static String TAG = MainActivity.class.getSimpleName();
    final static String MESS_KEY = "saved_mess_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMessageEditText = findViewById(R.id.message_edit_text);

        if (savedInstanceState != null) {
            String savedMessage =
                    savedInstanceState.getString(MESS_KEY);
            if (!TextUtils.isEmpty(savedMessage)) {
                mMessageEditText.setText(savedMessage);
            }
            Log.d(TAG, "onCreate:[" + savedMessage + "]");
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String savedMes = savedInstanceState.getString(MESS_KEY);
        if (!TextUtils.isEmpty(savedMes)) {
            mMessageEditText.setText(savedMes);
        }

        Log.d(TAG, "onRestoreInstanceState:[" + savedMes + "]");

    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MESS_KEY, mMessageEditText.getText().toString());
        Log.d(TAG, "onSaveInstanceState:[" + mMessageEditText.getText().toString() + "]");
    }

/*
    @Override
    protected void onResume() {
        super.onResume();
        if(!TextUtils.isEmpty(Storage.getInstance().getMessage())){
            mMessageEditText.setText(Storage.getInstance().getMessage());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Storage.getInstance().setMessage(mMessageEditText.getEditableText().toString());
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickExplicit(View view) {
        getSomething();
    }

    public void clickImplicit(View view) {
        // Создаем  текстовое сообщение
        String textMessage = "Our message";
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
        sendIntent.setType("text/plain");

        String title = getResources().getString(R.string.chooser_title);
        // Создаем Intent для отображения диалога выбора.
        Intent chooser = Intent.createChooser(sendIntent, title);

        // Проверяем, что intent может быть успешно обработан
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }

    }

    final static int OUR_REQUEST_CODE = 42;

    private void getSomething() {
        Intent intent = new Intent(MainActivity.this, ActivityExplicitIntent.class);
        startActivityForResult(intent, OUR_REQUEST_CODE);
    }

    String ANSWER_TO_THE_ULTIMATE_QUESTION = "answer";

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == OUR_REQUEST_CODE) {
            String answer = null;
            if (resultCode == RESULT_OK) {
                answer = data.getStringExtra(ANSWER_TO_THE_ULTIMATE_QUESTION);

            }
            Log.i("Answer", "the answer is:" + answer);
        }
    }

}
