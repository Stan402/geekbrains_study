cmake_minimum_required(VERSION 3.8)
project(Homeworks)

set(CMAKE_C_STANDARD 99)

set(SOURCE_FILES main.c c-course/C10task2.c Lesson6hw.c Lesson6hwTask2.c)
add_executable(Homeworks ${SOURCE_FILES})