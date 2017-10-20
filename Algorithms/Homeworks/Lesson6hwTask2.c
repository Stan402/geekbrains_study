//
// Created by Konstantin Vinogradov on 20/10/2017.
//


#include <stdio.h>
#include <malloc/malloc.h>
#include <stdlib.h>
typedef int T;
typedef struct Node {
    T data;
    struct Node *left;
    struct Node *right;
    struct Node *parent;
} Node;
// Распечатка двоичного дерева в виде скобочной записи
void printTree(Node *root) {
    if (root) {
        printf("%d", root->data);
        if (root->left || root->right) {
            printf("(");
            if (root->left)
                printTree(root->left);
            else
                printf("NULL");
            printf(",");
            if (root->right)
                printTree(root->right);
            else
                printf("NULL");
            printf(")");
        }
    }
}
//Создание нового узла
Node* getFreeNode(T value, Node *parent){
    Node* tmp = (Node*) malloc(sizeof(Node));
    tmp -> left=tmp->right=NULL;
    tmp -> data=value;
    tmp -> parent = parent;
    return tmp;
}

//Вставка узла
void insert(Node **head, int value){
    Node *tmp = NULL;
    if (*head == NULL){
        *head = getFreeNode(value, NULL);
        return;
    }
    tmp = *head;
    while (tmp){
        if (value > tmp->data){
            if (tmp->right){
                tmp = tmp->right;
                continue;
            } else{
                tmp->right = getFreeNode(value, tmp);
                return;
            }
        } else if (value < tmp->data){
            if (tmp->left){
                tmp = tmp->left;
                continue;
            } else {
                tmp->left = getFreeNode(value, tmp);
                return;
            }
        } else {
            exit(2);//дерево построено неправильно
        }
    }
}

void preOrderTravers(Node *root){
    if (root){
        printf("%d", root->data);
        preOrderTravers(root->left);
        preOrderTravers(root->right);
    }
}

int mainLesson6Task2(){
    Node *Tree = NULL;
    FILE *file = fopen("/Users/stan/IdeaProjects/geekbrains_study/Algorithms/Homeworks/data.txt", "r");
    if (file == NULL){
        puts("Can't open file!");
        exit(1);
    }
    int count;
    fscanf(file, "%d", &count); //Считываем количество записей
    int i;
    for (i=0; i<count; i++){
        int value;
        fscanf(file, "%d", &value);
        insert(&Tree, value);
    }
    fclose(file);
    printTree(Tree);
    printf("\nPreOrderTravers:");
    preOrderTravers(Tree);
    return 0;
}
