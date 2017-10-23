//
// Created by Konstantin Vinogradov on 21/10/2017.
//

#include <stdio.h>
#include <malloc/malloc.h>
#include <stdlib.h>
typedef struct Student{
    char firstName[32];
    char lastName[32];
    int age;
    int weight;
} Student;

typedef Student T2;
typedef struct Node {
    T2 data;
    struct Node *left;
    struct Node *right;
    struct Node *parent;
} Node;

void printStudent(Student *theStudent){
    printf("\nStudent: %s %s, age: %2d, weight: %3d",
           theStudent->firstName, theStudent->lastName, theStudent->age, theStudent->weight);
}


//Создание нового узла
Node* getFreeNode2(T2 value, Node *parent){
    Node* tmp = (Node*) malloc(sizeof(Node));
    tmp -> left=tmp->right=NULL;
    tmp -> data=value;
    tmp -> parent = parent;
    return tmp;
}


//Вставка узла
void insert2(Node **head, T2 value){
    Node *tmp = NULL;
    if (*head == NULL){
        *head = getFreeNode2(value, NULL);
        return;
    }
    tmp = *head;
    while (tmp){
        if (value.age > tmp->data.age){
            if (tmp->right){
                tmp = tmp->right;
                continue;
            } else{
                tmp->right = getFreeNode2(value, tmp);
                return;
            }
        } else if (value.age <= tmp->data.age){
            if (tmp->left){
                tmp = tmp->left;
                continue;
            } else {
                tmp->left = getFreeNode2(value, tmp);
                return;
            }
        } else {
            exit(2);//дерево построено неправильно
        }
    }
}

void preOrderTravers2(Node *root) {
    if (root) {
        printStudent(&root->data);
        preOrderTravers2(root->left);
        preOrderTravers2(root->right);
    }
}

void searchAgeLess(Node *root, int value) {
    if (root) {
        if (root->data.age < value) printStudent(&root->data);
        searchAgeLess(root->left, value);
        searchAgeLess(root->right, value);
    }
}

void readStudent(FILE *file, T2 *value){
    fscanf(file, "%s", value->firstName);
    fscanf(file, "%s", value->lastName);
    fscanf(file, "%d", &value->age);
    fscanf(file, "%d", &value->weight);
}

int mainLesson6Task3() {
    printf("\n\n");
    Node *Tree = NULL;
    FILE *file = fopen("/Users/stan/IdeaProjects/geekbrains_study/Algorithms/Homeworks/data_students.txt", "r");
    if (file == NULL) {
        puts("Can't open file!");
        exit(1);
    }
    int count;
    fscanf(file, "%d", &count); //Считываем количество записей
    int i;
    for (i = 0; i < count; i++) {
        T2 value;
        readStudent(file, &value);
        insert2(&Tree, value);
    }
    fclose(file);
    preOrderTravers2(Tree);
    int ageLimit;
    puts("\nEnter age limit you want to set: ");
    scanf("%d", &ageLimit);
    searchAgeLess(Tree, ageLimit);
    return 0;
}
