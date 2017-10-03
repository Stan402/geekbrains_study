/*
 * Lesson1hw.c
 *
 *  Created on: Oct 2, 2017
 *      Author: stan
 *      Константин Виноградов
 *      Условия задач:
 *
 *      12. Написать функцию нахождения максимального из трех чисел.
 *
 *      13. * Написать функцию, генерирующую случайное число от 1 до 100.
 *      а) с использованием стандартной функции rand()
 *      б) без использования стандартной функции rand()
 *
 *      14. *Автоморфные числа. Натуральное число называется автоморфным,
 *      если оно равно последним цифрам своего квадрата. Например, 252 = 625.
 *       Напишите программу, которая вводит натуральное число N и выводит на экран
 *        все автоморфные числа, не превосходящие N.
 *
 */

#include <stdio.h>
#include <math.h>
#include <stdlib.h>


void printAutomorphicNumbers(int num){
	if (num < 1) {
		printf("There is no automorphic numbers less then %d \n", num);
		return;
	}
	printf("\nAutomorphic numbers less then %d are: \n\n", num);
	int degreesOfTen = 1;
	while (num > degreesOfTen){
		int nextDegree = degreesOfTen * 10;
		int countTo = nextDegree > num ? num : nextDegree;
		for (long i = degreesOfTen; i < countTo; i++){
			if ( (long)((i* i) % nextDegree) == i){
				printf("%d ", (int)i);
			}
		}
		degreesOfTen = nextDegree;
	}
	printf("\n\n");
}

int getRnd(){
	return rand() % 100 + 1;
}

int getMyRnd(int seed){
	int a,b,x,mod;
	mod = 100;
	a = 16645;
	b = 101390;
	return (a * seed + b) % 100 + 1;
}

int findMax(int a, int b, int c){
	int max = b > a ? b : a;
	return c > max ? c : max;
}


int main(){
	int next;
	do{
		printf("Enter task number or 0 for exit: \n");
		printf("only tasks 14, 13 and 12 realized so far...\n");
		scanf("%d", &next);
		switch (next){
		case 14:
			printf("\nEnter natural number: \n");
			int num;
			scanf("%d", &num);
			printAutomorphicNumbers(num);
			break;
		case 13:
			printf("\nThis function returns random numbers from 1 to 100\n");
			int am;
			int flag;
			printf("\nEnter amount of random numbers you need:");
			scanf("%d", &am);
			if(am <= 0) break;
			printf("\nStandart randomizer or custom? 1/0 \n");
			scanf("%d", &flag);
			printf("\n");
			if (flag){
			for(int i = 0; i < am; i++){
				printf("%d ", getRnd());
			}
			} else {
				int seed;
				printf("Enter seed:\n");
				scanf("%d", &seed);
				for(int i = 0; i < am; i++){
					seed = getMyRnd(seed);
					printf("%d ", seed);
				}
			}
			printf("\n");
			break;
		case 12:
			printf("\nThis function returns random numbers from 1 to 100\n");
			int a, b, c;
			printf("Enter 3 number:\n");
			scanf("%d", &a);
			scanf("%d", &b);
			scanf("%d", &c);
			printf("Maximum of 3 is: %d\n\n", findMax(a, b, c));
			break;
		case 0:
			printf("\nThank you! See you next time...");
			break;
		default:
			printf("Wrong task-number %d", next);
		}
	} while(next != 0);
}




