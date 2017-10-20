//
// Created by Konstantin Vinogradov on 20/10/2017.
//

#include <stdio.h>
#include <string.h>
int getSimpleHash(char *words);

void mainLesson6(){

    //task1
    char line[256];
    puts("Enter string to compute hash: ");
    gets(line);
    int hash = getSimpleHash(&line);
    printf("\n%5d\n", hash);

}

int getSimpleHash(char *words){
    int length = strlen(words);
    int result = 0;
    for (int i = 0; i < length; i++){
        result += words[i];
    }
    return result;
}

