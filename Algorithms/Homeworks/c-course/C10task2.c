//
// Created by Konstantin Vinogradov on 20/10/2017.
//
#include <stdio.h>
#include <math.h>

int checkArray(int *arr, int length);
void printArray(int *arr, int length);

void mainC10(){
    int length = 5;
    int array[5] = {1,2,3,4,5};
    printArray(array, length);
    printf("\n%5d\n", checkArray(array, length));
    printArray(array, length);


}

int checkArray(int *arr, int length){
    int result = 0;
    for (int i=0; i<length; i++){
        if (arr[i] % 2 != 0){
            result = 1;
            arr[i] *=2;
        }
    }
    return result;
}

void printArray(int *arr, int length){
    printf("\n");
    for (int i = 0; i < length; i++){
        printf("%4d", arr[i]);
    }
    printf("\n");
}