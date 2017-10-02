/*
 * Lesson1hw.c
 *
 *  Created on: Oct 2, 2017
 *      Author: stan
 */

#include <stdio.h>
#include <math.h>


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


int main(){
	int next;
	do{
		printf("Enter task number or 0 for exit: \n");
		printf("only tasks 14, 13 realized so far...\n");
		scanf("%d", &next);
		switch (next){
		case 14:
			printf("\nEnter natural number: \n");
			int num;
			scanf("%d", &num);
			printAutomorphicNumbers(num);
			break;
		case 13:
			break;
		case 0:
			printf("\nThank you! See you next time...");
			break;
		default:
			printf("Wrong task-number %d", next);
		}
	} while(next != 0);
}




