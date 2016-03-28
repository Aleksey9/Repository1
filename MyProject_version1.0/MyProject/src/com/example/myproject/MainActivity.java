package com.example.myproject;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//задаём кол-во групп вопросов
		static final int count_of_group_questions = 2;
	
	static int count;
	static final private int NUMBER = 0;
	//static boolean[] ArrQuestion = new boolean [QuestionActivity.count_of_group_questions];
	static ArrayList <Integer> ArrQuestion = new ArrayList();
	
	//Создание главной активности
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView infoTextView = (TextView) findViewById(R.id.textView3);
		count = 0;
		infoTextView.setText("Количество изученных слов: " + count);
		for (int i = 0; i < count_of_group_questions; ++i)
			ArrQuestion.add(i);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.myproject, menu);
		return true;
	}
	
	//Обработка нажатия на стартовую кнопку
	public void StartButtonClicked(View v) {
		//проверка: пустой ли список
		if (ArrQuestion.size() > 0) {
			startActivityForResult(new Intent(this, QuestionActivity.class), NUMBER);
		} else {
			//выводим: "Вы справились со всеми вопросами"
			
		}
	}
	
	//Выдача результата
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		TextView infoTextView = (TextView) findViewById(R.id.textView3);

		if (requestCode == NUMBER) {
			if (resultCode == RESULT_OK) {
				int code = data.getIntExtra(QuestionActivity.KEY, 0);
				if (code >= 0) {
					//добавляем "+1" в счётчик
					count += 1;
					infoTextView.setText("Количество изученных глаголов: " + count);
					//удаляем текущую группу вопросов из списка:
					ArrQuestion.remove(code);
				}
			}
		}

	}
	
}
