/*
 * Lesson2hw.c
 *
 *  Created on: Oct 6, 2017
 *      Author: stan
 */


#include <stdio.h>
#include <math.h>
#include <stdlib.h>

void printNumberInBinary(int number){
	int digit = number % 2;
	number = number / 2;
	if (number > 0){
		printNumberInBinary(number);
	}
	printf("%d", digit);
}
// have no idea what is the meaning of "свойство четности степени"
int findDegree(int a, int b){
	if (b == 0) return 1;
	if (b == 1) return a;
	return findDegree(a, b/2)*findDegree(a, b/2)*findDegree(a, b % 2);
}

int howManyPathsArr(int a, int b){
	if (a > b) return 0;
	if (b > 100) return 0;
		int array[100] = {0};
	array[a] = 1;
	for (int i = a + 1; i <= b; i++){
		array[i] = array[i-1];
		if (i % 2 == 0) array[i] += array[i/2];
	}
	return array[b];
}
int howManyPathsRec(int a, int b){
	if (a > b) return 0;
	if (b - a == 0) return 1;
	if (b%2 > 0)
		return howManyPathsRec(a, b-1);
	else
		return howManyPathsRec(a, b-1) + howManyPathsRec(a, b/2);
}


int main(){
	int next;
	int a, b;
		do{
			printf("Enter task number or 0 for exit: \n");
			printf("only tasks 1, 2 and 3 realized so far...\n");
			scanf("%d", &next);
			switch (next){
			case 1:
				printf("\nEnter natural number to show it in binary system: \n");
				int num;
				scanf("%d", &num);
				printf("\n");
				printNumberInBinary(num);
				printf("\n");
				break;
			case 2:
				printf("\nThis function compute a^b: \n");
				printf("\nEnter a: \n");
				scanf("%d", &a);
				printf("\nEnter b: \n");
				scanf("%d", &b);
				if (a > 0 && b > 0){
				printf("%d", findDegree(a, b));
				printf("\n");
				} else {
					printf("a & b should be positive numbers!");
				}
				break;
			case 3:
				printf("\nThis function returns number of paths from a to b \n");
				printf("\nEnter a: \n");
				scanf("%d", &a);
				printf("\nEnter b: \n");
				scanf("%d", &b);
				if (a > 0 && b > 0){
				printf("Using array: %d\n", howManyPathsArr(a, b));
				printf("Using recursion: %d\n", howManyPathsRec(a, b));
				printf("\n");
				} else {
				printf("a & b should be positive numbers!");
				}

				break;
			case 0:
				printf("\nThank you! See you next time...");
				break;
			default:
				printf("Wrong task-number %d \n", next);
			}
		} while(next != 0);

	return 0;
}
