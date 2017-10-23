//
// Created by Konstantin Vinogradov on 23/10/2017.
//

#include <stdio.h>
#include <stdlib.h>
#include <malloc/malloc.h>

const int MAX_SIZE = 1024;

int getAdjacencyMatrix(int* arr, char fileName[1024]){
    int size;
    FILE *file = fopen(fileName, "r");
    if (file == NULL) {
        puts("Can't open file!");
        exit(1);
    }
    fscanf(file, "%d", &size); //Считываем размерность матрицы
    int i, j;
    for (i = 0; i < size; i++) {
        for (j=0; j<size; j++){
            fscanf(file, "%d", &arr[i*size + j]);
        }
    }
    fclose(file);

    return size;

}

void deepTravers(int* graf, int size, int start, int* marker){
    marker[start] = 1;
    printf("%3d", start);
    int i,j;
    for (i = 0; i < size; i++){
        if ((marker[i] == 0) & (graf[start*size + i] > 0)){
            deepTravers(graf, size, i, marker);
        }
    }
}

void wideTravers(int* graf, int size, int start, int* marker){
    int flag = 1;
    marker[start] = 1;
    int wave = 1;
    printf("%3d", start);
    while (flag){
        flag = 0;
        int i;
        for (i = 0; i < size; i++){
            if (marker[i] == wave){
                for (int j = 0; j < size; j++){
                    if (marker[j] == 0 & graf[i*size + j] > 0){
                        marker[j] = wave + 1;
                        printf("%3d", j);
                        flag = 1;
                    }
                }
            }
        }
        wave++;
    }
}


void mainLesson7hw(){

    //task1
    int graf[1024];
    int size = getAdjacencyMatrix(graf, "/Users/stan/IdeaProjects/geekbrains_study/Algorithms/Homeworks/adjacency_matrix.txt");
    int i,j;
    printf("\n");
    for (i = 0; i < size; i++){
        for (j = 0; j < size; j++){
            printf("%3d", graf[i*size + j]);
        }
        printf("\n");
    }

    //task2
    int marker[32] = {0};
    int start = -1;
    while(start < 0 || start >= size){
        printf("\nPlease enter number of starting point for travers. Should be from 0 to %d\n", size - 1);
        scanf("%d", &start);
    }
    printf("\n Deep travers starting from %d:\n", start);
    deepTravers(graf, size, start, marker);

    //task3
    for (i = 0; i < size; i++){
        marker[i] = 0;
    }
    printf("\n Wide travers starting from %d:\n", start);
    wideTravers(graf, size, start, marker);
}

