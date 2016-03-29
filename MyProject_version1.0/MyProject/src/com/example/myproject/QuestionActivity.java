package com.example.myproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuestionActivity extends Activity{
	
	/*
	 * Основные поля:
	 */
	
	//создаём объект "глобальный вопрос"
		static Question q1 = new Question();
		
	//номер случайного вопроса (пока не задействовано)
		//static int numberOfRandomQuestion;*/
		
	//номер случайной группы вопросов
		static int numberOfRandomGroupQuestion;
		
	//индекс номера случайной группы вопросов
		static int numberIndexOfRandomGroupQuestion;
		
	//кол-во спрошенных вопросов в группе
		static int CountAskQuestion = 0;
		
	//счётчик кол-ва правильных ответов в группе вопросов
		static int CorrectAnswers = 0;
		
	//получен ли ответ на вопрос
		static boolean AnswerTheQuestion;

	/*
	 * Функции и процедуры:
	 */
		
	/*************************************************************************************************************************
	 * Создание активности
	 */
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_second);
			
			//генерируем случайный вопрос
			numberIndexOfRandomGroupQuestion = (int) ((MainActivity.ArrQuestion.size())*Math.random());
			//!? это работает правильно только тогда, когда приведение к int отбрасывает дробную часть, а не округляет число
			
			//задаём случайный вопрос
			AskQuestions();
	}
		
	/*************************************************************************************************************************
	 * Вывод окна диалога
	 */
	public static void showMessage(Context context, String message)
    {
  	  AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
  	  dlgAlert.setMessage(message);
  	  dlgAlert.setTitle("Результат");
  	  dlgAlert.setPositiveButton("OK", null);
  	  dlgAlert.setCancelable(true);
  	  dlgAlert.setPositiveButton("Ok",
  			    new DialogInterface.OnClickListener() {
  			        public void onClick(DialogInterface dialog, int which) {
  			        	//logical = false;
  			        }
  			    });
  	  dlgAlert.create().show();
    }
	
	
	/*************************************************************************************************************************
	 * Вывод вопроса
	 */
	public void output() {
		TextView tv1 = (TextView) findViewById(R.id.textView1);
		TextView tv2 = (TextView) findViewById(R.id.textView2);
		
		//______________________________________________________________
		//Устанавливаем надписи на элементы        |
		//-----------------------------------------V-------------------- 
		tv1.setText("");
		tv2.setText(q1.name);
		//массив id-шников
		int Rid[] = {R.id.radio0, R.id.radio1, R.id.radio2, R.id.radio3, R.id.radio4};
		//записываем ответы в RadioButton
		for (int count = 0; count < q1.N; ++count) {
			RadioButton Rbtn = (RadioButton) findViewById(Rid[count]);
			Rbtn.setText(q1.ans[count]);
		}
		//--------------------------------------------------------------
	}

	
	/*************************************************************************************************************************
	 * Здесь задаём вопросы	
	 */
	public void AskQuestions() {
		
		//создаём 7 массивов, в  каждом из которых вопросные строки разных групп вопросов
		String[] q_1 = getResources().getStringArray(R.array.q_1);
		String[] q_2 = getResources().getStringArray(R.array.q_2);
		String[] q_3 = getResources().getStringArray(R.array.q_3);
		String[] q_4 = getResources().getStringArray(R.array.q_4);
		String[] q_5 = getResources().getStringArray(R.array.q_5);
		String[] q_6 = getResources().getStringArray(R.array.q_6);
		String[] q_7 = getResources().getStringArray(R.array.q_7);
		
		//создаём массив вопросных строк для одной группы вопросов
		String[] q_g = new String[7];
		
		numberOfRandomGroupQuestion = MainActivity.ArrQuestion.get(numberIndexOfRandomGroupQuestion);
		
		q_g[0] = q_1[numberOfRandomGroupQuestion];
		q_g[1] = q_2[numberOfRandomGroupQuestion];
		q_g[2] = q_3[numberOfRandomGroupQuestion];
		q_g[3] = q_4[numberOfRandomGroupQuestion];
		q_g[4] = q_5[numberOfRandomGroupQuestion];
		q_g[5] = q_6[numberOfRandomGroupQuestion];
		q_g[6] = q_7[numberOfRandomGroupQuestion];
		
		/*???
		boolean[] trfa = {false, true, true, true, true, true, true};
		int rand = (int) (6*Math.random());
		while (!trfa[rand]) {
			rand = (int) (6*Math.random());
		}
		trfa[rand] = false;
		*/
		
		if (CountAskQuestion == 0) {
			q1 = new Question(q_g[0]);
			output();
		} else if (CountAskQuestion == 1) {
			q1 = new Question(q_g[1]);
			output();
		} else if (CountAskQuestion == 2) {
			q1 = new Question(q_g[2]);
			output();
		} else if (CountAskQuestion == 3) {
			q1 = new Question(q_g[3]);
			output();
		} else if (CountAskQuestion == 4) {
			q1 = new Question(q_g[4]);
			output();
		} else if (CountAskQuestion == 5) {
			q1 = new Question(q_g[5]);
			output();
		} else if (CountAskQuestion == 6) {
			q1 = new Question(q_g[6]);
			output();
		}
		
		
	}

	
	/*************************************************************************************************************************
	 * Обработка нажатия на кнопку
	 */
	public final static String KEY = "com.example.myproject.KEY";
	public void buttonClicked(View view) {
		TextView tv = (TextView) findViewById(R.id.textView1);
		Button bt = (Button) findViewById(R.id.button1);
		//если ответили на вопрос (нажали на кнопку "ДАЛЕЕ")
		if (AnswerTheQuestion) {
			
			//Меняем кнопку "ДАЛЕЕ" на кнопку "ОТВЕТИТЬ"
			bt.setText("Ответить");
			AnswerTheQuestion = false;
			
			//Если ответили на все вопросы, то:
			if (CountAskQuestion == 6) {
				
				//Обнуляем счётчик кол-ва ответов:
				CountAskQuestion = 0;
				
				// Делаем намерение
				Intent i = new Intent();
				if (CorrectAnswers == 7)
					i.putExtra(KEY, numberIndexOfRandomGroupQuestion);
				else
					i.putExtra(KEY, -1);
				setResult(RESULT_OK, i);
				
				//Обнуляем счётчик правильных ответов
				CorrectAnswers = 0;
				finish();
				
			//если на какие-то вопросы не ответили, то:
			} else {
				CountAskQuestion += 1;
				AskQuestions();
			}
			
		//если не ответили на вопрос (нажали на кнопку "ОТВЕТИТЬ")
		} else {
			
			/**------------------------<находим ответ пользователя>------------------------*/
			int number = 0;
			
			//массив id-шников
			int Rid[] = {R.id.radio0, R.id.radio1, R.id.radio2, R.id.radio3, R.id.radio4};
			
			for (int i = 0; i < q1.N; ++i) {
				RadioButton Rbtn = (RadioButton) findViewById(Rid[i]);
				if (Rbtn.isChecked())
					number = i;
			}
			/**____________________________________________________________________________*/
			
			
			
			/**----<проверяем ответ на правильность и выводим соответствующее сообщение>---*/
			
			//если ответ правильный
			if (q1.correctAnswer == number) {
				
				//выводим сообщение
				tv.setText("Правильно!");
				showMessage(this, "Правильно!");
				
				//добавляем этот ответ в счётчик правильных ответов
				CorrectAnswers += 1;
				
			//если ответ непрвильный
			} else {
				
				//выводим сообщение с правильным ответом
				tv.setText("Неправильно! Правильный ответ " + q1.ans[q1.correctAnswer]);
				showMessage(this, "Неправильно! Правильный ответ " + q1.ans[q1.correctAnswer]);
				
			}
			//выводим соответствующую картинку для данного кол-ва првильных вопросов
			
			/**____________________________________________________________________________*/
			
			
			
			/**--------<проверяем, последний ли вопрос и запускаем следующий вопрос>-------*/
			
			//если вопрос последний, то
			if (CountAskQuestion == 6) {
				
				//если ответы на все вопросы правильные, то
				if (CorrectAnswers == 7) {
					
					//выводим сообщение
					tv.setText("Вы ответили на все вопросы правильно!");
					showMessage(this, "Вы ответили на все вопросы правильно!");
					
				//а если неправильные, то
				} else {
					
					//выводим сообщение с кол-вом правильных ответов
					tv.setText("Вы ответили правильно на " + CorrectAnswers + " вопросов из 7");
					showMessage(this, "Вы ответили правильно на " + CorrectAnswers + " вопросов из 7");
					
				}
			}
			
			//ответ дан на вопрос, ставим значение AnswerTheQuestion в true
			AnswerTheQuestion = true;
			
			//из кнопки "Ответить" делаем кнопку "Далее"
			bt.setText("Далее");
			
			/**____________________________________________________________________________*/
			
		}
		
		
	}


	
	
	
	protected void onDestroy() {
		super.onDestroy();
	}
	
	public void RbuttonClicked(View view) {
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText("");
	}
}
