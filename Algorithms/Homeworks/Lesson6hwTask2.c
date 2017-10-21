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
int searchResult;
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
void inOrderTravers(Node *root){
    if (root){
        preOrderTravers(root->left);
        printf("%d", root->data);
        preOrderTravers(root->right);
    }
}
void postOrderTravers(Node *root){
    if (root){
        preOrderTravers(root->left);
        preOrderTravers(root->right);
        printf("%d", root->data);
    }
}

void searchTree(Node **head, int value){
    if(*head == NULL) return;

    Node *tmp2 = NULL;
    tmp2 = *head;
    if (tmp2){
        if (tmp2->data == value){
            printf("\nFound: %5d", value);
            searchResult = 1;
            return;
        } else if (tmp2->data > value){
            searchTree(&(tmp2->left), value);
        } else if (tmp2->data < value){
            searchTree(&(tmp2->right), value);
        } else puts("Something went wrong :)");
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
    printf("\nInOrderTravers:");
    inOrderTravers(Tree);
    printf("\nPostOrderTravers:");
    postOrderTravers(Tree);
    puts("\nEnter number to find in the Tree: ");
    int toFind;
    scanf("%d", &toFind);
    int result = toFind -1;
    searchResult = 0;
    searchTree(&Tree, toFind);
    if (searchResult == 0) printf("Couldn't find value!");

    return 0;
}
