# NumbersProject

## Programming task
Write Java/Kotlin code that as an input accepts an array consisting of three numbers (integers). The code should change these numbers to meet the following requirements:
1. Each number should be divisible by three.
2. Sum of all three numbers should be as large as possible.

Changing a number means increasing (decreasing is not allowed) any of its digits. Increasing a digit by one means one change, that is increasing a digit by five would mean five changes. You can perform up to six changes in total for all three numbers.
For example:
1. [110, 222, 3333] can be changed to [111, 222, 3333] - this is one change
2. [111, 222, 33] can be changed to [114, 333, 33] - here we have six changes

You can distribute changes among numbers as you wish. Digit 9 cannot be changed.

## My implementation
I wrote my implementation in Kotlin because I use this language to make Android apps. In my code, in the beginning, the user gets me three numbers. After that, I make a few steps.


### Counting the maximum amount of changes
I check the maximum value for one number written by a user. 
For example:
- if the user writes 127, the max value can be 999, 
- for 325235, it will be 999999.

Then I can calculate max number of possible changes between the user's value and the value created from nines.
It will be helpful to calculate the possible amount of changes.

### Calculating the possible amount of changes
If the user number is dividable by three, you have three options. You can make 0, 3 or 6 changes to this number. 
If the number is not divisible by three and the modulo result is 1, you need to make 2 or 5 changes in this number to get it dividable by three. 
If the number is not divisible by three and the modulo result is 2, you need to make 1 or 4 changes in this number to get it dividable by three.

For example:
- 77 % 3 = 2, so you can make 1 or 4 changes to get value dividable by three (in this case, it is possible to do 1 or 4 changes)
- 88 % 3 = 1, so you can make 2 or 5 changes to get the value dividable by three (but in this case, it is possible to make only two changes because the max number for this value is 99). I used the maximum amount of changes described in the previous paragraph.

### Optimization
In this task, I can change one digit max six times. So number 2 can be changed six times (you will get 8). 
For example, for number 12112, I can't get a greater value than 78778, so I can store this value and use it in the next step.

### Calculating maximum numbers for every amount of changes
I calculate and store the maximum number for the number of changes.
For 77:
- maximum value for 1 change is 87,
- maximum value for 4 changes is 99.

If the user's number has all digits greater or equal to 3, I start the calculation from a number containing only nine digits.
For example:
- user's number: 357893   ->  max number: 999999

If the user's number has less digits than 3, there is no need to start from a number made only from nines. A better option is to add 6 for every digit less than three. 
For example:
- user's number: 162189   ->  max number: 798799 

It is crucial to check if the max number is dividable by 3. If not, it is necessary to add 1 or 2 to this number to make it dividable. 
In the while loop, I can subtract three from the max number to the moment when I will find maximum numbers for every amount of changes described in *Calculating the possible amount of changes* paragraph.

Determining max possible value is very important because if the program starts checking from the highest potential value and then decreasing, we are sure that the first encountered number that matches our requirements will be the highest number for this specific case.

### Finding values meeting the requirement of maximum 6 changes for all numbers
In this part, I try to check all possible combinations of digit changes and store all results with a maximum of six changes or less in all three numbers.

### Getting solutions for the maximum sum
In this step, I sort a list with all possible solutions. I select results for maximum sum and write it in the console.


### End
