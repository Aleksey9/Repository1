package com.example.myproject;

import java.util.ArrayList;

import android.widget.RadioButton;
import android.widget.TextView;

/*
 * Объект "вопрос"
 */

public class Question {

	public static final int N = 5;
	
	public String name; //сам вопрос в текстовом виде
	public String ans[] = new String [N]; //массив с ответами на этот вопрос
	public int correctAnswer; //номер правильного ответа
	
	//извлекаем вопрос из вопросной строки и случайным образом его записываем:
	public void AddQuestion(String strQuestion) {
		//создаём список, в который будем заносить ответы на вопрос:
		ArrayList <String> qList = new ArrayList();
		//Обрабатываем вопросную строку:
		String str = strQuestion;
		int p = str.indexOf("_");
		//Заносим название вопроса в сам объект:
		this.name = str.substring(0, p);
		//Заносим по очереди ответы на вопрос в список: 
		for (int i = 0; i < this.N; ++i) {
			qList.add(str.substring(p + 1, str.indexOf("_", p + 1)));
			p = str.indexOf("_", p + 1);
		}
		//Запоминаем номер правильного ответа:
		Integer cA = new Integer(str.substring(p + 1));
		int corAns = (int) (cA);
		//Теперь будем случайным образом вытаскивать из списка ответы и заносить их в массив:
		//Индекс случайного элемента списка:
		int rand;
		//Индекс массива объекта:
		int i = 0;
		//Вытаскиваем из списка элементы, пока он не кончится:
		while (qList.size() > 0) {
			//Генерируем случайный индекс:
			rand = (int) (qList.size()*Math.random());
			//Записываем этот случайный элемент в массив объекта:
			this.ans[i] = qList.get(rand);
			//Удаляем этот элемент:
			qList.remove(rand);
			//Если индекс случайного элемента меньше индекса правильного ответа, то:
			if (rand < corAns) {
				--corAns; // уменьшаем индекс правильного ответа на единицу
			} else if (rand == corAns) { // а если индекс случайного элемента равен индексу правильного ответа, то:
				this.correctAnswer = i; //говорим, что индекс правильного ответа - это i
			}
			++i; //идём дальше
		}
	}
	
	public Question() {
		
		return;
	}
	
	public Question(String strQuestion) {
		String str = strQuestion;
		int p = str.indexOf("_");
		this.name = str.substring(0, p);
		for (int i = 0; i < this.N; ++i) {
			this.ans[i] = str.substring(p + 1, str.indexOf("_", p + 1));
			p = str.indexOf("_", p + 1);
		}
		Integer cA = new Integer(str.substring(p + 1));
		this.correctAnswer = (int) (cA);
		return;
	}
	
	
	//поставить вопрос
	public void SetQuestion(String str) {
		this.name = str;
	}
	
	//добавить ответы на вопрос с указанием номера правильного ответа
	public void SetAnswer(String[] answer, int corAnswer) {
		for (int i = 0; i < N; ++i)
			this.ans[i] = answer[i];
		this.correctAnswer = corAnswer;
	}
	
}
