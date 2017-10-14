/*
 * Lesson2hw.c
 *
 *  Created on: Oct 6, 2017
 *      Author: stan
 *  Константин Виноградов
 *
 *
 */


#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>

#define MaxN 100

void printArray(int N, int *arr){
	int j;
	printf("\n");
	for(j=0; j<N; j++){
		printf("%5i", arr[j]);
	}
	printf("\n");
}

int fillArray(int *arr){
	char fileName[100];
	int length;
	int i;
	printf("\nEnter path to the test file: \n");
	scanf("%s", &fileName);
	printf(fileName);
	FILE *in;
	in = fopen(fileName, "r");
	fscanf(in, "%i", &length);
	for(i = 0; i < length; i++){
		fscanf(in, "%i",&arr[i]);
	}
	fclose(in);
	return length;
}

void task31(int length, int *arr){
	int arr1[MaxN];
	int arr2[MaxN];
	int i, j, count1, count2, temp;
	memcpy(arr1, arr, MaxN);
	memcpy(arr2, arr1, MaxN);
	printf("\nInitial array: \n");
	printArray(length, arr2);

	//bubble sort
	count1 = 0;
	for(i = 1; i < length; i++){
		for(j = 1; j < length; j++ ){
			if(arr1[j] < arr1[j-1]){
				temp = arr1[j];
				arr1[j] = arr1[j-1];
				arr1[j-1] = temp;
			}
			count1++;
		}
	}
	printf("\n bubble sorted array: ");
	printArray(length, arr1);
	printf("Number of operations: %d", count1);

	//optimized bubble sort
		count2 = 0;
		int flag = 1;
		for(i = 1; i < length; i++){
			if (flag == 0) break;
			flag = 0;
				for(j = 1; j < length; j++ ){
					if(arr2[j] < arr2[j-1]){
						temp = arr2[j];
						arr2[j] = arr2[j-1];
						arr2[j-1] = temp;
						flag = 1;
					}
					count2++;
				}
			}
		printf("\n optimized bubble sorted array: ");
		printArray(length, arr2);
		printf("Number of operations: %d\n", count2);
}

void task2(int length, int *arr){
	printf("\nInitial array: \n");
	printArray(length, arr);

	int left, right, temp;
	int count, i;
	count = 0;
	left = 0;
	right = length - 1;
	while (left < right){
		for(i = left; i < right; i++){
			if(arr[i+1] < arr[i]){
				temp = arr[i];
				arr[i] = arr[i+1];
				arr[i+1] = temp;
			}
			count++;
		}
		right--;
		for(i = right; i > left; i--){
			if(arr[i] < arr[i-1]){
				temp = arr[i];
				arr[i] = arr[i-1];
				arr[i-1] = temp;
			}
			count++;
		}
		left++;
	}
	printf("\nArray after shaker sort: ");
	printArray(length, arr);
	printf("Number of operations: %d\n", count);
}
// Just sorting the array without any output
void sort(int length, int *arr){
	int left, right, temp;
		int i;
		left = 0;
		right = length - 1;
		while (left < right){
			for(i = left; i < right; i++){
				if(arr[i+1] < arr[i]){
					temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
				}
			}
			right--;
			for(i = right; i > left; i--){
				if(arr[i] < arr[i-1]){
					temp = arr[i];
					arr[i] = arr[i-1];
					arr[i-1] = temp;
				}
			}
			left++;
		}
}

int task3(int length, int *arr, int a){
	int left = 0;
	int right = length - 1;
	int med = 0;
	int count = 0;
	while (left < right){
		med = (left + right)/2;
		count++;
		if (arr[med] == a) {
			break;
		} else if (arr[med] < a){
			left = med + 1;
		} else {
			right = med;
		}
	}
	printf("Used %d iterations \n", count);
	if (arr[med] == a) return med;
	else return -1;
}


int main33(){
	int next;
	int arr[MaxN];
	int length;
		do{
			printf("Enter task number or 0 for exit: \n");
			printf("only tasks 1, 2 and 3 realized so far...\n");
			scanf("%d", &next);
			switch (next){
			case 1:
				length = fillArray(arr);
				task31(length, arr);
				break;
			case 2:
				length = fillArray(arr);
				task2(length, arr);
				break;
			case 3:
				length = fillArray(arr);
				sort(length, arr);
				printArray(length, arr);
				int a;
				printf("Enter number to find: \n");
				scanf("%d", &a);
				int index = task3(length, arr, a);
				printf("\n Index of %d is %d \n", a, index);


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
