# Excercise 1

## 1 Program Analysis
1. Sort with library 
2. UI should use more abstraction for creating orders
3. Individually check max for array
4. Use List instead of array
5. UI functionality in model class should be moved out
6. all variable names should be in english to be consistent
7. Use camelCase consistently (menuloop)
8. Make Input more testable with customizable streams (instead of System.in)
9. Use library instead of Input
10. Method names should be verbs (menuLoop -> runMenuLoop)
11. Service: 
    - Fixed hour price as magic number
    - Variable names in constructor
    - Split variables into one line each
    - Class variables can be final
12. Product:
    - Class variables can be final
13. Product - Order too mixed


## 1.1
1. Packages
    1. model
    2. service
    3. util
    4. ui
2. Infinite orders
    1. New while loop
    2. Reset products and services after each order is printed
3. Refactor
    1. Use records for model
