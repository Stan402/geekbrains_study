/*
 * Lesson5hw.c
 *
 *  Created on: Oct 18, 2017
 *      Author: stan
 */

#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>
const char delimeter = '_';

int isOperand(char a){
	if (a == '+' | a=='-' | a == '*' | a == '/'){
		return 1;
	} else {
		return 0;
	}
}
int isNumber(char a){
	if( a > 47 & a < 58){
		return 1;
	} else {
		return 0;
	}
}


char* convert(char *s1, int start, int end){
	char first[256];
	char second[256];
	char result[256];
	char operand;
	int indexOperand;

	if (isNumber(s1[start])){
		first = s1[start];
		int i = start + 1;
		while (isNumber(s1[i])){
			strcat(first, s1[i]);
			i++;
		}
		if (isOperand(s1[i])){
			operand = s1[i];
			indexOperand = i;
		} else {
			puts("wrong expression");
		}
		i++;
		if(isNumber(s1[i])){
			second = s1[i];
			i++;
			while(i <= end & isNumber(s1[i])){
				strcat(second, s1[i]);
				i++;
			}
			if (i > end){
				strcat(first, delimeter);
				strcat(first, second);
				strcat(first, operand);
				return first;
			}
		}

	}
	return "fail";
}

int task55(){
	char expression[256];
	printf("Enter standart math expression:\n");
	printf("You can use only numbers, +, -, *, /, (, ). Without spaces!!\n");
	scanf("%s", &expression);
	puts("\n");
	int length = strlen(expression);
	printf("%d", length);
	char result[1024];
	puts(convert(expression, 0, length-1));





	return 0;
}


int main(){

	task55();

	return 0;
}
