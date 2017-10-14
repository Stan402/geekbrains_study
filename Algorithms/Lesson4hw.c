/*
 * Lesson4hw.c
 * Виноградов Константин
 *
 * 1. *Количество маршрутов с препятствиями.
 *    Реализовать чтение массива с препятствием и нахождение количество маршрутов.
 *
 * Например, карта:
 * 3 3
 * 1 1 1
 * 0 1 0
 * 0 1 0
 * 2. Решить задачу о нахождении длины максимальной последовательности с помощью матрицы.
 *
 */


#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>
// block for task43

#define C = 8
#define R = 8

int board[50][50];

int SearchSolution(int n);
void initBoard(int n, int m, int a[50][50]);


int task41(){
	char fileName[100];
	int N, M;
	int field[50][50];
	int i, j;
	printf("\nEnter path to the test file: \n");
	scanf("%s", &fileName);
	FILE *in;
	in = fopen(fileName, "r");
	fscanf(in, "%i", &N);
	fscanf(in, "%i", &M);
	for(i = 0; i < N; i++){
		for(j = 0; j < M; j++ ){
			fscanf(in, "%i", &field[i][j]);
		}
	}
	fclose(in);
	//fill first column
	for(i = 1; i < N; i++){
		if(field[i][0] == 1){
			field[i][0] = field[i-1][0];
		}
	}
	//fill first raw
	for(j = 1; j < M; j++){
		if(field[0][j] == 1){
			field[0][j] = field[0][j-1];
		}
	}
	//fill the rest of field
	for(i = 1; i < N; i++){
		for(j = 1; j < M; j++){
			if (field[i][j] == 1){
				field[i][j] = field[i-1][j] + field[i][j-1];
			}
		}
	}
	//Printing the field
	printf("\n");
		for(i=0; i<N; i++){
			for(j = 0; j < M; j++){
			printf("%5i", field[i][j]);
			}
			printf("\n");
		}
		printf("\n");

		return field[N-1][M-1];
}

int task42(){
	int N = 6;
	int M = 7;
	int i,j;
	int result = 0;

	char word1[100] = "asdfgg";
	char word2[100] = "adjkfog";

	int matrix[100][100] = {0};
	for(i=1; i< N+1; i++){
		for(j = 1; j < M+1; j++){
			if(word1[i-1] == word2[j-1]){
				matrix[i][j] = matrix[i-1][j-1] + 1;
			} else{
				if (matrix[i-1][j] > matrix[i][j-1]){
					matrix[i][j] = matrix[i-1][j];
				} else{
					matrix[i][j] = matrix[i][j-1];
				}
			}
		}
	}
	result = matrix[N][M];

	return result;
}

void task43(){

}

void initBoard(int n, int m, int a[50][50]){

}

int main(){
	int next;
	int path;
		do{
			printf("Enter task number or 0 for exit: \n");
			printf("only tasks 1, 2 realized so far...\n");
			scanf("%d", &next);
			switch (next){
			case 1:
				path = task41();

				break;
			case 2:
				path = task42();
				printf("\n Length of max subsequence is %d \n\n", path);

				break;
			case 3:


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
